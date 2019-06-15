package com.ovalinfo.starter

import com.ovalinfo.starter.model.*
import mu.KLogging
import org.junit.Test
import kotlinx.coroutines.*

class OtherTests {

    companion object : KLogging()

    @Test
    fun runSimpleThread() {

    }

    @Test
    fun simpleDslTest() {
        val c = cloud {
            name = "AWS"
            vms {
                vm {
                    this@cloud.name = "Sick"
                    name = "vm1"
                    cpu = 10
                }
                vm {
                    name = "vm2"
                    cpu = 8
                }
                vm {
                    name = "vmware1"
                    cpu = 4
                }
            }
        }
        logger.debug(gson {
            serializeExcept {
                whenClass { equals(VirtualMachine::class.java) }
            }
            serializeNulls()
        }.toJson(c))
    }
}