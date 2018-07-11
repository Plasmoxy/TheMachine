import java.util.*
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit

// hmm this magic aint work
operator fun <T> ScheduledThreadPoolExecutor.rangeTo(other: () -> T): ScheduledFuture<T>
	= schedule({other()}, 0, TimeUnit.SECONDS) as ScheduledFuture<T>

fun main(args: Array<String>) {
	val t = ScheduledThreadPoolExecutor(1)
	
	val tascc: ScheduledFuture<Int> = t..{
		println(Date())
		3
	}
	
	println(tascc.get())
}