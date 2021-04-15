package com.example.collector.configuration

import org.apache.activemq.ActiveMQConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jms.config.DefaultJmsListenerContainerFactory
import org.springframework.jms.core.JmsTemplate


@Configuration
class JmsConfig {

//    @Value("\${wireLevelEndpoint}")
//    val wireLevelEndpoint: String = ""
//
//    var BROKER_URL = "tcp://localhost:61616"
//    var BROKER_USERNAME = "admin"
//    var BROKER_PASSWORD = "admin"
    @Bean
    fun connectionFactory(): ActiveMQConnectionFactory {
        val connectionFactory = ActiveMQConnectionFactory()
//        connectionFactory.brokerURL = BROKER_URL
//        connectionFactory.password = BROKER_USERNAME
//        connectionFactory.userName = BROKER_PASSWORD
        return connectionFactory
    }

    @Bean
    fun jmsTemplate(): JmsTemplate {
        val template = JmsTemplate()
        template.connectionFactory = connectionFactory()
        return template
    }

    @Bean
    fun jmsListenerContainerFactory(): DefaultJmsListenerContainerFactory {
        val factory = DefaultJmsListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory())
        factory.setConcurrency("1-1")
        return factory
    }
}