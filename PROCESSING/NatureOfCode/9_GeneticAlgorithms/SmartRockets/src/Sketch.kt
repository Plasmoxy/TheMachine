import processing.core.PApplet
import processing.core.PVector

class Sketch : PApplet() {

	inner class RocketPopulation(val initPos : PVector,
	                             val populationSize : Int,
	                             val mutationRate : Float,
	                             val rocketLifetime : Int,
	                             val target : PVector) {
		
		var members = Array(populationSize) {
			// ACHTUNG -> pos random
			Rocket(PVector(random(150f, 250f), random(150f, 250f)), rocketLifetime)
		}
		
		var matingPool = ArrayList<Rocket>()
		var generations = 0
		
		fun generate() {
			
			// perform selection according to target fitness
			for (member in members) {
				member.fit(target)
			}
			
			// select to mating pool
			matingPool.clear()
			for (member in members) {
				var n = (member.fitness * populationSize).toInt()

				for (j in 0 until n) {
					matingPool.add(member) // add references of member according to fitness
				}
			}

			// reproduce
			for ( i in members.indices ) {
				// reproduction
				// select indices
				var a = random(matingPool.size.toFloat()).toInt()
				var b = random(matingPool.size.toFloat()).toInt()

				// get parents
				// perhaps check if parents not same object ? ( gains not that much efficiency )
				var parentA = matingPool[a]
				var parentB = matingPool[b]

				var child = Rocket(initPos, rocketLifetime)
				child.dna = parentA.crossover(parentB.dna)
				
				// mutate child
				child.mutate(mutationRate)

				members[i] = child
			}
			
			generations++
			
		}
		
		fun live() {
			for (m in members) {
				m.run()
			}
		}
		
	}
	
	inner class DNA(lifetime : Int) {
		var maxforce = 0.1f
		var genes = Array<PVector>(lifetime) {
			PVector.random2D().mult(random(0f, maxforce))
		}
	}
	
	inner class Rocket(var pos : PVector,
	                   val lifetime : Int) {
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
		
		fun draw() {
			pushStyle()
			fill(0)
			ellipse(pos.x, pos.y, 2f, 2f)
			popStyle()
		}
		
		fun run() {
			applyForce(dna.genes[geneCounter])
			geneCounter++
			update()
		}
		
		fun fit(target : PVector) {
			var d = PVector.dist(pos, target)
			fitness = 1/d
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
	
	
	val LIFETIME = 200
	var lifeCounter = 0
	
	lateinit var population : RocketPopulation

	override fun settings() {
		size(400, 400)
	}

	override fun setup() {
		population = RocketPopulation(PVector(200f, 200f), 10, 0.01f, LIFETIME, PVector(100f, 100f))
	}

	override fun draw() {
		
		background(200)
		
		if (lifeCounter < LIFETIME ) {
			population.live()
			lifeCounter++
		} else {
			lifeCounter = 0
			population.generate()
		}
		
		for (rocket in population.members) {
			rocket.draw()
		}
		
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}