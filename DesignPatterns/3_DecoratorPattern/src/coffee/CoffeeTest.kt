package coffee
private fun displayBeverage(b: Beverage) {
	println("-- coffee.Beverage --\n" +
			"   description: ${b.description}\n" +
			"   cost: ${b.cost}")
}

fun main(args: Array<String>) {
	
	var b1: Beverage = Espresso()
	displayBeverage(b1)
	
	var b2: Beverage = HouseBlend()
	displayBeverage(b2)
	
	b1 = Whip(Mocha(b1)) // add mocha and whip
	
	// !! code is programmed to INTERFACE, not IMPLEMENTATION ( to abstract class in this case )
	// even if the beverage is decorated, the decorators are transparent to the type
	displayBeverage(b1)

}