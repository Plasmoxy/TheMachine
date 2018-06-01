package otherbulshit
class Person(val name : String, var vek : Int) {
	
	fun greet() {
		println("Ahoj ja som $name a mam $vek rokov.")
	}
	
}


open class A(var cislo : Int) {
	open fun zobraz() {
		println(cislo)
	}
}

open class B(c : Int) : A(c) {
	
	override fun zobraz() {
		println("TOTO JE Z BECKA")
	}
	
}


fun main(args: Array<String>) {
	var p = Person("Peter", 17)
	p.greet()
	
	
	var t = B(3)
	t.zobraz()
}