
class CompanionCore {
	
	companion object {
		val x = 4
		val y = 5
		val z = 7
		
		init {
			println("CompanionCore class lazily loaded")
		}
	}
	
}

fun main(args: Array<String>) {
	CompanionCore
}