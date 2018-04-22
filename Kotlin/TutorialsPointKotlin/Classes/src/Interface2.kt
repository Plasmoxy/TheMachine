
interface Iface {
	var prop: String
	fun useProp()
}

class User(override var prop: String) : Iface {
	override fun useProp() {
		println("prop = " + prop)
	}
}

fun printProp(a: Iface) {
	println("===USING PROP==")
	a.useProp()
	println("===============")
}

fun main(args: Array<String>) {
	var user = User("XDDDD PROP")
	printProp(user)
	
	printProp(object:Iface {
		override var prop = "XD"
		override fun useProp() {
			for (i in 0..9) {
				println(prop)
			}
		}
	})
}
		