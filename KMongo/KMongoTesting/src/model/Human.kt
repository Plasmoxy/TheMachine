package model

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class Human(
		val name: String,
		val age: Int = 0) {
}