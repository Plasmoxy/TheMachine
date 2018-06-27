import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread


class MainModel {
	
	var a = 0
	
	
	
}

class Server(val port: Int, var model: MainModel) {
	
	private val serverSocket = ServerSocket(port)
	var active = true
	
	init {
		println("[Server] created at port $port")
	}
	
	fun listen() {
		
		println("[Server] listening ...")
		
		while (active) {
			val client = serverSocket.accept()
			thread { ClientHandler(client, model).run() } // pass client to handler with its thread
		}
	}
	
}

class ClientHandler(private val client: Socket, private val model: MainModel) {
	
	private val reader = BufferedReader(InputStreamReader((client.getInputStream())))
	private val writer = PrintWriter(client.getOutputStream())
	
	private var active = true
	
	fun run() {

		println("[ClientHandler] ${client.inetAddress.hostAddress} opened connection")
		
		try {
			while (active) {
				val data = reader.readLine()
				
				when (data) {
					
					"hello" -> {
						println("------ CLIENT SAYS HELLO ---------")
					}
					
					"increment" -> {
						model
					}
					
					null -> {
						shutdown()
						active = false
					}
					
					
				}
				
			}
		}
		
		catch(ex: Exception) {
			ex.printStackTrace()
			shutdown()
		}
	}
	
	fun shutdown() {
		reader.close()
		writer.close()
		client.close()
		println("[ClientHandler] ${client.inetAddress.hostAddress} closed connection")
	}
	
}