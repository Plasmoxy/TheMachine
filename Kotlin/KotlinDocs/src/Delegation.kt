import kotlin.properties.Delegates.observable

fun main(args: Array<String>) {
	
	var meno : String by observable("ziadne") { prop, oldval, newval ->
		println("meno sa zmenilo na $newval")
	}
	
	
	meno = "Seb"
	
}