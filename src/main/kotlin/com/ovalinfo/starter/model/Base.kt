package com.ovalinfo.starter.model

import com.ovalinfo.starter.intList
import mu.KLogging
import javax.persistence.Tuple
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

typealias foldFun<A,V> = (A,V) -> A

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
        property, oldVal, newVal -> historyList.add(newVal)
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