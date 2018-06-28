import java.io.BufferedReader
import java.io.Closeable
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

class Server(val port: Int,
             val router: (String, ClientHandler) -> Unit) {
	
	private val serverSocket = ServerSocket(port)
	var active = true
	
	init {
		println("[Server] created at port $port")
	}
	
	fun listen() {
		
		println("[Server] listening ...")
		
		while (active) {
			val clientSocket = serverSocket.accept()
			thread { ClientHandler(this, clientSocket).run() } // pass client to handler with its thread
		}
	}
	
}

class ClientHandler(private val server: Server, 
                    private val socket: Socket) : Closeable {
	
	private val reader = BufferedReader(InputStreamReader((socket.getInputStream())))
	private val writer = PrintWriter(socket.getOutputStream(), true)
	
	private var active = true
	
	val address = socket.inetAddress.hostAddress
	
	fun run() {

		println("[ClientHandler] ${socket.inetAddress.hostAddress} opened connection")
		
		use {
			while (active) {
				val data = reader.readLine()
				
				if (data == null) {
					active = false
					break
				}
				
				synchronized (server) {
					server.router(data, this)
				}
				
			}
		}
	}
	
	fun send(msg: String) {
		writer.println(msg)
	}
	
	override fun close() {
		active = false
		reader.close()
		writer.close()
		socket.close()
		println("[ClientHandler] ${socket.inetAddress.hostAddress} closed connection")
	}
	
}