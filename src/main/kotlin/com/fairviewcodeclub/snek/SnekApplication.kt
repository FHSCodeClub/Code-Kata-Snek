package com.fairviewcodeclub.snek

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SnekApplication

fun main(args: Array<String>) {
    runApplication<SnekApplication>(*args)
}
