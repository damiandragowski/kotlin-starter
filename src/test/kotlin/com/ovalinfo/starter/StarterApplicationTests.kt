package com.ovalinfo.starter

import com.ovalinfo.starter.jms.TibcoJms
import com.ovalinfo.starter.model.*
import mu.KLogging
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jms.connection.CachingConnectionFactory
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.NullPointerException
import java.lang.StringBuilder

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
    class Test123:()->Unit {
        override operator fun invoke(): Unit {
            logger.debug("Test123")
        }
    }

    @Test
    fun annotationTest() {
        val v1 = Vector2D(10,10)
        val v2 = Vector2D(3,4)
        logger.debug((v1-v2).toString())
    }

    @Test
    fun nullable() {
        val l = listOf<String?>("DDR", null, "AWL")
        for ( n in l) {
            n?.let { logger.debug(it) }
            n.let { logger.debug(it?.length?:0) }
        }

        val notNofl = l.filterNotNull()
        try {
            logger.debug(l[1])
        } catch (e: Exception) {
            logger.debug ("NPE occures")

        }
        try {
            for (n in l) {
                val newN = n?.length ?: failToGet("no value")
                logger.debug(newN)
            }
        }catch(e: IllegalStateException) {

        }

    }

    @Test
    fun operatorTest() {
        val v1 = Vector2D(10,10)
        val v2 = Vector2D(3,4)
        logger.debug((v1+v2).toString())
        logger.debug((v1*v2))
        logger.debug(v2.perpendicular().toString())
        logger.debug(v2[0])
        logger.debug(v2[1])
        logger.debug(v1(v2))
    }

    @Test
    fun labmdasTest() {
        val foldFunImpl: foldFun<StringBuilder, Int> = { arr, value ->
            arr.append(value)
            arr
        }

        val list = listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        logger.debug(list.fold(StringBuilder(), foldFunImpl).toString())
        Test123()()
        val powy: Double.(Int) -> Double = { it ->
            var a = 1.0
            repeat(it) { a *= this }
            a
        }
        logger.debug("outcome " + 2.0.powy(8))
        logger.debug("outcome " + powy(2.0, 8))
        logger.debug("outcome " + powy.invoke(2.0, 8))

        logger.debug("outcome " + list.comb(100, function = { acc, value -> acc - value }))

        val genFromTen = sequence(10)
        val genFromHundred = sequence(100)
        repeat(10) {
            logger.debug("seq: " + genFromTen())
            logger.debug("seq: " + genFromHundred())
        }
        val list2 = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7)
        list2.myAdd1(8)
        list2.forEach({ it -> logger.debug(it) })
        list2.myAdd2(10)
        list2.forEach({ it -> logger.debug(it) })


    }

    @Test
    fun delegateProps() {

        val base = Base()
        base.str = "Test"
        logger.debug(base.str)
        val list: intList = ArrayList()

        val person = Person(
            mutableMapOf(
            "name" to "test",
            "surname" to "tester")
            ,list
        )

        logger(person.toString())
        person.age = 5
        person.age = 55
        person.age = 555
        logger.debug(person.age.toString())

        logger.debug("Change history " + list.toString())

        logger.debug(Person.createPerson(list).name)

    }

    @Test
    fun forTestsLetAlso() {
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
    fun `Inner Loop, Outer Loop breaking example` () {

        var s2 = 1
        outerLoop@ for ( i in 1..5 )
            innerLook@ for ( j in 1..5 ) {
                if ( i == 2 ) break@outerLoop
                s2 = s2 shl 1
            }
        logger.debug(s2.toString())

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

        DispatchSomeClass().callTest2(SomeClass())
        val anim1 = Animal(nickname = "bob", age = 10)
        val anim2 = Animal(nickname = "boby", age = 10)
        logger.debug(anim1.toString())
        logger.debug((anim1+anim2).toString())
        val (a1, a2) = anim1
        logger.debug(a1)
        logger.debug(a2)
        test(BootClass.ClassicBoot("jagged"))
        logger.debug ( "" + fact(5))

        testing(SourceImpl("1"));
    }

    interface Source<in V, out T> {
        fun produce():T
        fun consume(v : V):Int
    }

    class SourceImpl<V, T>(val t: T): Source<V, T> {
        override fun produce(): T {
            return t
        }

        override fun consume(v: V):Int {
            return v.toString().length
        }
    }

    fun testing(s : Source<Any, String>) {
        val s2 : Source<String, Any> = s
        println(s2.consume("testing"))
        println(s2.produce())
    }
    // use sealed class as expresion
    fun test(e : BootClass):Unit =
        when(e) {
            is BootClass.ClassicBoot -> { e.test() }
            is BootClass.SmartBoot -> { println("SmartBoot") }
            is BootClass.EmptyBoot -> println("else")
        }

    tailrec fun fact(i : Long, run :Long = 1): Long {
        return if ( i == 1L ) run else fact(i-1, run*i)
    }
}
