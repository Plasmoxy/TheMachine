
class Human( var meno : String, 
			 var vek : Int ) {
	
	fun greet() {
		println("Ahoj ja som $meno a mam $vek rokov.")
	}
	
}

open class Base(var cislo : Int) {
	
	fun vypis() {
		println(cislo)
	}
	
}

class Derived(c : Int) : Base(c) {
	
	
	
}

fun main(args: Array<String>) {
	
	var a = Derived(4)
	
	a.vypis()
	
}
