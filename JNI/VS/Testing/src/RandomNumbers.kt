
fun main(args: Array<String>) {
	var lib = Library()
	var ms = 0L
	var javatime = 0L
	var cpptime = 0L
	
	val ITERATIONS = 10000000
	
	
	println("=== RANDOM NUMBERS BY C++ USING JNI ===")
	
	
	println("=== JAVA - math random ===")
	
	ms = System.currentTimeMillis()
	var javaArr = Array(ITERATIONS, {i->0L}) // create empty array
	for (i in 0..ITERATIONS-1) {
		javaArr[i] = (Math.random()*10).toLong()
	}
	javatime = System.currentTimeMillis() - ms
	
	
	
	println("=== CPP using rand() through JNI")
	
	ms = System.currentTimeMillis()
	var cppArray = lib.someRandoms(ITERATIONS)
	cpptime = System.currentTimeMillis() - ms
	
	
	
	println("RESULTS:\nJAVA = $javatime ms\nC++ = $cpptime ms")
	
}