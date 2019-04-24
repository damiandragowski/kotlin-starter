package com.ovalinfo.starter.jms

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import com.tibco.tibjms.*
import org.springframework.context.annotation.Bean
import org.springframework.jms.connection.CachingConnectionFactory


/**
 * @author Damian.Drągowski
 */
@Configuration
@PropertySource("classpath:/application.properties")
open class TibcoJms {
    @Value("\${tibco.jms.host}")
    var jmsHost: String? = null

    @Value("\${tibco.jms.user}")
    var jmsUser: String? = null

    @Value("\${tibco.jms.pass}")
    var jmsPass: String? = null

    @Bean
    open  fun targetConnectionFactory(): TibjmsConnectionFactory  {
        val factory = TibjmsConnectionFactory(jmsHost)
        factory.setUserName(jmsUser)
        factory.setUserPassword(jmsPass)
        return factory
    }

    /*
     *   Client Access
     */
    @Bean
    open fun cachingConnectionFactoryCA(targetConnectionFactory: TibjmsConnectionFactory) : CachingConnectionFactory {
        val cachingConnectionFactoryCA = CachingConnectionFactory()
        cachingConnectionFactoryCA.targetConnectionFactory = targetConnectionFactory
        cachingConnectionFactoryCA.sessionCacheSize = 20
        cachingConnectionFactoryCA.isCacheConsumers = true
        return cachingConnectionFactoryCA
    }

    /*
     *   Provider Access
     */
    @Bean
    open fun cachingConnectionFactoryPA(targetConnectionFactory: TibjmsConnectionFactory) : CachingConnectionFactory {
        val cachingConnectionFactoryPA = CachingConnectionFactory()
        cachingConnectionFactoryPA.targetConnectionFactory = targetConnectionFactory
        cachingConnectionFactoryPA.sessionCacheSize = 20
        cachingConnectionFactoryPA.isCacheConsumers = true
        return cachingConnectionFactoryPA
    }
}
