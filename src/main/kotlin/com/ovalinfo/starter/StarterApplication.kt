package com.ovalinfo.starter

import com.ovalinfo.starter.model.Vector2D
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StarterApplication

fun main(args: Array<String>) {
    runApplication<StarterApplication>(*args)
    val v1 = Vector2D(1,1)
    val v2 = Vector2D(1,1)
    v1-v2
}
