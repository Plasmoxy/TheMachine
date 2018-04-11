

open class A(var v : Int) {
	
	init {
		println("A CREATED -> v=" + this.v)
	}
	
}

open class B(var v : Int) {
	
	init {
		println("B CREATED ->v=" + this.v)
	}
	
}

open class C(v: Int, var msg : String) : A(v) {
	
	init {
		println("C CREATED from A")
	}

	fun say() {
		println(msg + "C.v = " + v)
	}
	
}

fun main(args: Array<String>) {
	
	var a = A(1)
	var b = B(2)
	var c = C(4, "Hello fag this is C")
	
	c.say()
	
}