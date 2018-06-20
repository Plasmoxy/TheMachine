package soket

import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketIOServer

fun main(args: Array<String>) {

	val config = Configuration()
	config.hostname = "localhost"
	config.port = 5033
	
	val server = SocketIOServer(config)
	
	server.addConnectListener { 
		println(it.remoteAddress)
	}
	
	server.addDisconnectListener {
		println("DISCON")
	}
	
	server.addEventListener("foo", String::class.java) { client, str, ack ->
		println("XDDDDDDDDDDDDD")
	}
	
	server.start()
	
	Thread.sleep(Long.MAX_VALUE)
	
	server.stop()
	
}