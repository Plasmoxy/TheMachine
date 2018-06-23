import io.javalin.Javalin
import j2html.TagCreator.*
import java.text.SimpleDateFormat
import java.util.*

object ResLoader {
	fun read(name: String) : String {
		return javaClass.getResource(name).readText()
	}
}

fun main(args: Array<String>) {
	
	val app = Javalin.start(80)
	
	app.get("/") {
		it.html(div(attrs(".maincontent"),
				
				span(attrs(".time"),
						SimpleDateFormat("HH:mm:ss").format(Date())
				),
				
				style(ResLoader.read("style.css"))
		).render())
	}
	
}