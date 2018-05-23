import java.lang.Math.random

typealias Layer = Array<Neuron>


class Neuron(private val index: Int, // kazdy ma vlastny index
			 private  val activationFunction: ActivationFunction,
			 outputCount: Int
) {
	
	var output = 1.0 // zo zaciatku je vysledok 1
	var memory = DoubleArray(outputCount, init = {random()}) // kazdy neuron ma taku pamat, s kolkymi neuronmi je prepojeny
	
	
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
	
}