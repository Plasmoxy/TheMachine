package soket

import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter


fun main(args: Array<String>) {
	
	val socket = IO.socket("http://localhost:5033")
	
	socket.on(Socket.EVENT_CONNECT, {
		
		println("connect client")
		
		println("send foo")
		socket.emit("foo", "hi")
		socket.disconnect()
		
	}).on(Socket.EVENT_DISCONNECT, Emitter.Listener {
		println("disconnect client")
	})
	
	socket.connect()
}