
sealed class Size {
	class BIG : Size()
	class SMALL : Size()
}

enum class Size2 {
	BIG, SMALL
}


fun main(args: Array<String>) {
	var size = Size.BIG()
	var size2 = Size2.BIG
	
	when (size) {
		is Size.BIG -> println("XD")
	}
	
	when(size2) {
		Size2.BIG -> println("XDD")
	}
	
	
}