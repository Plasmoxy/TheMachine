package otherbulshit

fun main(args: Array<String>) {
	
	val b : String by lazy {
		println("computing")
		"XD"
	}
	
	
	val rand : Double by lazy {
		println("COMPUTING RANDOM VALUE")
		val r = Math.random()
		println(r)
		r
	}
	
	println("$b -> $rand")
	
}