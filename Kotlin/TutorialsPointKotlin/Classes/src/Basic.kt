class Basic {
	private var msg : String = "HELLO"

	fun hello() {
		println(msg)
	}
}

fun main(args: Array<String>) {
	var b = Basic()
	b.hello()
}