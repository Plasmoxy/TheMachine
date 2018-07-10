import kotlin.reflect.KClass

private open class Klas
private open class KlasDerived : Klas()

private fun parg(vararg args: Any) {
	println(args.joinToString(" "))
}

fun main(args: Array<String>) {
	parg(1, 2, 3, "XD", 0.12)
	
	val testArray = arrayOf(7, 2, 3)
	parg(*testArray)
	
	
}