package coffee

import kotlin.math.roundToInt

private fun displayBeverage(b: Beverage) {
	println("-- Beverage --\n" +
			"   description: ${b.description}\n" +
			"   size: ${b.size}\n" +
			"   cost: ${((b.cost*100).roundToInt())/100.0} €")
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
	
	// THEORY: weird, it doesnt compare references but triggers a equals() chain reaction amazing !
	// EDIT: it DOES compare references apparently BUT if the equal chain is fully true then the objects are same ( because of equals operator override )
	// RESULT -> YES, Pair is a DATA class, the equals methods are autorouted and these pairs are equal
	println(Pair("XD", 1) == Pair("XD", 1))
	println(Pair("XD", 1) == Pair("XDd", 1))
	
}