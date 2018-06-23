package io.github.plasmoxy.empty

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmptyApplication

fun main(args: Array<String>) {
    runApplication<EmptyApplication>(*args)
}
