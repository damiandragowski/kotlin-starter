package com.ovalinfo.starter.model

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import mu.KLogger
@DslMarker
annotation class vmDSL


data class VirtualMachine(val name:String, val cpu: Int)

@vmDSL
class VirtualMachineBuilder {
    var name :String = ""
    var cpu : Int = 0
    fun build() : VirtualMachine = VirtualMachine(name, cpu)
}
fun cloud(block: CloudBuilder.() -> Unit) = CloudBuilder().apply(block).build()

@vmDSL
class VMS:ArrayList<VirtualMachine>() {
    fun vm(block: VirtualMachineBuilder.() -> Unit) {
        add(VirtualMachineBuilder().apply(block).build())
    }
}

data class Cloud (val name: String, val vmList: List<VirtualMachine>)

@vmDSL
class CloudBuilder {
    var name :String = ""
    private val vmList  = mutableListOf<VirtualMachine>()

    fun vms(block: VMS.() -> Unit)  { vmList.addAll(VMS().apply(block)) }
    fun build() : Cloud = Cloud(name, vmList)
}

fun KLogger.debug(c:Cloud)=debug("{} : {}", c.name, c.vmList.joinToString { it.name + " with cpu " + it.cpu })

fun gson(block: GsonBuilder.() -> Unit): Gson = GsonBuilder().apply(block).create()

class CustomStrategyBuilder {

    private var field: (FieldAttributes) -> Boolean = { false }
    private var clazz: (Class<*>) -> Boolean? = { false }

    fun whenField(block: FieldAttributes.() -> Boolean) {
        field = block
    }

    fun whenClass(block: Class<*>.() -> Boolean) {
        clazz = block
    }

    fun build(): ExclusionStrategy {
        return object : ExclusionStrategy {
            override fun shouldSkipClass(clazz: Class<*>?): Boolean {
                return clazz?.let { clazz(it) } ?: false
            }

            override fun shouldSkipField(f: FieldAttributes?): Boolean {
                return f?.let { field(it) } ?: false
            }

        }
    }
}

fun GsonBuilder.serializeExcept(block: CustomStrategyBuilder.() -> Unit) {
    val strategy = CustomStrategyBuilder().apply(block).build()
    this.addSerializationExclusionStrategy(strategy)
}