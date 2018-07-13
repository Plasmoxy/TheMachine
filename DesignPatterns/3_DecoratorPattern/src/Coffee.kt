// headfirst design patterns starbuzz coffeeshop example
// except i wrote it through Kotlin properties


// ---- BASE CLASSES ----

// base type class for all beverages with one property implemented ( logically "unknown" )
abstract class Beverage {
	open val description = "Unknown Beverage"
	abstract val cost: Double
}

// ??? alternative : property interface
interface IBeverage {
	val description: String
		get() = "Unknown Beverage"
	val cost: Double
}

// decorator base for condiments, IS A Beverage -> inheritance for type matching
abstract class CondimentDecorator : Beverage() {
	// require condiment decorators to REIMPLEMENT description property
	override abstract val description: String
}

// ---- BEVERAGES ----

class Espresso : Beverage() {
	override val description = "Espresso - a good coffee very nice"
	override val cost = 1.99
}

class HouseBlend : Beverage() {
	override val description = "House blend coffeee"
	override val cost = 0.89
}

// ---- DECORATORS ----

// mocha decorator
class Mocha(val beverage: Beverage) : CondimentDecorator() {
	// DELEGATE beverage properties to Mocha's properties and DECORATE ( append ) them with mocha custom properties
	override val description: String
		get() = beverage.description + ", with mocha"
	override val cost: Double
		get() = beverage.cost + 0.20
}

class Whip(val beverage: Beverage) : CondimentDecorator() {
	override val description: String
		get() = beverage.description + ", with whip"
	override val cost: Double
		get() = beverage.cost + 0.10
}

