
// holder is both producer and consumer
private class Holder<T>(private var x: T) {
	fun get(): T = x
	fun set(a: T) {
		x = a
	}
}


private fun holderCopy(from: Holder<out Any>, to: Holder<in Any>) {
	to.set(from.get())
}

fun main(args: Array<String>) {
	val first = Holder("XD")
	val second = Holder(Any())
	
	// this wont be allowed because the function can do bad things to first
	// -> i.e. write Int to String variable, which would then throw ClassCastException
	// so we just project these types as producer / consumer -only
	println(second.get())
	holderCopy(first, second)
	println(second.get())
}