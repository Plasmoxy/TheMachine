import kotlin.coroutines.experimental.buildSequence

//actually nope probably removed
fun main(args: Array<String>) {
	var sqe = buildSequence {
		yield(3)
	}
	
	println(sqe)
}