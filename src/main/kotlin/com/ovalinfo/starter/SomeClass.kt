package com.ovalinfo.starter

import java.lang.StringBuilder
import java.util.stream.Collectors

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
    companion object  {}
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

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    this[index2] = this[index1].also { this[index1] = this[index2] }
}

inline fun <reified T: Any> MutableList<T>.print() {
    println(this.map(Any::toString).fold(StringBuilder(), { sb,  it-> sb.append(it).append(",")} ).toString())
}

val <T> MutableList<T>.bitsize: Int
    get() = this.size*8

fun TestClassProperties.Companion.ten() = 10

class DispatchSomeClass {
    open fun test1() = println("test1 from dispatch class")
    fun SomeClass.test2() {
        test1()
        this@DispatchSomeClass.test1()
    }
    fun callTest2(s : SomeClass) {
        s.test2()
    }
}