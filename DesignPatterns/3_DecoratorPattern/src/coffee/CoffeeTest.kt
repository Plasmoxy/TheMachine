package coffee

import kotlin.math.roundToInt

private fun displayBeverage(b: Beverage) {
	println("-- Beverage --\n" +
			"   description: ${b.description}\n" +
			"   cost: ${0.01 * ((b.cost*100).roundToInt())} €")
}

fun main(args: Array<String>) {
	
	var b1: Beverage = Espresso(BeverageSize.SMALL)
	displayBeverage(b1)
	
	b1 = Whip(Mocha(b1)) // add mocha and whip
	
	// !! code is programmed to INTERFACE, not IMPLEMENTATION ( to abstract class in this case )
	// even if the beverage is decorated, the decorators are transparent to the type
	displayBeverage(b1)
	
	
	var b2: Beverage = Whip(HouseBlend(BeverageSize.GRAND))
	displayBeverage(b2)
	
	// apparently kotlin autodelegates methods in Pair down to their subobjects
	// weird, it doesnt compare references but triggers a equals() chain reaction amazing !
	println(Pair("XD", 1) == Pair("XD", 1))
	println(Pair("XD", 1) == Pair("XDd", 1))
	
}