import com.beust.klaxon.json

fun main(args: Array<String>) {
	
	val arr = json {
		listOf(
				listOf(1, 2),
				mapOf(
						"xd" to 3
				)
		)
	}
	
	println(arr.toString())
}
