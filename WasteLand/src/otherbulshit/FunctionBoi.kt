package otherbulshit

import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class Server {
	
	val active = AtomicBoolean(true)
	
	private var counter = 0
	
	init {
		while(active.get()) {
			println(counter)
			counter++
			
			if (counter > 5) {
				active.set(false)
			}
			
			Thread.sleep(1000)
		}
	}
}

fun main(args: Array<String>) {
	val th = thread { Server() }
}