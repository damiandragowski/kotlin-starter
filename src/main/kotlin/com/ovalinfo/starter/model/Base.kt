package com.ovalinfo.starter.model

import com.ovalinfo.starter.intList
import mu.KLogger
import mu.KLogging
import java.lang.IllegalStateException
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

typealias foldFun<A,V> = (A,V) -> A

fun <A,V> Collection<V>.comb(accumulator: A, function: ((A,V)->A) ): A {
    var result = accumulator
    for ( t in this) {
        result = function(result,t)
    }
    return result
}

fun KLogger.debug(i : Int): Unit = this.debug("{}", i)

val  myAdd1:  MutableCollection<Int>.(Int) -> Boolean = { it -> add(it) }
// val  myAdd2: <reified T> MutableCollection<T>.(T) -> Boolean = { it -> add(it) }

fun <T> MutableCollection<T>.myAdd2(t :T): Boolean = this.add(t)

fun sequence(seed: Int): (()->Int) {
    var seq: Int = seed
    return { seq++
        seq
    }
}

class DoAdditionProcess<T>(var _value: T) {
    companion object : KLogging()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        logger.debug("Getting property ${property.name}")
        return _value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        logger.debug("Setting property ${property.name}")
        this._value = value
    }
}

open class Base(val strInit:String="") {
    var str: String by DoAdditionProcess<String>(strInit)
    open fun test() {
        println("Base.test()");
    }
}

class Derived: Base() {
    override fun test() {
        println("Derived.test()");
    }
}
interface Factory<T> {
    fun create(): T
}

class Person(val map: MutableMap<String,String>, val historyList: intList ) {
    var surname : String by map
    var name : String by map
    var age : Int by Delegates.observable({
        historyList.add(0)
        0
    }()) {
        _, _, newVal -> historyList.add(newVal)
    }

    companion object: Factory<Person> {
        override fun create():Person { return  Person(mutableMapOf(
            "name" to "",
            "surname" to ""), intList())
        }
        fun createPerson(list: intList): Person  = Person(mutableMapOf(
            "name" to "",
            "surname" to "")
            ,list)
    }
}

fun failToGet(str : String):Nothing {
    throw IllegalStateException(str)
}

fun <T> Collection<T>.partitionTo(a: MutableCollection<T>,
                                  b:  MutableCollection<T>,
                                  comparer: (T)->Boolean):Pair<MutableCollection<T>, MutableCollection<T>> {
    for ( aa in this) {
        if ( comparer(aa) ) {
            a.add(aa)
        } else {
            b.add(aa)
        }
    }
    return Pair(a,b)
}

fun <T> T.myApply(f: T.() -> Unit): T { return this.apply(f) }

fun createString(): String {
    return StringBuilder().myApply {
        append("Numbers: ")
        for (i in 1..10) {
            append(i)
        }
    }.toString()
}

fun buildMap(f: MutableMap<Int, String>.() -> Unit):MutableMap<Int, String> {
    val h:MutableMap<Int, String> = mutableMapOf()
    h.f()
    return h
}

fun usage(): Map<Int, String> {
    return buildMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}