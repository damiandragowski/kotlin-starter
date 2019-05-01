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

    val queueName by lazy {
        cachingConnectionFactoryCA.toString()
    }

    @Test
    fun contextLoads() {
        val m = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
        val l = listOf(2, 2, 3, 5, 5)
        for ((k, v) in m) {
            println("key = $k value = $v")
        }
        for ((index, value) in l.withIndex()) {
            println("" + index + " value = " + value)
        }

        l.let {
            it.size
        }.let {
            println(it)
        }

        l.also {
            it.reversed()
        }.also {
            println(it)
        }


        logger.debug(queueName)
        logger.debug("test")
    }

    @Test
    fun `Some class test` () {
        val s = SomeClass()
        val s1 = if (s.str.startsWith("H")) {
            println("if statement")
            s.str } else {
            println("else statement")
            ""
        }
        logger.debug(s1)
        s.str = "from test"
        logger.debug(s.str)

        val a:Short = 1 shl 2
        logger.debug(a.toString())
        var s2 = 1
        outerLoop@ for ( i in 1..5 )
            for ( j in 1..5 ) {
                if ( i == 2 ) break@outerLoop
                s2 = s2 shl 1
            }
        logger.debug(s2.toString())


        val l1 = listOf(9,8,7,6,5,4,3,2,1)

        l1.forEach label@{
            if (it > 6) return@label

        }

        val testClassProperties = TestClassProperties(1,2)
        logger.debug ("outcome " + testClassProperties.testInteger)

        val l2 = mutableListOf(1, 2, 3, 4)

        l2.swap(3,0)
        l2.print()
        logger.debug("bit size " + l2.bitsize)



        //val sss:SomeClass2 =  SomeClass2()
    }

}
