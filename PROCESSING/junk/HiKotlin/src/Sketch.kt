import processing.core.PApplet
import processing.core.PVector

class Sketch : PApplet() {

	open inner class Ball(x : Float, y : Float) {
		var pos = PVector(x, y)

		fun display() {
			pushMatrix()
			pushStyle()
				fill(0)
				noStroke()
				translate(pos.x, pos.y)
				ellipse(0f, 0f, 20f, 20f)
			popStyle()
			popMatrix()
		}
	}
	
	open inner class Mover(x : Float, y : Float) : Ball(x, y) {
		
		var vel = PVector()
		
		fun update() {
			pos.add(vel)
		}
	}
	
	var m = Mover(30f, 30f)

	override fun settings() { size(400, 400) }

	override fun setup() {
		m.vel.x = 1f
	}

	override fun draw() {
		background(200)
		
		m.update()
		m.display()
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}