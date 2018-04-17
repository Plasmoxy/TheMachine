import kotlin.math.pow

// mmm functionz testing

fun main(args: Array<String>) {
	
	// one line fun def
	fun a(x : Double) : Double = x.pow(2)
	println(a(2.0))
	
	// lambda
	var b = { x:Double -> x.pow(2) }
	println(b(2.0))
	
	// simple decorator
	fun decorator( f: () -> Any?) {
		println("=========")
		f()
		println("=========")
	}
	
	// decorator which provides parameter
	fun paramDecorator( f: (String) -> Any?) {
		decorator { 
			f("MESSAGE FROM DECORATOR")
		}
	}
	
	// decorator which prints return value
	fun returnDecorator( f: () -> String) {
		decorator {
			println(f())
		}
	}
	
	
	decorator {
		println("HEYY IM DECORATED")
	}
	
	paramDecorator { st ->
		println(st)
	}
	
	returnDecorator { "XD" }
	
	

}