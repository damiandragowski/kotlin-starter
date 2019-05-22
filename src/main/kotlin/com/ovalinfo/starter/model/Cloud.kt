package com.ovalinfo.starter.model

data class VirtualMachine(var name:String = "", var cpu: Int = 0)

data class Cloud (var name: String="", var vmList: MutableList<VirtualMachine> = mutableListOf())

fun virtualMachine(block: (VirtualMachine) -> Unit):VirtualMachine {
    val result = VirtualMachine()
    block(result)
    return result
}

fun cloud(block: (Cloud) -> Unit):Cloud {
    val result = Cloud()
    block(result)
    return result
}
