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
	
	lateinit var entities : Map<String, Mover>
	
	override fun settings() { size(400, 400) }

	override fun setup() {

		entities = hashMapOf(
				"m" to with(Mover(30f, 30f)) {
					vel.x = 1f
					vel.y = 1f
					return@with this
				},

				"cool falling ball with absolutely unnecessary long name" to with(Mover(50f ,50f)) {
					vel.y = 0.5f
					return@with this
				}
		)
	}

	override fun draw() {
		background(200)
		
		entities["m"]!!.vel.add(0.1f, 0.1f)
		
		for ( (_, e : Mover) in entities ) {
			e.update()
			e.display()
		}
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}