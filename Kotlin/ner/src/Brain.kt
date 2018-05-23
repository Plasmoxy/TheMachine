
typealias Network = Array<Layer>

// topologia znamena: rozlozenie neuronov
// kazdy prvok udava ze v ktorej vrstve je kolko neuronov
// napr [2, 3, 1] = topologia

class Brain(activationFunction: ActivationFunction,
			topology: IntArray) {
	
	private val network : Network
	
	init {
		val layerCount = topology.size // topoogia su layery
		
		// chceme initializovať kaŽdú vrstvu + BIAS neurony
		network = Network(size = layerCount, init = {
			val neuronCount = topology[it] + 1 // pocet neuronov + bias
			
			// chceme aby neuron bol prepojeny so vsetkymi len nie s biasom
			// posledny=bias uz nie je prepojeny s inymi neuronmi
			val outputCount = if (it == layerCount - 1) 0 else topology[it + 1]
			
			// return
			Layer(size = neuronCount, init = {
				Neuron(it, activationFunction, outputCount)
			})
		})
	}
	
}