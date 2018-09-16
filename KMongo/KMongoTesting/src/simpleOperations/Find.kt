package simpleOperations

import DemoMongoDB
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import model.Human
import org.litote.kmongo.getCollection


fun main(args: Array<String>) {
	
	val objs = DemoMongoDB.getCollection<Human>("humans")

	val hoomans = objs.find()
	
	try {
		hoomans.toMutableList()
	} catch(ex: MissingKotlinParameterException) {
		println("ERROR CONSTRUCTING KOTLIN OBJECTS")
	}
	
	
	
	
}