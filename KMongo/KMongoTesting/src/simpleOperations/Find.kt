package simpleOperations

import DemoMongoDB
import model.Human
import org.litote.kmongo.*


fun main(args: Array<String>) {
	
	val humans = DemoMongoDB.getCollection<Human>("humans")
	val seb = humans.findOne(Human::name eq("Seb")) ?: throw Exception("no human with that name")
	seb.age += 1
	humans.updateOneById(seb._id, seb)
	
}