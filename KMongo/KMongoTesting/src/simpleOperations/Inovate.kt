package simpleOperations

import DemoMongoDB
import model.Human
import model.HumanInovated
import org.litote.kmongo.*

fun main(args: Array<String>) {

	val humansCollection = DemoMongoDB.getCollection<HumanInovated>("humans")
	val innovatedHumansCollection = DemoMongoDB.getCollection<HumanInovated>("inovatedHumans")
	
	val humansSet = humansCollection.find().toMutableSet()
	humansSet.forEach {
		println("Updating : $it")
		innovatedHumansCollection.updateOne(Human::name eq(it.name), it, upsert())
	}
}