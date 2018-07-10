
private class Greetings(val map: Map<String, Any?>) {
	val home: String by map
	val outside: String by map
	
	override fun toString(): String = "Greetings -> home=$home, outside=$outside"
}

fun main(args: Array<String>) {

	var mp = mapOf("home" to "Hello", "outside" to "Good day")
	println(Greetings(mp))

}