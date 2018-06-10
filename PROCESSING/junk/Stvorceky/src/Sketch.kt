import processing.core.PApplet

class Sketch : PApplet() {

	var minSize = 1f
	var i = 0f
	
	var x = 0f
	var y = 0f
	
	val ITERATIONS = 50

	override fun settings() {
		fullScreen()
	}

	override fun setup() {
		frameRate(20f)
		noFill()
		
		x = width/2f
		y = height/2f
	}

	override fun draw() {
		if (i > ITERATIONS) return
		
		stroke(0, (i/ITERATIONS)*255)
		ellipse(x - 50 + i*5, y - 50 + i*5, minSize/2, minSize/2)
		
		minSize *= 1.1f
		i += 0.5f
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}