import kotlin.math.tanh

class ActivationFunction {
	
	fun compute(t: Double) = tanh(t)
	
	fun derivative(t: Double) : Double {
		// original derivacia = 1 - tanh(x)^2
		return 1.0 - t*t // optimalizovana aproximovana derivacia
	}
	
}