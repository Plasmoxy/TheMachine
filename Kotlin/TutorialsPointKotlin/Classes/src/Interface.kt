
typealias Xd = Map<String, Int>

interface Greeter {
	fun greet(name: String) : String
}

class Human(var name: String) {
	var greeter : Greeter? = null
	
	fun introduce() {
		println("=== INTRODUCING SEB ===")
		with (greeter?.greet(name)) {
			println(if (this != null) this else "")
		}
	}
	
}

fun main(args: Array<String>) {
	var h = Human("Seb")
	h.greeter = object:Greeter {
		override fun greet(name: String): String {
			return "HELLO I'M " + name
		}
	}
	h.introduce()
}