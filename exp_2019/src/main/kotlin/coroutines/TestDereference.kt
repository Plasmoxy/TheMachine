package coroutines

import kotlinx.coroutines.*

var job: Job? = null

suspend fun f() {
    delay(2000)
    println("XD")
}


fun main(args: Array<String>) { runBlocking {
    repeat(10) {

        // cancel the job
        job?.cancelAndJoin()
        job = launch { f() }

    }
}}