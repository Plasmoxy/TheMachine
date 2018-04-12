
import io.socket.client.IO
import io.socket.client.Socket
import java.util.*

val PORT = 5999
val ADDRESS = "http://localhost:$PORT"
val socket = IO.socket(ADDRESS)

val scanner = Scanner(System.`in`)

var active = true

fun slog(x : Any?) {
	print("[ BACKEND ] ")
	println(x)
}

fun exit() {
	socket.disconnect()
}

fun main(args : Array<String>) {
	slog("RUNNING NOW")

	// bind socket events
	socket.on(Socket.EVENT_CONNECT, {
		
		slog("CONNECTED")
		
	}).on("testEvent", {
		
		
		
	}).on(Socket.EVENT_DISCONNECT, {
		
		slog("DISCONNECTED")
		
	})
	
	// connect to server
	socket.connect()
	
	exit()
	
}