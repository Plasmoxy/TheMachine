import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class Client(val serverAddress : String, serverPort : Int) {

	val socket = Socket(serverAddress, serverPort)
	val writer = PrintWriter(socket.getOutputStream())
	val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
	
	fun run() {
		writer.println("hello")
		writer.flush()
		shutdown()
	}
	
	fun shutdown() {
		writer.close()
		reader.close()
		socket.close()
	}
	
}