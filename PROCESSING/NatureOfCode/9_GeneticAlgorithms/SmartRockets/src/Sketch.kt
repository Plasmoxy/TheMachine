import processing.core.PApplet
import processing.core.PVector

class Sketch : PApplet() {

	inner class RocketPopulation(val populationSize : Int, 
	                             val mutationRate : Float,
	                             val rocketLifetime : Int,
	                             val target : PVector) {
		
		var members = Array(populationSize) {
			Rocket(rocketLifetime)
		}
		
		var matingPool = ArrayList<Rocket>()
		var generation = 0
		
		fun generate() {

			// perform selection according to target fitness
			for (member in members) {
				member.fit(target)
			}
			
			
			
		}
		
	}
	
	inner class DNA(lifetime : Int) {
		var maxforce = 0.1f
		var genes = Array<PVector>(lifetime) {
			PVector.random2D().mult(random(0f, maxforce))
		}
	}
	
	inner class Rocket(val lifetime : Int) {
		var pos = PVector()
		var vel = PVector()
		var acc = PVector()
		
		var dna = DNA(lifetime)
		var fitness = 0f
		
		var geneCounter = 0
		
		fun applyForce(f : PVector) {
			acc.add(f)
		}
		
		fun update() {
			vel.add(acc)
			pos.add(vel)
			acc.mult(0f)
		}
		
		fun run() {
			applyForce(dna.genes[geneCounter])
			geneCounter++
			update()
		}
		
		fun fit(target : PVector) {
			var d = PVector.dist(pos, target)
			fitness = pow(1/d, 2f)
		}

		fun crossover(partner : DNA) : DNA {
			var child = DNA(lifetime)

			var midpoint = random(dna.genes.size.toFloat()).toInt()

			for ( i in dna.genes.indices ) {
				if (i > midpoint) 	child.genes[i] = dna.genes[i]
				else 				child.genes[i] = partner.genes[i]
			}

			return child
		}

		fun mutate(rate : Float) {
			for ( i in dna.genes.indices ) {
				if (random(1f) < rate) {
					dna.genes[i] = PVector.random2D().mult(random(0f, dna.maxforce))
				}
			}
		}
		
	}
	

	override fun settings() {
		size(400, 400)
	}

	override fun setup() {
		
	}

	override fun draw() {
		
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}