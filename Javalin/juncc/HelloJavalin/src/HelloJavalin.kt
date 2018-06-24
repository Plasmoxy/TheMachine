import io.javalin.Javalin
import j2html.TagCreator.*
import java.text.SimpleDateFormat
import java.util.Date

object ResLoader {
	fun read(name: String) : String {
		return javaClass.getResource(name).readText()
	}
}

fun main(args: Array<String>) {
	
	val app = Javalin.create()
			.port(getHerokuAssignedPort())
			.start()
	
	app.get("/") {
		it.html(div(attrs(".maincontent"),
				
				span(attrs(".time"),
						SimpleDateFormat("HH:mm:ss").format(Date())
				),
				
				style(ResLoader.read("style.css"))
		).render())
	}
	
}

private fun getHerokuAssignedPort(): Int {
	val processBuilder = ProcessBuilder()
	return if (processBuilder.environment()["PORT"] != null) {
		Integer.parseInt(processBuilder.environment()["PORT"])
	} else 7000
}