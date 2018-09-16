package simpleOperations

import DemoMongoDB
import com.mongodb.client.model.DropIndexOptions
import com.mongodb.client.model.IndexOptions
import model.Human
import org.litote.kmongo.*

fun main(args: Array<String>) {

	val humans = DemoMongoDB.getCollection<Human>("humans")
	
}