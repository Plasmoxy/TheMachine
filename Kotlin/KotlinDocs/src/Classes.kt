
private class Dog {
	
	val isDead get() = age >= 10
	
	// custom setter with backing field
	var age = 4
		set(a) { field = if (a > 10) 10 else a }
	
	fun print() {
		println("dog of age $age and is ${if (isDead) "not " else ""}alive.")
	}
}


private interface IFaceAlpha {
	
	fun hello() {
		println("HELLOO")
	}
	
	fun both() {
		println("both from alpha")
	}
}

interface IFaceBeta {
	
	fun hey() {
		println("HEYYYY")
	}
	
	fun both() {
		println("both from beta")
	}
	
}


private class Everything : IFaceAlpha, IFaceBeta {
	
	override fun both() {
		super<IFaceAlpha>.both()
		super<IFaceBeta>.both()
	}
	
}

fun main(args: Array<String>) {
	
	val t = Dog()
	t.print()
	t.age = 50
	t.print()
	
	val ever = Everything()
	ever.hello()
	ever.hey()
	ever.both()
	
	
}

enum class XD { A, B, C }
