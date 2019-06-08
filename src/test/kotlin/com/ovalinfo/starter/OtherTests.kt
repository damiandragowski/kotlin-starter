package com.ovalinfo.starter

import com.ovalinfo.starter.model.cloud
import com.ovalinfo.starter.model.debug
import mu.KLogging
import org.junit.Test

class OtherTests {

    companion object : KLogging()

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
        logger.debug(c)
    }
}