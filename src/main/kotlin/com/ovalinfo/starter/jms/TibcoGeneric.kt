package com.ovalinfo.starter.jms

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.apache.cxf.transport.jms.JMSConfigFeature
import org.apache.cxf.transport.jms.JMSConfiguration
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.*
import org.springframework.jms.connection.CachingConnectionFactory

@Configuration
@PropertySource("classpath:/engine.properties")
open class TibcoGenericCA<T>(val queueName: String, val type: Class<T>) {


    @Bean
    open fun jmsConfigurationGeneric(cachingConnectionFactoryCA: CachingConnectionFactory): JMSConfiguration {
        val jmsConfigurationNotifyInboundDeliveryCreated = JMSConfiguration()
        jmsConfigurationNotifyInboundDeliveryCreated.connectionFactory = cachingConnectionFactoryCA
        jmsConfigurationNotifyInboundDeliveryCreated.concurrentConsumers = 16
        jmsConfigurationNotifyInboundDeliveryCreated.targetDestination = queueName
        jmsConfigurationNotifyInboundDeliveryCreated.requestURI = queueName
        jmsConfigurationNotifyInboundDeliveryCreated.timeToLive = 120000
        jmsConfigurationNotifyInboundDeliveryCreated.receiveTimeout = 120000
        return jmsConfigurationNotifyInboundDeliveryCreated
    }

    @Bean
    open fun jmsConfigFeature(jmsConfigurationGeneric: JMSConfiguration): JMSConfigFeature {
        val jms = JMSConfigFeature()
        jms.jmsConfig = jmsConfigurationGeneric
        return jms
    }

    @Bean
    open fun jaxWsProxyFactoryBean(jmsConfigFeature: JMSConfigFeature): JaxWsProxyFactoryBean {
        val res = JaxWsProxyFactoryBean()
        res.address = "jms:jndi:$queueName?jndiConnectionFactoryName=QueueConnectionFactory"
        res.serviceClass = type
        res.features.add(jmsConfigFeature)
        return res
    }

    @Bean
    @Qualifier
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    open fun jaxWsProxyBean(jaxWsProxyFactoryBean: JaxWsProxyFactoryBean): T {
        return jaxWsProxyFactoryBean.create(type)
    }
}