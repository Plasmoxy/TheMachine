import com.mongodb.MongoCredential
import com.mongodb.ServerAddress
import org.litote.kmongo.KMongo
import java.io.FileReader
import java.util.*


class FuckException : Exception("Retarded themachine config file")

object TheConfig {
	
	val mongoDemoUsername: String
	val mongoDemoPassword: String
	
	init {
		val props = Properties().apply {
			load(FileReader("machine.properties"))
		}
		
		mongoDemoUsername = props.getProperty("mongo.demo.username") ?: throw FuckException()
		mongoDemoPassword = props.getProperty("mongo.demo.password") ?: throw FuckException()
		
	}
	
}

val DemoMongoDB = KMongo.createClient(
		ServerAddress("shardbytes.com", 27017),
		listOf<MongoCredential>(
				MongoCredential.createCredential(
						TheConfig.mongoDemoUsername,
						"admin",
						TheConfig.mongoDemoPassword.toCharArray()
				)
		)
		
).getDatabase("demo")