package customJavaIO

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream
import java.io.*

// filterinputstream is a decorator base
class KapsLokInputStream(istream: InputStream) : FilterInputStream(istream) {
	override fun read() = Character.toUpperCase(super.read())
}

fun main(args: Array<String>) {
	// DECORATE ALL THE STREAMS !
	val sysin = LineInputStream(KapsLokInputStream(BufferedInputStream(System.`in`)))
	
	while(true) {
		println(sysin.readLine())
	}
}