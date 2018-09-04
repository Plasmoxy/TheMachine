import io.javalin.Javalin
import io.javalin.embeddedserver.Location

class Core {
	
	val app: Javalin
	
	init {
		app = Javalin.create()
				.port(80)
				.enableStaticFiles("static", Location.EXTERNAL)
				.start()
		
		
	}
	
}