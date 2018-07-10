
// fact(1) = 1
// fact(2) = 1*2 = fact(1) * 2
// fact(3) = 1*2*3 = fact(2) * 3
// fact(x) = fact(x-1) * x

// nope just normal recursive FUCC
// well perhaps ifchecks or smth
private fun factorial(x: Int): Int = if (x == 0) 1 else factorial(x-1) * x

fun main(args: Array<String>) {
	println(factorial(5))
}