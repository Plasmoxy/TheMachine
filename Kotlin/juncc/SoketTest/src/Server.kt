// lambda routing java socket server by Plasmoxy


import java.io.*
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread

open class Server(val port: Int) {
	
	private val serverSocket = ServerSocket(port)
	
	var active = true // to control server
	
	// routing
	var onMessage: (msg: String, client: ClientHandler) -> Unit = {_,_->}
	var onConnect: (client: ClientHandler) -> Unit = {}
	var onDisconnect: (client: ClientHandler) -> Unit = {}

	var clients = listOf<ClientHandler>()
	
	fun listen() {
		while (active) {
			val clientSocket = serverSocket.accept()
			thread { ClientHandler(this, clientSocket).run() } // pass client to handler with its thread
		}
	}

	fun broadcast(msg: String) {
		for (c in clients) {
			c.send(msg)
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
		
		synchronized(server) {
			server.clients += this
			server.onConnect(this)
		}
		
		try {
			while (active) {
				val data = reader.readLine()

				if (data == null) {
					active = false
					break
				}

				synchronized (server) {
					server.onMessage(data, this)
				}

			}
		}
		
		catch (ex: IOException) {
			println("<$address> <${ex.message}>")
		}
		
		finally { close() }
	}
	
	fun send(msg: String) {
		writer.println(msg)
	}
	
	override fun close() {
		active = false
		reader.close()
		writer.close()
		socket.close()
		
		synchronized(server) {
			server.clients -= this
			server.onDisconnect(this)
		}
	}
	
	fun broadcast(msg: String) {
		synchronized(server) {
			for (c in server.clients) {
				if (c != this) c.send(msg)
			}
		}
	}
	
}