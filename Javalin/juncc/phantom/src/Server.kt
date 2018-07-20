import io.javalin.Javalin

fun main(args: Array<String>) {

	val app = Javalin.create()
			.port(args[0].toInt())
			.start()

	app.get("/") {
		it.html("XDD")
	}

}