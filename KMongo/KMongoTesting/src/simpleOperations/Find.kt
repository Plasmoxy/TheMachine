package simpleOperations

import DemoMongoDB
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import model.Human
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.toId


fun main(args: Array<String>) {
	
	val objs = DemoMongoDB.getCollection<Human>("humans")
	
	
}