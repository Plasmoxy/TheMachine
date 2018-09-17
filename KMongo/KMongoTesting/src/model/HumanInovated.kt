package model

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id
import org.litote.kmongo.newId

data class HumanInovated(
		@BsonId val _id: Id<Human> = newId(),
		var name: String,
		var age: Int = 0,
		val className: String = ""
)