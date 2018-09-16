package simpleOperations

import DemoMongoDB
import com.mongodb.MongoWriteException
import model.Human
import org.litote.kmongo.*

fun main(args: Array<String>) {

	val humans = DemoMongoDB.getCollection<Human>("humans")
	
	try {
		humans.insertOne(
				Human(
						"Ján",
						(Math.random()*76).toInt()
				)
		)
	} catch(ex: MongoWriteException) {
		println("user with that index name already exists")
	}

}