import processing.core.PApplet

class Sketch : PApplet() {
	
	override fun settings() {
		size(400, 400)
	}
	
	
	inner class DNA {
		
		var genes = Array(18) {
			random(32f, 128f).toInt().toChar()
		}
		
		var fitness = 0f
		var mutationRate = 0.01f
		
		fun fit(target : String) {
			var score = 0
			
			for ((idx, gene) in genes.withIndex()) {
				if (gene == target[idx]) {
					score++
				}
			}
			fitness = score.toFloat()/target.length
		}
		
		fun crossover(partner : DNA) : DNA {
			var child = DNA()
			
			var midpoint = random(genes.size.toFloat()).toInt()
			
			for ( i in genes.indices ) {
				if (i > midpoint) child.genes[i] = genes[i]
				else child.genes[i] = partner.genes[i]
			}
			
			return child
		}
		
		fun mutate() {
			for ( i in genes.indices ) {
				if (random(1f) < mutationRate) {
					genes[i] = random(32f, 128f).toInt().toChar()
				}
			}
		}
	}
	
	var population = Array(100) { DNA() }
	
	var phrase = "cat"

	override fun setup() {
		
	}

	override fun draw() {
		
		// selection
		for (member in population) {
			member.fit(phrase)
		}
		
		// select to mating pool
		var matingPool = ArrayList<DNA>()
		for (member in population) {
			var n = (member.fitness * 100).toInt()
			
			for (j in 0 until n) {
				matingPool.add(member) // add references of member according to fitness
			}
		}
		
		// reproduction
		// select indices
		var a = random(matingPool.size.toFloat()).toInt()
		var b = random(matingPool.size.toFloat()).toInt()
		
		// get parents
		// perhaps check if parents not same object ? ( gains not that much efficiency )
		var parentA = matingPool[a]
		var parentB = matingPool[b]
		
		var child = parentA.crossover(parentB)
		child.mutate()
	}
	
}

fun main(args: Array<String>) {
	PApplet.main("Sketch")
}