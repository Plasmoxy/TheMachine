
fun main(args: Array<String>) {
	var lib = Library()
	
	println("=== RANDOM NUMBERS BY C++ USING JNI ===")
	
	for ((i, n) in lib.someRandoms(1000000).withIndex()) {
		println("$i -> $n")
	}
}