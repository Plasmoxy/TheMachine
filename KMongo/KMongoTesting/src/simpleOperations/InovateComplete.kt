package simpleOperations

import DemoMongoDB
import model.HumanInovated
import org.litote.kmongo.*

fun main(args: Array<String>) {

	val inovated = DemoMongoDB.getCollection<HumanInovated>("inovatedHumans")
	val normal = DemoMongoDB.getCollection<HumanInovated>("humans")
	
	// delet all normal
	normal.deleteMany(EMPTY_BSON)
	
	// insert the inovated there
	normal.insertMany(inovated.find().toMutableList())
	
	// clean inovated
	inovated.deleteMany(EMPTY_BSON)

}