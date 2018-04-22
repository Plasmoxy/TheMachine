
data class Book(
		val id: String,
		var name: String,
		var author: String
){
	
	fun dump() {
		println(this)
		println("$id -> $name : $author")
	}
	
}

fun main(args: Array<String>) {
	Book("1", "Kys", "Boris Blyat").dump()
}