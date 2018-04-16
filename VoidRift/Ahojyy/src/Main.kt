
open class Entity(_x : Int) {

	var x = _x

	open fun show() {
		println(x)
	}
}

class Sub(_x : Int) : Entity(_x) {
	override fun show() {
		println("SHOWING SUBB")
		super.show()
	}
}

fun main(args : Array<String>) {
	var e = Sub(5)
	e.show()
}