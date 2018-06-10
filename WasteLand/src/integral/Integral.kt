package integral

import kotlin.math.PI
import kotlin.math.pow
import kotlin.math.sqrt

fun integrate_simple(f : (Double) -> Double, a : Double, b : Double, parts : Long) : Double  {
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

fun integrate_trapezoid(f : (Double) -> Double, start : Double, end : Double, parts : Long) : Double  {
	
	var percent = 0L
	
	// q = (b-a)/n
	val q = (end-start)/parts
	var sum = 0.0
	
	sum += q * f(start)/2.0
	sum += q * f(end)/2.0
	
	// for k in 1..(n-1)
	for (k in 1 until parts) {
		sum += q * f(start + k*q)
		
		val cpercent = k*100/parts
		if (cpercent > percent) {
			println("$percent %")
			percent = cpercent
		}
		
	}
	
	
	// return the sum which is the integral
	return sum
	
}


fun main(args: Array<String>) {
	
	// circle formula x^2 + y^2 = r^2 ( im taking only the upper circle with radius 1 )
	// so y = sqrt(1 - x^2)
	var circle = { x : Double -> sqrt(1 - x.pow(2)) }
	
	// derivative of that circle
	var circle_d = { x : Double -> -x/sqrt(1 - x.pow(2)) }
	
	// arc length : L(f) = integral sqrt(1 + (df/dx)^2) dx
	var arc = { x : Double -> sqrt(1 + (-x/sqrt(1 - x.pow(2))).pow(2) ) }
	
	// better arc ( simplified expresion )
	var betterarc = { x: Double -> sqrt(1.0/(1.0-x.pow(2.0))) }
	
	// integrate the arc length function, DOESNT WORK FOR ARC of circle as for infinity
	// println( 4*integrate_simple(circle, 0.0, 1.0, 10000) )
	 
	
	var my_PI = 4 * integrate_trapezoid( circle, 0.0, 1.0, 1_000_000_000 )
	println( "\nmy pi     = $my_PI\nnormal pi = ${PI}" )
	
}