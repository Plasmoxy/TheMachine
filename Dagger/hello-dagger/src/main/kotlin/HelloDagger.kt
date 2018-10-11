import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


data class NumberHolder(var number: Double) {
	init {
		println("NumberHolder instantiated ($number)")
	}
}

class Boi @Inject constructor() { val st = "hexe"}

@Singleton @Component(modules = [NumberModule::class])
interface NumberComponent {
	fun inject(o: Printer)
}


@Module class NumberModule {
	
	init { println("NumberModule instantiated") }
	
	@Provides @Singleton fun providesNumberHolder(b: Boi) = NumberHolder(Math.random())
	
}

class Printer(private val comp: NumberComponent) {
	
	@Inject lateinit var obj: NumberHolder
	
	init {
		println("Printer instantiated")
		comp.inject(this)
	}
	
	fun print() {
		println(obj.number)
	}

}


fun main(args: Array<String>) {
	println("main started")
	
	val comp = DaggerNumberComponent.create()
	println("dagger component created")
	
	Printer(comp).print()
	Printer(comp).print()
}