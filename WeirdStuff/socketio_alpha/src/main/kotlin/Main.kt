
import io.socket.client.IO
import io.socket.client.Socket

val PORT = 5999
val ADDRESS = "http://localhost:$PORT"
var socket = IO.socket(ADDRESS)

var active = true

fun slog(x : Any?) {
	println("[ JVM BACKEND ] $x")
}

fun meanwhile(x: Any?) {
	print("\n$x\nserver>")
}


fun main(args : Array<String>) {
	slog("RUNNING NOW")

	// bind socket events
	socket.on(Socket.EVENT_CONNECT, {
		
		meanwhile("CONNECTED")

	}).on("xdBack", {
		
		if (it.isNotEmpty()) {
			meanwhile("XD MESSAGE BACK = ${it[0]}")
		}
		
	}).on(Socket.EVENT_DISCONNECT, {
		
		meanwhile("DISCONNECTED")

		if(!active) {
			slog("EXITING")
			System.exit(0)
		}
		
	})
	
	// connect to server
	socket.connect()
	
	
	// CONSOLE
	println("=== CONSOLE (type e or exit to quit) ===")
	consoleloop@while (active) {
		
		print("server>")
		
		val cmd = readLine()
		if (cmd != null) {
			
			when (cmd) {
				"e", "exit" -> active = false
				"xd" -> socket.emit("xd", "LOLOLO")
				else -> println("No such command.")
			}
			
		}
		
	}
	
	
	slog("DISCONNECTING")
	socket.disconnect()
	
}