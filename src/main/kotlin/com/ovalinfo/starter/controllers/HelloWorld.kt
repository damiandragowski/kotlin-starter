package com.ovalinfo.starter.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController {

    @GetMapping("/helloworld")
    fun helloWorld() = "Hello World"
}