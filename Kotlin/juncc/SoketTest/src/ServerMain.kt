fun main(args: Array<String>) {
	
	var a = 0
	
	val server = Server(9999) { data, client ->

		println("<${client.address}> $data")
		client.send("<${client.address}> $data")
		
	}
	
	
	server.listen()
	
}