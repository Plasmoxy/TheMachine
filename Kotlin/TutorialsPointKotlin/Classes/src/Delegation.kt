

interface Printable {
	fun print(s: String)
}

class Adapter(superObject: Printable) : Printable by superObject

fun main(args: Array<String>) {
	Adapter(object:Printable {
		override fun print(s: String) {
			println(s)
		}
	}).print("XD")
}