

class Klasssssssss {
	
	val list = listOf(1, 2, 3, 4)
	
	fun funnny(param : Int) {
		println(param)
	}
	
	fun xd() {
		list.forEach(::funnny)
	}
	
}


fun main(args: Array<String>) {
	var k = Klasssssssss()
	
	with (k) {
		xd()
	}
	
	k.apply { 
		xd()
	}
	
	k.let {
		
	}
	
}