package coffee

// headfirst design patterns starbuzz coffeeshop example
// except i wrote it through Kotlin properties
// and i dont know how to use them so i cant add stuff xDDDDD

// ---- UTILS ----
private fun getPriceFromMap(map: Map<Pair<String, BeverageSize>, Double>, name: String, size: BeverageSize)
		= map[Pair(name, size)] ?: throw Exception("Beverage not in map dude")

// ---- DATA ----
enum class BeverageSize { SMALL, BIG, GRAND }

// ---- BASE CLASSES ----

// base type class for all beverages with one property implemented ( logically "unknown" )
abstract class Beverage {
	open val description = "Unknown Beverage"
	open val name = "Unknown Name"
	abstract val cost: Double
	
	open val size: BeverageSize = BeverageSize.SMALL
}

// ??? alternative : property interface
interface IBeverage {
	val description: String
		get() = "Unknown coffee.Beverage"
	val cost: Double
}

// decorator base for condiments, IS A Beverage -> inheritance for type matching
abstract class CondimentDecorator : Beverage() {
	// require condiment decorators to REIMPLEMENT description property
	override abstract val description: String
	override abstract val name: String
}

// ---- BEVERAGES ----

class Espresso(override val size: BeverageSize) : Beverage() {
	override val description = "Espresso - a good coffee very nice"
	override val name = "Espresso"
	override val cost: Double
		get() = getPriceFromMap(beveragePrices, name, size)
}

class HouseBlend(override val size: BeverageSize) : Beverage() {
	override val description = "House blend coffeee"
	override val cost: Double
		get() = when(size) {
			BeverageSize.SMALL -> 0.70
			BeverageSize.BIG -> 1.50
			BeverageSize.GRAND -> 3.00
		}
}

// ---- DECORATORS ----

// mocha decorator
class Mocha(val beverage: Beverage) : CondimentDecorator() {
	
	private val condimentName = "Mocha"
	
	// DELEGATE beverage properties to Mocha's properties and DECORATE ( append ) them with mocha custom properties
	override val description: String
		get() = beverage.description + ", with mocha"
	
	override val name: String get() = beverage.name // delegate
	
	
	// alternative mocha costs for different beverages
	override val cost: Double
		get() = beverage.cost + when(beverage.size) { // IMPORTANT: use beverage property rather than self properties !!!
			BeverageSize.SMALL -> 0.20
			BeverageSize.BIG -> 0.25
			BeverageSize.GRAND -> 0.40
		}
}

class Whip(val beverage: Beverage) : CondimentDecorator() {
	
	private val condimentName = "Whip"
	
	override val description: String
		get() = beverage.description + ", with whip"
	
	override val name: String get() = beverage.name // delegate
	
	// alternative whip costs for different beverages
	override val cost: Double
		get() = beverage.cost + when (beverage.size) {
			BeverageSize.SMALL -> 0.10
			BeverageSize.BIG -> 0.20
			BeverageSize.GRAND -> 0.50
		}
}

