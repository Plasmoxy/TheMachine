// Genetic algorithm that evolves to a given phrase
// by Plasmoxy
// thanks to Nature of Code

import processing.core.PApplet

class Sketch : PApplet() {
	
	override fun settings() {
		size(800, 500)
	}
	
	
	inner class DNA(val geneAmount : Int) {
		
		var genes = Array(geneAmount) {
			random(32f, 128f).toInt().toChar()
		}
		
		var fitness = 0f
		
		fun fit(target : String) {
			var score = 0 // fitness score of current DNA
			
			for ( i in genes.indices) {
				if (genes[i] == target[i]) {
					score++
				}
			}
			fitness = score.toFloat()/target.length
		}
		
		fun crossover(partner : DNA) : DNA {
			var child = DNA(geneAmount)
			
			var midpoint = random(genes.size.toFloat()).toInt()
			
			for ( i in genes.indices ) {
				if (i > midpoint) 	child.genes[i] = genes[i]
				else 				child.genes[i] = partner.genes[i]
			}
			
			return child
		}
		
		fun mutate(rate : Float) {
			for ( i in genes.indices ) {
				if (random(1f) < rate) {
					genes[i] = random(32f, 128f).toInt().toChar()
				}
			}
		}
		
		fun getPhenotype() : String {
			return genes.joinToString("")
		}
	}
	
	inner class GeneticPopulation( val target : String,
							val populationSize : Int,
							val mutationRate : Float) {
		
		// number of generations
		var generations = 1
		
		// create a population of members with random dna
		var members = Array(populationSize) { DNA(target.length) }
		
		// jump to new generation
		fun generate() {

			// perform selection according to target fitness
			for (member in members) {
				member.fit(target)
			}

			// select to mating pool
			var matingPool = ArrayList<DNA>()
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

				var child = parentA.crossover(parentB)
				child.mutate(mutationRate)

				members[i] = child
			}
			
			
			generations++
			
		}
		
		fun checkPhenotype(targetPhenotype : String) : String? {
			
			// scan the entire population and check for phenotype
			for ( m in members ) {
				val phen = m.getPhenotype()
				if (phen == targetPhenotype) {
					return phen
				}
			}
			
			return null
		}
		
	}
	
	
	// custom vars
	lateinit var population : GeneticPopulation
	val phrase = "the password is hidden here"
	var active = true
	
	override fun setup() {
		surface.setTitle("Genetic algorithm phrase fitter by Plasmoxy")
		//frameRate(1000f)
		population = GeneticPopulation(phrase, 1000, 0.01f)
	}

	override fun draw() {
		if (!active) return

		background(20)
		
		
		population.generate()
		
		var ph = population.members[0].getPhenotype()
		
		textSize(30f)
		fill(255)
		text("Current first phenotype :\n$ph", 20f, 50f)
		
		textSize(20f)
		text("generations = ${population.generations}\n" +
				"population size = ${population.populationSize}\n" +
				"mutation rate = ${population.mutationRate}"
				,20f, 150f
		)
		
		val checkedPhenotype = population.checkPhenotype(phrase)
		if ( checkedPhenotype != null) {
			textSize(30f)
			fill(0f, 255f, 0f)
			text("TARGET FOUND : $checkedPhenotype", 20f, 300f)
			active = false
		}
		
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}