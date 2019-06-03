package com.ovalinfo.starter.model

import mu.KLogger

data class VirtualMachine(val name:String, val cpu: Int)
class VirtualMachineBuilder {
    var name :String = ""
    var cpu : Int = 0
    fun build() : VirtualMachine = VirtualMachine(name, cpu)
}
fun cloud(block: CloudBuilder.() -> Unit) = CloudBuilder().apply(block).build()

data class Cloud (val name: String, val vmList: List<VirtualMachine>)
class CloudBuilder {
    var name :String = ""
    private val vmList  = mutableListOf<VirtualMachine>()

    fun vm(block: VirtualMachineBuilder.() -> Unit)  { vmList.add(VirtualMachineBuilder().apply(block).build()) }
    fun build() : Cloud = Cloud(name, vmList)
}

fun KLogger.debug(c:Cloud)=debug("{} : {}", c.name, c.vmList.joinToString { it.name + " with cpu " + it.cpu })
