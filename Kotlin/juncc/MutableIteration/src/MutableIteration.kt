fun main(args: Array<String>){
	
	val numbers = mutableListOf(1, 2, 3, 4)
	
	val iterator = numbers.listIterator()
	while (iterator.hasNext()) {
		val num = iterator.next()
		print("$num ")
		if (num%2 == 0) {
			iterator.add(0)
			print("! ")
		}
	}
	
	println(numbers)

}