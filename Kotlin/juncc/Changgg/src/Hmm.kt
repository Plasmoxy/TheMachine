
data class BoxInt(var x: Int) {
	override fun toString() = x.toString()
}

fun chang(t: BoxInt) {
	t.x += 1
}

fun main(args: Array<String>) {

	val x = BoxInt(3)
	println(x)
	chang(x)
	println(x)

}