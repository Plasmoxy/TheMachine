
interface Source<out T> {
	fun next(): T
}

class SourceClass: Source<String> {
	override fun next(): String = "XD"
}

// does cast as T in Source is only producing ( out ), not consuming
fun demo(obj: Source<Any>) {
	println(obj.next())
}

fun main(args: Array<String>) {
	demo(SourceClass())
	
}