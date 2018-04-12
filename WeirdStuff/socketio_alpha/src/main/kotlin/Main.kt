
import io.socket.client.IO
import io.socket.client.Socket


var socket = IO.socket("http://localhost:5999")

fun slog(x : Any?) {
	print("[ BACKEND ] ")
	println(x)
}

fun main(args : Array<String>) {
	slog("RUNNING NOW")

	socket.on(Socket.EVENT_CONNECT, {
		
		slog("CONNECTED")
		
	}).on("event", {
		
		
		
	}).on(Socket.EVENT_DISCONNECT, {
		
		
		
	})
	
	socket.connect()
	
}