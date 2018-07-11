
// actually nope.
// ducks but trying it with kotlin approach
package ducksKotlin

interface FlyBehavior {
	fun fly() {
		println("FLY FLY !")
	}
}

class Duck(val name: String) {

	open fun display() {
		println("Hello im a duck with name $name")
	}

}

fun main(args: Array<String>) {

}