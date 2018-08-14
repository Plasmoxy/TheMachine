package io.github.plasmoxy.helloperhaps

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

data class Greeting(val id: Long, val content: String)
data class DoubleData(val value: Double)

@RestController
class GreetingController {

	val counter = AtomicLong()

	@GetMapping("/") fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
			Greeting(counter.incrementAndGet(), "Hello, $name")
	
}

@SpringBootApplication
class Application


fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
