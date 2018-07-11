data class User(val name: String, val age: Int)

fun main(args: Array<String>) {
	val lst = mutableListOf(1, 2)
	lst[0] = 4
	
	val map = mutableMapOf<Int, String>()
	map[1] = "X"
	
	println(map)
	
	
	val users = mutableMapOf<Int, User>()
	users[0] = User("Seb", 17)
	
	println(users)
}