
fun tens(f: (Int) -> Any?) {
	for (i in 1..10) {
		f(i)
	}
}

fun main(args: Array<String>) {

	tens {
		println(it)
	}

}