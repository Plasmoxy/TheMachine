import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.core.HttpException
import com.github.kittinunf.fuel.httpGet

data class Fruit(val id: String, val price: Double, val amount: Long)

fun getFruit(): List<Fruit>? {
	val (data, error) = "https://sharddbytes.com/fruit".httpGet()
			.responseString().third
	
	error?.apply {
		if (exception is HttpException) {
			println("Http Error : ${response.responseMessage}")
		} else {
			println("Uknown error : ${message}")
		}
		return null
	}
	
	return Klaxon().parseArray(data ?: "[]")
}

fun main(args: Array<String>) {
	
	val fruit = getFruit()
	if (fruit == null) return // autocast to non-nullable
	
	println(fruit)
	
}