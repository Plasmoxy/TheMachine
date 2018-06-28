import java.io.BufferedReader
import java.io.Closeable
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

class Server(val port: Int, val router: (String) -> Unit) {
	
	private val serverSocket = ServerSocket(port)
	var active = true
	
	init {
		println("[Server] created at port $port")
	}
	
	fun listen() {
		
		println("[Server] listening ...")
		
		while (active) {
			val client = serverSocket.accept()
			thread { ClientHandler(this, client).run() } // pass client to handler with its thread
		}
	}
	
}

class ClientHandler(private val server: Server, private val client: Socket) : Closeable {
	
	private val reader = BufferedReader(InputStreamReader((client.getInputStream())))
	private val writer = PrintWriter(client.getOutputStream(), true)
	
	private var active = true
	
	fun run() {

		println("[ClientHandler] ${client.inetAddress.hostAddress} opened connection")
		
		use {
			while (active) {
				val data = reader.readLine()
				
				if (data == null) {
					active = false
					break
				}

				//println("<${client.inetAddress.hostAddress}> ${data}")
				synchronized (server) {
					server.router(data)
				}
				
				
			}
		}
	}
	
	override fun close() {
		reader.close()
		writer.close()
		client.close()
		println("[ClientHandler] ${client.inetAddress.hostAddress} closed connection")
	}
	
}