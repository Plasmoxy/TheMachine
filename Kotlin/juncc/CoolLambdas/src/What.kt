
// create custom if lambda function
fun myif(expr : Boolean, fn : () -> Any?) {
	if (expr) fn()
}

fun main(args: Array<String>) {
	
	myif(2 > 1, {
		println("HEHE")
	})
	
	// omit parameter as last lambda
	myif(2 > 1) {
		println("XD")
	}
}