open private class A(val property: String)
open private class B(prop: String) : A(prop)

fun main(args: Array<String>) {
	
	print("String : ")
	println("XD" == "XD")
	
	print("A Hello == A Boi :")
	println(A("Hello") == A("Boi"))
	
	print("A Hello == A Hello :")
	println(A("Hello") == A("Hello"))
	
	print("B Hello == A Hello :")
	println(B("Hello") == A("Hello"))
	
	val ref = A("XD")
	val ref2 = ref
	print("ref == ref2 :")
	println(ref == ref2)
}