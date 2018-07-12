
// actually nope.
// ducks but trying it with kotlin approach
package ducksKotlin

interface FlyBehavior { fun fly() }
interface QuackBehavior { fun quack() }
interface DisplayBehavior {
	val name: String
	fun display()
}

class FlyNoWay : FlyBehavior {
	override fun fly() {} // nope cant fly
}

class FlyWithWings : FlyBehavior {
	override fun fly() {
		println("   IM FLYING WITH WINGS !!")
	}
}

class FlyWithRocket : FlyBehavior {
	override fun fly() {
		println("   IM FLYINT WITTT ROKEKTTEZZZZ XDDDDDDD")
	}
}

class NoQuack : QuackBehavior {
	override fun quack() {} // nope cant quack
}

class Quack : QuackBehavior {
	override fun quack() {
		println("   QUACK QUACK !")
	}
}

class Squeak : QuackBehavior {
	override fun quack() {
		println("   SQUEAK SQUAEK !!")
	}
}

// name is val, Display delegate shouldnt change name by reference
open class GreetingDisplay(override val name: String) : DisplayBehavior {
	override fun display() {
		println("- GUTEN TAGG my name ist $name -")
	}
}

class RetardedDisplay(name: String) : GreetingDisplay(name) {
	override fun display() {
		super.display()
		println("   AND I AM RETARDED xDDD")
	}
}

// name can be changed by Duck
abstract class Duck(override var name: String,
                    var flyBehavior: FlyBehavior, // take behavior objects from constructor
                    var quackBehavior: QuackBehavior,
                    var displayBehavior: DisplayBehavior) :
		FlyBehavior by flyBehavior, // delegate the behaviors to duck and save references in object
		QuackBehavior by quackBehavior,
		DisplayBehavior by displayBehavior

class BasicDuck(name: String) : Duck(name, FlyWithWings(), Quack(), GreetingDisplay(name))
class RetardedDuck(name: String) : Duck(name, FlyNoWay(), NoQuack(), GreetingDisplay(name))

fun main(args: Array<String>) {
	val bd = BasicDuck("Jack")
	bd.display()
	bd.fly()
	bd.quack()
	
	val rd = RetardedDuck("Faggot")
	rd.display()
	rd.fly()
	bd.quack()
	
	
	rd.display()
	rd.name = "Faggotchanged"
	rd.display() // nope a mistake well perhaps i shall learn more about delegation
}