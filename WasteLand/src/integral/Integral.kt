package integral

import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

fun integrate(f : (Double) -> Double, a : Double, b : Double, parts : Int) : Double  {
	var size = b - a
	var dx = size/parts
	
	var value = 0.0
	
	var i = a
	while (i <= b) {
		
		value += f(i) * dx
		
		i += dx
	}
	
	return value
}

fun integrate_round( f : (Double) -> Double, a : Double, b : Double, parts : Int ) : Double {
	return (integrate(f, a, b, parts)*1000).roundToInt()/1000.0
}

fun main(args: Array<String>) {
	
	// circle formula x^2 + y^2 = r^2 ( im taking only the upper circle with radius 1 )
	// so y = sqrt(1 - x^2)
	var circle = { x : Double -> sqrt(1 - x.pow(2)) }
	
	// derivative of that circle
	var circle_d = { x : Double -> -x/sqrt(1 - x.pow(2)) }
	
	// arc length : L(f) = integral sqrt(1 + (df/dx)^2) dx
	var arc = { x : Double -> sqrt(1 + (-x/sqrt(1 - x.pow(2))).pow(2) ) }
	
	// integrate the arc length function
	println( integrate(arc, 0.0, 1.0, 10000) )
	
}