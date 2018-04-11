
fun main(args: Array<String>) {
	
	var list = mutableListOf<Int>()
	println("is list arraylist = ${list is ArrayList}")
	
	var map = hashMapOf(
			"name" to "John",
			"age" to 23
	)
	
	println("map->name = ${map["name"]}")
	
	
	for (i in 0..9) {
		list.add(i)
	}
	
	list.filter {it > 3}
	
	println("list = $list")
}