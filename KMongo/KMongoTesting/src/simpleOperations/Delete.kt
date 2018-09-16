package simpleOperations

import DemoMongoDB
import model.Human
import org.litote.kmongo.*

fun main(args: Array<String>) {

	val result = DemoMongoDB.getCollection("humans").deleteMany(Human::name eq("Ján"))
	println(result.deletedCount)

}