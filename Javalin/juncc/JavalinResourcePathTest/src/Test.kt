import io.javalin.Javalin

fun main(args: Array<String>) {
	
	Javalin.create()
			.enableStaticFiles("static")
			.start()
	
}