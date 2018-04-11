
fun pront(f: () -> Any?) {
	println(f())
}

fun main(args: Array<String>) {
	
	var a = 5
	
	pront {
		when (a) {
			1, 2 -> "ONE TWO"
			3, 4 -> "THREE FOUR"
			else -> "NONE"
		}
	}
	
	println({
		var temp = ""
		for (i in 0..9) {
			temp += i
		}
		temp
	}())
	
}