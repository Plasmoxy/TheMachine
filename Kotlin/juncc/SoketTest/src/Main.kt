import kotlin.concurrent.thread

fun main(args: Array<String>) {
	
	var model = MainModel()
	
	var server = Server(9999, model)
	thread { server.listen() }
	
	
	var c1 = Client("localhost", 9999)
	thread { c1.run() } // run yo stuff
	
}