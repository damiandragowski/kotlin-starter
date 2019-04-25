package com.ovalinfo.starter

import com.ovalinfo.starter.jms.TibcoJms
import mu.KLogging
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.connection.CachingConnectionFactory
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [(TibcoJms::class)])
@WebAppConfiguration
class StarterApplicationTests {

	companion object : KLogging()

	@Autowired
	private lateinit var cachingConnectionFactoryCA: CachingConnectionFactory

	@Test
	fun contextLoads() {
		val m = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
		val l = listOf(2,2,3,5,5)
		for ( (k,v) in m ) {
			println("key = $k value = $v")
		}
		for ( (index,value) in l.withIndex() ) {
			println("" + index + " value = " +value)
		}
		logger.debug(cachingConnectionFactoryCA.toString())
		logger.debug("test")
	}

}
