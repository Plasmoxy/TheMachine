import processing.core.PApplet
import java.util.*

// ultra retarded bruteforcer with random string

class Sketch : PApplet() {

	val rand = Random()
	val target = "cat"
	
	var active = true

	fun getRandomString(length : Int) : String {
		return String(CharArray(length) {
			'a' + rand.nextInt(26)
		})
	}

	override fun settings() {
		size(400, 400)
	}

	override fun setup() {
		fill(0)
		noStroke()
		frameRate(1000f)
	}

	override fun draw() {
		background(200)
		textSize(30f)
		
		val s = getRandomString(target.length)
		
		if (active) {
			if (s.equals(target)) active = false
			text("ACTIVE", 30f, 30f)
		}
		
		else {
			text("COMPLETE", 30f, 30f)
		}
		
		text(s, 30f, 100f)
		textSize(10f)
		text("also this is gonna never quess it so yor just wasting yor time", 30f, 150f)
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}