package interfucc

interface BaseDelegate {
	// because i dont need class to have methods in interface
	fun hello() {
		println("HELLO FAGGIT")
	}
}

interface AdderDelegate {
	fun add(a : Int, b : Int) : Int {
		return a+b
	}
}

class CoolClass(base : BaseDelegate, adder : AdderDelegate) : BaseDelegate by base, AdderDelegate by adder


fun main(args: Array<String>) {
	
	var base = object : BaseDelegate {}
	var adder = object : AdderDelegate {}
	
	// the cool class now has functions from both Base and Adder delegate interfaces
	var cool = CoolClass(base, adder)
	
	cool.hello()
	println(cool.add(1,2))
	
	
}