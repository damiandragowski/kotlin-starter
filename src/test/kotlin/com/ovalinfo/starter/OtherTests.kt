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
            it.name = "AWS"
        }
        logger.debug(c)
    }
}