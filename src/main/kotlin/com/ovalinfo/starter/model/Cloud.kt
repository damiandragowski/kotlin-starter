package com.ovalinfo.starter.model

import mu.KLogger

data class VirtualMachine(val name:String, val cpu: Int)
class VirtualMachineBuilder {
    var name :String = ""
    var cpu : Int = 0
    fun build() : VirtualMachine = VirtualMachine(name, cpu)
}
fun cloud(block: CloudBuilder.() -> Unit) = CloudBuilder().apply(block).build()

data class Cloud (val name: String, val vm: VirtualMachine?)
class CloudBuilder {
    var name :String = ""
    private var vm : VirtualMachine? = null

    fun vm(block: VirtualMachineBuilder.() -> Unit)  { vm = VirtualMachineBuilder().apply(block).build() }
    fun build() : Cloud = Cloud(name, vm)
}

fun KLogger.debug(c:Cloud)=debug("{} : {} with {} cpus", c.name, c.vm?.name, c.vm?.cpu)
