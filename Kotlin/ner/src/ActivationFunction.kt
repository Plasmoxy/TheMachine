import kotlin.math.tanh

class ActivationFunction {
	
	fun compute(t: Double) : Double {
		return tanh(t)
	}
	
}