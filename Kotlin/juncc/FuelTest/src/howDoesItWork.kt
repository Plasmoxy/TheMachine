import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.httpGet

class WrongVersionException : Exception("Wrong version, update app !")
class WrongAuthException : Exception("User unauthenticated, need to relogin!")


/**
 * Exception that holds status code and response string.
 */
class StatusException(val status: Int, val body: String): Exception("Http Exception : [$status] -> $body")

/**
 * Custom fuel handling with status codes.
 */
@Throws(StatusException::class,
		WrongAuthException::class,
		Exception::class
) fun statusFuel(request: Request): String {
	val (_, res, result) = request.responseString()
	val resdata = res.data.toString(Charsets.UTF_8)
	val s = res.statusCode
	
	if (s == 200)
		return resdata
	else if (s == 401)
		throw WrongAuthException()
	else if (s < 0)
		throw result.component2()!!.exception
	else
		throw StatusException(s, resdata)
}

fun main(args: Array<String>) {

	try {
		val data = statusFuel("https://httpstat.us/401".httpGet())
		println(data)
	} catch (e: StatusException) {
		println(e)
	}
	
}