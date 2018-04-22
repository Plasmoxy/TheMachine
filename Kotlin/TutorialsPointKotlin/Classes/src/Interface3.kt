
interface A {
	fun xd() {
		println("XDD")
	}
}

interface B {
	fun xr()  {
		println("XRRR")
	}
}

fun main(args: Array<String>) {
	var a = object:A, B{}
	a.xd()
	a.xr()
}
		