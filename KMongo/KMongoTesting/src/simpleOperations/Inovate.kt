package simpleOperations

import DemoMongoDB
import model.Human
import model.HumanInovated
import org.litote.kmongo.*

fun main(args: Array<String>) {
	
	println("Getting collections...")
	val humansCol = DemoMongoDB.getCollection<Human>("humans")
	val humansNewCol = DemoMongoDB.getCollection<HumanInovated>("humansNew")
	Thread.sleep(1000)
	
	println("Clearing newHumans collections")
	humansNewCol.deleteMany() // delet all
	
	println("Creating set from humans")
	// KMongo autocast find() -> Human
	val humansList = humansCol.find().toMutableList()
	
	println("Mapping humans to inovated humans")
	val newHumansList = humansList.map {
		HumanInovated(newId(), it.name, it.age)
	}
	
	println("Inserting inovated humans to newHumans")
	humansNewCol.insertMany(newHumansList)
	
}