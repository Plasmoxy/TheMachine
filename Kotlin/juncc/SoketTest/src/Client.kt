import java.io.*
import java.net.Socket
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

class Client(val serverAddress : String, serverPort : Int) : Closeable {
	
	var socket: Socket

	init {
		println("<Connecting to $serverAddress:$serverPort>")
		socket = Socket(serverAddress, serverPort)
		println("<Connected>")
	}
	
	val writer = PrintWriter(socket.getOutputStream(), true)
	val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
	
	val active = AtomicBoolean(true)
	
	
	private fun socketChecked(f: () -> Unit) {
		try { f() }
		catch(ex: IOException) {
			println("<socket ex : ${ex.message}>")
		}
		finally { close() }
	}
	
	fun run() {

		thread {
			socketChecked {
				while(active.get()) {
					println(reader.readLine())
				}
			}
		}

		thread {
			socketChecked {
				while(active.get()) {
					
					val input = readLine()
					
					if (input == "/exit") {
						active.set(false)
						break
					}
					
					writer.println(input) // send stuff to server
				}
			}
		}
		
	}
	
	override fun close() {
		writer.close()
		reader.close()
		socket.close()
	}
	
}