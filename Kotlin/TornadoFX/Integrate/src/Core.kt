import tornadofx.plusAssign
import java.util.*

class Core(private val view : MainView) {
	
	fun updateResult(x: Double) {
		view.secondaryText.text = x.toString()
	}
	
	
	fun numBtn(n : Int) {
		view.primaryText.text += n
	}
	
	fun operatorBtn(op : Char) {
		view.primaryText.text += op
	}
	
	fun clear() {
		view.primaryText.text = ""
	}
	
	fun evaluate() {
		var data = HashMap<Int, String>()
		var index = 0
		
		// parse to indexmap
		for (c : Char in view.primaryText.text) {
			when (c) {
				'+','-','*','/' -> {
					index++
					data[index] = c.toString()
					index++
				}
				
				'0','1','2','3','4','5','6','7','8','9','.' -> {
					if (data[index] == null) data[index] = ""
					data[index] = data[index] + c
				}
				
				else -> {
					view.secondaryText.text = "INPUT ERROR"
					return
				}
			}
		}
		
		println(data)
	}
	
}