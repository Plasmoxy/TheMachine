import io.javalin.Javalin
import io.javalin.embeddedserver.Location

fun main(args: Array<String>) {
	
	println("===== PHANTOM ======")

	val app = Javalin.create()
			.port(args[0].toInt())
			.enableStaticFiles("static", Location.EXTERNAL)
			.start()

	app.get("/") {
		it.html("XDD")
	}
	
	println(" ROUTING DONE ! ")

}