package coroutines

import kotlinx.coroutines.*

fun minDelayTest() = runBlocking {
    val m = launch { delay(2000) }
    val job = launch {
        delay(500)
        println("aafter small delay")
    }

    job.join()
    m.join()

    println("DONE")
}

fun jobState() = runBlocking {

    val job = launch {
        println(isActive)
    }

    job.join()
    println(job.isActive)

}


// returns Deferred with "success" or with Exception("XD")
suspend fun randomStringWithException() = GlobalScope.async {
    delay(1000) // add delay to task (simulates a http request for example)
    if (Math.random() > 0.5) throw Exception("XD")
    else "success"
}

// calculate all tousand coroutines at same, then put them into a list and return
suspend fun <T> awaitTousandResults(generator: suspend () -> Deferred<T>): List<T?> {
    // create a list of tasks
    val tasks = mutableListOf<Deferred<T>>()

    // add 1000 tasks (they automatically start)
    for (i in 0..1000) {
        tasks.add(generator())
    }

    // await all the tasks while also in try/catch
    val results: List<T?> = tasks.map { it ->
        try {
            it.await()
        } catch(e: Exception) {
            null
        }
    }

    return results
}

fun main(args: Array<String>) { runBlocking {
    val stringResults: List<String?> = awaitTousandResults(::randomStringWithException)
    println(stringResults)
}}