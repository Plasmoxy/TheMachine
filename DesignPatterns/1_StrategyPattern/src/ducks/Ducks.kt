// STRATEGY DESIGN PATTERN here ....

package ducks
interface FlyBehavior { fun fly() }
interface QuackBehavior { fun quack() }


class FlyNoWay : FlyBehavior {
	override fun fly() {} // nope cant fly
}

class FlyWithWings : FlyBehavior {
	override fun fly() {
		println("IM FLYING WITH WINGS !!")
	}
}

class FlyWithRocket : FlyBehavior {
	override fun fly() {
		println("IM FLYINT WITTT ROKEKTTEZZZZ XDDDDDDD")
	}
}

class NoQuack : QuackBehavior {
	override fun quack() {} // nope cant quack
}

class Quack : QuackBehavior {
	override fun quack() {
		println("QUACK QUACK !")
	}
}

class Squeak : QuackBehavior {
	override fun quack() {
		println("SQUEAK SQUAEK !!")
	}
}

open class Duck(val name: String) {
	
	// fly and quack behaviors can change but dont have to be present, they VARY
	// -> we encapsulate them outside
	
	// then create delegation instances here in ducc
	var flyBehavior: FlyBehavior = FlyWithWings()
	var quackBehavior: QuackBehavior = Quack()
	
	// doesnt change, keep it
	fun swim() {
		println("$name is swimming")
	}
	
	// does change, ALL DUCKS so we run it through inheritance
	open fun display() {
		println("Hi my dude im a duck with name $name")
	}
	
	// here we DELEGATE methods from the interfaces to our class
	fun performQuack() {
		quackBehavior.quack()
	}
	
	fun performFly() {
		flyBehavior.fly()
	}
}

open class RedDuck(name: String) : Duck(name) {
	
	init {
		quackBehavior = NoQuack() // red duck is autist and cannot quack
	}
	
	override fun display() {
		println("boi im a REDD DUCC xDDDD name= $name")
	}
	
}

open class RetardedDuck(name: String) : Duck(name) {
	init {
		// this duck is totally retarded and cant do anything
		quackBehavior = NoQuack()
		flyBehavior = FlyNoWay()
	}
	
	override fun display() {
		println("XAXAAXAXAXA reatard DUCC xDDDD im $name xDDD")
	}
}

// hunters have a whistle that mimics duck quacking
// we can reuse the ducks.Quack here
class DuckWhistle {
	private val quackBehavior = Quack()
	
	fun whistle() {
		quackBehavior.quack()
	}
}


fun main(args: Array<String>) {
	val d: Duck = RedDuck("Jack") // polymorphism works
	d.display()
	d.performFly()
	d.performQuack()
	
	println("achtung : apply roket")
	d.flyBehavior = FlyWithRocket()
	d.performFly()
	
	DuckWhistle().whistle()
}