import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import javax.xml.bind.DatatypeConverter

fun main(args: Array<String>) {
	
	val digestor = MessageDigest.getInstance("SHA-256")
	
	val password = "koberec"
	val salt = String.format("%016x", Random().nextLong())
	
	val tohash = password + salt
	
	val bytehash = digestor.digest(tohash.toByteArray(StandardCharsets.UTF_8))
	val stringhash = DatatypeConverter.printHexBinary(bytehash).toLowerCase()
	
	println(
			"password : $password\n" +
			"salt : $salt\n" +
			"hash : $stringhash"
	)
	
}