import java.io.*
import java.net.Socket
import kotlin.concurrent.thread

class Client(val serverAddress : String, serverPort : Int) : Closeable {

	val socket = Socket(serverAddress, serverPort)
	val writer = PrintWriter(socket.getOutputStream(), true)
	val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
	
	fun run() {

		thread {
			try {
				while(true) {
					println(reader.readLine())
				}
			}
			
			catch(ex: IOException) {
				println(ex.message)
			}
			
			close()
		}
		
		use {

			writer.println(readLine())
			
		}
	}
	
	override fun close() {
		writer.close()
		reader.close()
		socket.close()
	}
	
}