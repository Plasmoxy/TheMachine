import java.util.*

class Core(private val view : MainView) {
	
	var expression = ""
	var result = 0.0
	
	init {
		update()
	}
	
	fun update() {
		view.primaryText.text = expression
		view.secondaryText.text = result.toString()
	}
	
	fun numBtn(n : Int) {
		expression += n
		update()
	}
	
	fun operatorBtn(op : Char) {
		expression += op
		update()
	}
	
	fun clear() {
		expression = ""
		update()
	}
	
	fun evaluate() {
		var data = HashMap<Int, String>()
		var index = 0
		
		for (c : Char in expression) {
			when (c) {
				'+' -> {
					index++
					data[index] = c.toString()
					index++
				}
				
				else -> {
					if (data[index] == null) data[index] = ""
					data[index] = data[index] + c
				}
			}
		}
		
		println(data)
	}
	
}