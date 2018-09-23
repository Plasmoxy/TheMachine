import java.security.SecureRandom
import java.util.*

fun main(args: Array<String>) {
	
	val secureRandom = SecureRandom()
	
	val bytes = ByteArray(64)
	secureRandom.nextBytes(bytes)
	val base = String(Base64.getEncoder().encode(bytes))
	println(base)
	
}
