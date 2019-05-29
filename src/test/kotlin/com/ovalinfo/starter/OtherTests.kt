package com.ovalinfo.starter

import com.ovalinfo.starter.model.cloud
import com.ovalinfo.starter.model.debug
import com.ovalinfo.starter.model.virtualMachine
import mu.KLogging
import org.junit.Test

class OtherTests {

    companion object : KLogging()

    @Test
    fun simpleDslTest() {
        val c = cloud {
            name = "AWS"
            virtualMachine {
                name = "vm1"
                cpu = 10
            }
        }
        logger.debug(c)
    }
}