
fun main(args : Array<String>) {

	// kotlin class
	var h = Hello()
	h.say()
	println(h.add(1,2))

	// java class
	println(Math.add(5, 6))

	var obj = object {
		var value = 4
	}

	println(obj.value)


	var arr = arrayOf(
			1, 2, 3
	)

	println("arr[2] = " + arr[2])

}