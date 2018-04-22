
class Sequence {
	
	var queue = mutableListOf<() -> Unit>()
	
	fun add(f: () -> Unit) = queue.add(f)

	fun execute() {
		for (f in queue) {
			f()
		}
	}
}

fun main(args: Array<String>) {
	var seq = Sequence()
	
	var a = 4
	var pa = {println(a)}
	
	
	seq.add(pa)
	seq.add {
		a += 5
		a += 4
	}
	seq.add(pa)
	
	seq.execute()
	
	
}