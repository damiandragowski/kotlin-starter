package com.ovalinfo.starter

open class SomeClass {
    private var name: String
    var str : String
        get() = "Hello World $name"
        set(i: String) { name = i }
    init {
        name = ""

    }

    open fun test1() = println("test1")
}

abstract class SomeClass2 : SomeClass() {
    override abstract  fun test1()
}
