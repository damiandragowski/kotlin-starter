package com.ovalinfo.starter.model

import mu.KLogging
import javax.persistence.Tuple
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

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

class Person(val map: MutableMap<String,String>, val historyList: ArrayList<Int> ) {
    var surname : String by map
    var name : String by map
    var age : Int by Delegates.observable({
        historyList.add(0)
        0
    }()) {
        property, oldVal, newVal -> historyList.add(newVal)
    }
}