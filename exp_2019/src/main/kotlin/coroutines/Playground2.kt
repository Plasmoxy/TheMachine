package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) { runBlocking {

    val job = GlobalScope.launch {
        delay(1000)
        println("job done")
    }

    job.join()
    job.cancel()
    println(job.start())

    delay(4000)
}}