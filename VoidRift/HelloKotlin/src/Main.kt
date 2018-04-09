
fun main(args : Array<String>) {

	var h = Hello()
	h.say()
	println(h.add(1,2))

	var obj = object {
		var value = 4
	}

	println(obj.value)
}