package com.example.collector.service

import org.slf4j.LoggerFactory
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import javax.jms.Session

@Service
class MetricsConsumer {

    private val log = LoggerFactory.getLogger(MetricsConsumer::class.java)

    @JmsListener(destination = "brokerReplyTo")
    fun receiveMessage(
        @Payload metrics: HashMap<String, String>,
        @Headers headers: MessageHeaders,
        message: Message<HashMap<String, String>>,
        session: Session
    ) {
        log.info("received <$metrics>")
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -")
        log.info("######          Message Details           #####")
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -")
        log.info("headers: $headers")
        log.info("message: $message")
        log.info("session: $session")
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -")
    }
}