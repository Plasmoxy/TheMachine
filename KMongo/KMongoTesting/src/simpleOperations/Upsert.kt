package simpleOperations

import DemoMongoDB
import com.mongodb.client.model.DropIndexOptions
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.UpdateOptions
import model.Human
import org.litote.kmongo.*

fun main(args: Array<String>) {

	val humansCollection = DemoMongoDB.getCollection<Human>("humans")
	
	val h = Human(newId(), "Sebag", 17)
	
	// upsert that human !
	val updateResult = humansCollection.updateOne(Human::name eq(h.name), h, upsert())
	println(updateResult)
	
	
}