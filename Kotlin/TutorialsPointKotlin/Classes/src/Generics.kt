
class Printer<T>(var a: T) {
	fun print() {
		println(a)
	}
}

fun main(args: Array<String>) {
	var o = Printer("XD")
	var o2 = Printer(5)
	
	o.print()
	o2.print()
}