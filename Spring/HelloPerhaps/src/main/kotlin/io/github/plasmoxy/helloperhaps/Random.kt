package io.github.plasmoxy.helloperhaps

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RandomController {
	@GetMapping("/random") fun random() = DoubleData(Math.random())
}