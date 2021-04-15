package com.example.collector.service

import org.springframework.jms.core.JmsTemplate
import org.springframework.stereotype.Service
import javax.jms.Queue

@Service
class MetricsProducer(val jmsTemplate: JmsTemplate) {

    fun sendMessage(queueName: String, message: String) {
        println("Sending message " + message + "to queue - " + queueName)
        jmsTemplate.send(queueName) { session ->
            session.createTextMessage()
        }
    }

    fun sendBrokerStatRequest() {
        val replyToName = "brokerReplyTo"
        val queueBrokerName = "ActiveMQ.Statistics.Broker"
        println("Sending stat request to broker, reply to $replyToName")
        var replyToQueue: Queue? = null
        jmsTemplate.execute { session ->
            replyToQueue = session.createQueue(replyToName)
            session.createConsumer(replyToQueue)
        }
        jmsTemplate.send(queueBrokerName) { session ->
            val message = session.createTextMessage()
            message.jmsReplyTo = replyToQueue
            message
        }
    }

    fun sendQueueStatRequest(queueName: String) {
        val replyToName = "${queueName}ReplyTo"
        val queueBrokerName = "ActiveMQ.Statistics.Broker"
        println("Sending stat request to broker - $queueName")
        var replyToQueue: Queue? = null
        jmsTemplate.execute { session ->
            replyToQueue = session.createQueue(replyToName)
            session.createConsumer(replyToQueue)
        }
        jmsTemplate.send(queueBrokerName) { session ->
            val message = session.createTextMessage()
            message.jmsReplyTo = replyToQueue
            message
        }
    }
}