package model

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class Human(
		var name: String,
		var age: Int = 0,
		@BsonId val _id: Id<Human> = newId()
)