// more convenient choices
fun <T, Q> Pair<T, Q>.`🅰️`() = first
fun <T, Q> Pair<T, Q>.`🅱`() = second



fun main(args: Array<String>) {

	val p = Pair(1, 2)
	println(p.first + p.second)
	// nope ->
	
	// this better
	println(p.`🅰️`() + p.`🅱`())
}