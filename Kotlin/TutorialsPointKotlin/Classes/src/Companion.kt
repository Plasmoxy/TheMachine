
class Util {
	companion object {
		val PI = 3.14
		var value = 0
	}
}

fun main(args: Array<String>) {
	println(Util.PI)
	
	println(Util.value)
	Util.value = 4
	println(Util.value)
}