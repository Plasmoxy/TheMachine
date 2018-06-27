import kotlin.properties.Delegates.observable

fun main(args: Array<String>) {
	
	for ( i in 0..10 step 2) print("$i ")
	println()
	
	var numbers = listOf(1, 3, 2, 6, 5)
	var evens = numbers.filter { x -> x%2==0 }
	var odds = numbers.filter { x -> x%2>0 }
	
	println("numbers : $numbers")
	println("evens : $evens")
	println("odds : $odds")
	
	
	var name : String by observable("") { _, _, x -> println("The name is $x") }
	name = "Seb"
	
}