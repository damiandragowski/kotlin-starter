package com.ovalinfo.starter.model

import mu.KLogger

data class VirtualMachine(var name:String = "", var cpu: Int = 0)

data class Cloud (var name: String="", var vm: VirtualMachine? = null)

fun cloud(block: Cloud.() -> Unit) = Cloud().apply(block)

fun Cloud.virtualMachine(block: VirtualMachine.() -> Unit) = { vm = VirtualMachine().apply(block) }

fun KLogger.debug(c:Cloud)=debug("{} : {} with {} cpus", c.name, c.vm?.name, c.vm?.cpu)
