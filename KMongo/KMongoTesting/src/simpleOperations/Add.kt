package simpleOperations

import DemoMongoDB
import model.Human
import org.litote.kmongo.*

fun main(args: Array<String>) {

	val humans = DemoMongoDB.getCollection<Human>("humans")
	humans.insertOne(
			Human(
					"Seb",
					(Math.random()*76).toInt()
			)
	)

}