package com.ovalinfo.starter

interface TestProperites {
    val testInteger: Int
}

interface TestProperties2 : TestProperites {
    val testAdd1: Int
    val testAdd2: Int
    override val testInteger :Int
        get() = testAdd1 + testAdd2
}

class TestClassProperties(    override val testAdd1: Int,
                              override val testAdd2: Int): TestProperties2 {

}

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
