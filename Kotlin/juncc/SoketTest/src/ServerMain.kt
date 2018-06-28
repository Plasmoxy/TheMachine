fun main(args: Array<String>) {
	
	var a = 0
	
	val server = Server(9999) {
		when(it) {
			
			"hello" -> {
				a++
				println(a)
			}
			
		}
	}
	
	
	server.listen()
	
}