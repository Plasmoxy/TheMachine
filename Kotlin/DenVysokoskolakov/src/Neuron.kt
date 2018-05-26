// ucenie neuronu:
// kazdy si pamata gradient = uvedomenie si chyby
// g = gradient
// d = naucene skusenosti
// T = rychlost uenia / talent // patri <0, 1>
// K = na kolko sa spoliehame na predchadzajuce skusenosti a nie na vlastnu INTUICIU
// K patri <0, 1>
// r = spravny vysledok

// g = (r - o)tanh'(o)
// pre i = b, 0, 1, ... {
// 	d[i] = T*x[i]*g + K*d[i]
//	w[i] = w[i] + d[i]
// }

// v reale by mala byt T = 0.15 az 0.3 -> ucime sa relativne pomaly
// K => k=0 spolieha sa na intuiciu, k=1 spolieha sa na skusenosti
// malo by byt v 0.5


import java.lang.Math.random

typealias Layer = Array<Neuron>

// const = compile time constant !!
private const val LEARNING_RATE = 0.15 // = T
private const val EXPERIENCE_FACTOR = 0.5 // = K

class Neuron(private val index: Int, // kazdy ma vlastny index
			 private  val activationFunction: ActivationFunction,
			 outputCount: Int
) {
	
	var output = 1.0 // zo zaciatku je vysledok 1
	var gradient = 0.0 // mal by si pamatat gradient

	
	var memory = DoubleArray(outputCount, init = {random()}) // kazdy neuron ma taku pamat, s kolkymi neuronmi je prepojeny
	
	// kratkodoba pamat = skusenosti
	var experience = DoubleArray(outputCount, init = { 0.0 }) // na zaciatku nemaju zniadne skusenosti
	

	// mysliet dostane ako vstupny parameter predchadzajuci layer
	// t = b + x1w1 + x2w2 + ....
	// b = bias = svedomie
	fun think(previousLayer : Layer) {
		// (until) zoznam cisel od 0 do pl.size ~~ [0, 1] if size=2
		val t = (0 until previousLayer.size).sumByDouble { 
			previousLayer[it].output * previousLayer[it].memory[index]
		}
		
		output = activationFunction.compute(t)
	}
	
	//1.  neuron by mal vediet ucit sa z spravneho vysledku
	//2. mal by sa vediet aj ucit z dalsej vrstvy
	//3. mal by to vediet zapamatat si
	// UCENIE JE ZOSPODKU -> learn from next layer
	
	fun learnFromResult(correctResult: Double) {
		val delta = correctResult - output
		gradient = delta * activationFunction.derivative(output) // urci gradient
	}

	fun learnFromNextLayer(nextLayer: Layer) {
		// funguje podobne, len deltu vypocitame inak
		// lastindex = size-1
		val delta = (0 until nextLayer.lastIndex).sumByDouble { 
			memory[it] * nextLayer[it].gradient
		}
		
		// gradient ostava
		gradient = delta * activationFunction.derivative(output) // urci gradient
	}
	
	fun remember(previousLayer: Layer) {
		previousLayer.forEach { neuron ->
			val oldExperience = neuron.experience[index]
			val newExperience = LEARNING_RATE*neuron.output*gradient + EXPERIENCE_FACTOR*oldExperience
			
			neuron.memory[index] += newExperience
			neuron.experience[index] = newExperience // kratkodoba pamat
		}
	}
	
}