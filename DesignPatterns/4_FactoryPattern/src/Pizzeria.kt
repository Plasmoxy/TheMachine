// simple pizzeria that uses Factory pattern by Plasmoxy

// --- pizza definitions ----

interface Pizza {
	val name: String
	fun prepare()
	fun bake()
	fun cut()
	fun box()
}

class CheesePizza : Pizza {
	override val name = "Cheese Pizza"
	
	override fun prepare() = println("a lot of cheese is put on the raw pizza")
	override fun bake() = println("a strong smell of cheese comes from the oven")
	override fun cut() = println("the knife slices through delicious hot cheese")
	override fun box() = println("the cook puts the cheese pizza in the box")
}

class VeganPizza : Pizza {
	override val name = "Vegan Pizza"
	
	override fun prepare() = println("the cook goes in the garden and collects a lot of non-meat and puts it on pizza")
	override fun bake() = println("the grass and vegetals start to burn in the oven, the cones even exploded")
	override fun cut() = println("cant cut this theres stone in it")
	override fun box() = println("the pizza is too disappointing so we send it to vegans")
}

// ---- pizza factory ---
// why factory ? -> factory can have MORE CLIENTS
// not only orderpizza but also i.e. fastfoodpizza, airplanepizza may require pizza creation
// instead of putting all that creation code inside each of those classes, we put it in separate factory object

// PLOT TWIST : this ist just simple factory, NOT FULL FACTORY DESIGN PATTERN !!!!

class SimplePizzaFactory {
	
	fun createPizza(type: String): Pizza = when(type) {
		"cheese" -> CheesePizza()
		"vegan" -> VeganPizza()
		else -> throw Exception("NO SUCH PIZZA DUDE")
	}
	
}

fun orderPizza(type: String): Pizza {
	
	// we DONT want this code to change !
	// -> we DO NOT determine type and instantiate it here...
	// we put the object creation code in FACTORY object
	// what orderpizza cares about is only how to PREPARE pizza and order it, not how the pizza was created
	
	
	val pizza = SimplePizzaFactory().createPizza(type)
	
	pizza.prepare()
	pizza.bake()
	pizza.cut()
	pizza.box()
	
	return pizza
	
}