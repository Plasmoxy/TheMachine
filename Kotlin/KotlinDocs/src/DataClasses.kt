
data class Klasa(val name: String, var age: Int)

fun main(args: Array<String>) {

	val a = Klasa("Seb", 17)
	val e = a
	
	val b = Klasa("Seb", 17)
	
	if (e === a) {
		println("ssame object")
	}
	
	if (a == b) {
		println("equals works same data")
	}
	
	println(a)

}