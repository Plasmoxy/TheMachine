fun main(args: Array<String>) {
	var f: (String, Int) -> Int = { s, x -> 2*x }
	var g: String.(Int) -> Int = { b -> 3 }
	
	// lambda with Int type receiver
	var iplus: Int.(Int) -> Int = { x -> this+x }
	
	println(2.iplus(5))
	println(iplus(2, 4))
}