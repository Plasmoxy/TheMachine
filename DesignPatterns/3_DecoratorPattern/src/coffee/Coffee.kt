package coffee

// headfirst design patterns starbuzz coffeeshop example
// based on Strategy and Decorator patterns
// except i wrote it through Kotlin properties
// and i dont know how to use them so i cant add stuff xDDDDD
// edit: I love kotlin properties, makes getters and setters so ez and no bullshit code ^^

enum class BeverageSize { SMALL, BIG, GRAND }
data class Item(val name: String, val size: BeverageSize)

// ---- FUNCTIONS ----
private fun getItemPrice(i: Item)
		= itemPrices[i] ?: throw Exception("Beverage not in map dude")

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
abstract class CondimentDecorator(protected val beverage: Beverage) : Beverage() {
	
	// require condiment decorators to REIMPLEMENT properties ( should delegate them to beverage val )
	override abstract val description: String
	override abstract val name: String
	override abstract val size: BeverageSize
	override abstract val cost: Double
}

// ---- BEVERAGES ----

class Espresso(override val size: BeverageSize) : Beverage() {
	override val description = "Espresso - a good coffee very nice"
	override val name = "Espresso"
	override val cost: Double
		get() = getItemPrice(Item(name, size))
}

class HouseBlend(override val size: BeverageSize) : Beverage() {
	override val description = "House blend coffeee"
	override val name = "HouseBlend"
	override val cost: Double
		get() = getItemPrice(Item(name, size))
}

// ---- DECORATORS ----

// mocha decorator
class Mocha(beverage: Beverage) : CondimentDecorator(beverage) {
	
	private val condimentName = "Mocha" // just private, just for internal stuff and getting from map
	
	override val name get() = beverage.name // delegate name of beverage
	override val size get() = beverage.size // delegate
	
	// DELEGATE beverage properties to Mocha's properties and DECORATE ( append ) them with mocha custom properties
	override val description
		get() = beverage.description + ", with mocha"
	
	// alternative mocha costs for different beverages
	// CHAIN this decorator property to previous beverage delegate cost
	override val cost
		get() = beverage.cost + getItemPrice(Item(condimentName, beverage.size))
}

class Whip(beverage: Beverage) : CondimentDecorator(beverage) {
	
	private val condimentName = "Whip"
	
	override val name get() = beverage.name // delegate
	override val size get() = beverage.size
	
	override val description: String
		get() = beverage.description + ", with whip"
	
	// alternative whip costs for different beverages
	override val cost: Double
		get() = beverage.cost + getItemPrice(Item(condimentName, beverage.size))
}

