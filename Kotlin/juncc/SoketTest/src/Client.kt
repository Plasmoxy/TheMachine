import java.io.BufferedReader
import java.io.Closeable
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(val serverAddress : String, serverPort : Int) : Closeable {

	val socket = Socket(serverAddress, serverPort)
	val writer = PrintWriter(socket.getOutputStream(), true)
	val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
	
	fun run() {
		writer.println("hello")
		writer.flush()
		close()
	}
	
	override fun close() {
		writer.close()
		reader.close()
		socket.close()
	}
	
}