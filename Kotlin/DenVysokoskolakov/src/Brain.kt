import kotlin.math.sqrt

typealias Network = Array<Layer>

// topologia znamena: rozlozenie neuronov
// kazdy prvok udava ze v ktorej vrstve je kolko neuronov
// napr [2, 3, 1] = topologia


// MOZOG
class Brain(activationFunction: ActivationFunction,
			topology: IntArray) {
	
	private val network : Network
	
	private var error = 0.0 // potrebuje si zapamatat chybu
	private var recentAverageError = 0.0 // priemerna chyba za niekolko predchadzajucich pokusov
	
	private val LEARNING_RATE = 0.001 // cely mozog sa uci strasne pomaly
	private val SMOOTHING_FACTOR = 100 // pouziva sa pri pocitani priemeru chyby
	
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
	
	fun learn(correctResults: DoubleArray) {
		// vypocitame chybu
		computeError(correctResults)
		computeRecentAverageError()
		
		// spatna propagacia
		teachOutputLayer(correctResults) // nauc vystupnu vrstvu
		teachOtherLayers() // nauc ostatne layere
		
		remember()
	}
	
	
	// WTFFFFFF
	private fun computeError(correctResults: DoubleArray) {
		val outputLayer = network.last()
		
		error = (0 until outputLayer.lastIndex).sumByDouble {
			val delta = correctResults[it] - outputLayer[it].output
			delta*delta // nepouzijeme derivaciu ale umocnime
		}
		
		error /= outputLayer.lastIndex
		error = sqrt(error)
		error += LEARNING_RATE * sqrt(network.sumByDouble { 
			it.sumByDouble { 
				it.memory.sumByDouble { 
					it*it
				}
			}
		})
	}
	
	private fun computeRecentAverageError() = (recentAverageError * SMOOTHING_FACTOR + error) / (SMOOTHING_FACTOR + 1.0)
	
	private fun teachOutputLayer(correctResults: DoubleArray) {
		val outputLayer = network.last()
		
		// bacha je tam aj BIAS
		(0 until outputLayer.lastIndex).forEach {
			outputLayer[it].learnFromResult(correctResults[it])
		}
	}
	
	private fun teachOtherLayers() {
		// iterujeme odzadu, nechcem iterovať cez input a output
		(network.lastIndex - 1 downTo 1).forEach {
			val layer = network[it]
			val nextLayer = network[it + 1]
			
			layer.forEach {
				it.learnFromNextLayer(nextLayer)
			}
		}
	}
	
	// TODO: skopcic
	private fun remember() {
		
		
		
	}
	
}