package simpleOperations

import DemoMongoDB
import model.Human
import org.litote.kmongo.*

fun main(args: Array<String>) {

	
	val humans = DemoMongoDB.getCollection<Human>("humans")
	Thread.sleep(1000)
	
	print("Enter human name to create : ")
	val humanName = readLine() ?: throw Exception("Error reading line.")
	val available = humans.findOne(Human::name eq(humanName)) == null
	
	if (available) {
		
		val newHuman = Human(
				name = humanName,
				age = (Math.random()*70).toInt()
		)
		humans.insertOne(newHuman)
		println("Created human : $newHuman")
		
	} else {
		println("There is already a human with that name!")
		return
	}

}