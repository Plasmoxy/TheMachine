package interfucc
// create fragment for printing
interface Printer {
	val printString : String
	fun print() {
		println(printString)
	}
}

// implement and configure fragment interface
class Human(val name : String, var age : Int) : Printer {
	override val printString = "I'm a Hooman called $name and i got $age years"
}

fun main(args: Array<String>) {
	
	Human("Seb", 17).print()
	
}