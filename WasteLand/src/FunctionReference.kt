
class Counter(var a : Int) {
	fun add(t : Int) {
		a += t
	}
}

fun main(args: Array<String>) {
	
	var numbers = arrayOf(1, 2, 4, 5)
	
	var c = Counter(0)
	numbers.forEach(c::add)
	
	println(c.a)
}