package com.ovalinfo.starter

class SomeClass {
    private var name: String
    var str : String
        get() = "Hello World $name"
        set(i: String) { name = i }
    init {
        name = ""

    }
}