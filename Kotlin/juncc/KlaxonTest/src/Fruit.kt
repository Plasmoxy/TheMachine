import com.beust.klaxon.Klaxon
import okhttp3.OkHttpClient
import okhttp3.Request

data class Fruit(val id: String, val price: Double, val amount: Long)

fun main(args: Array<String>) {

	val client = OkHttpClient()
	
	val fruitResponse = client.newCall(
			Request.Builder()
					.url("https://shardbytes.com/fruit")
					.build()
	).execute().body()?.string() ?: ""
	
	val klaxon = Klaxon()
	
	val fruits = klaxon.parseArray<Fruit>(fruitResponse) ?: listOf()
	
	for (f in fruits) {
		println("${f.id}\n" +
				"   price: ${f.price}€\n" +
				"   amount: ${f.amount}")
	}

}