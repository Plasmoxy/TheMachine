package otherbulshit

import kotlin.properties.Delegates.observable

fun main(args: Array<String>) {
	
	var name : String by observable("<empty>") { prop, old, new ->
		println("[observable] name changed : $old -> $new")
	}
	
	name = "Seb"
	name = "John"
	
}