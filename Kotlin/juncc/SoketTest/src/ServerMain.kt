
fun main(args: Array<String>) {
	
	val server = ChatServer(9999)

	println("-- server up --")
	server.listen()
	
}