
import io.socket.client.IO
import io.socket.client.Socket


var socket = IO.socket("http://localhost:5999")

fun main(args : Array<String>) {
	Hello.hi()

	socket.on(Socket.EVENT_CONNECT, {
		
		socket.emit("foo", "hi")
		
	}).on("event", {
		
		
		
	}).on(Socket.EVENT_DISCONNECT, {
		
		
		
	})
	
}