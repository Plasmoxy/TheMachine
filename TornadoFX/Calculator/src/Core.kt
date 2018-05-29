import java.util.*

class Core(private val view : MainView) {
	
	fun primaryAppend(s: String) {
		view.primaryText.text += s
	}
	
	fun clear() {
		view.primaryText.text = ""
		view.secondaryText.text = ""
	}
	
	fun evaluate() {
		val expression = view.primaryText.text.replace(" ", "")
		
		var expr = ArrayList<String>() // expression data
		var index = 0
		
		expr.add("") // load first
		
		// parse to list
		for (c : Char in expression) {
			when (c) {
				'+','-','*','/' -> {
					index++
					expr.add(c.toString()) // apply operator
					index++
					expr.add("") // load new string
				}
				
				'0','1','2','3','4','5','6','7','8','9','.' -> {
					expr[index] = expr[index] + c
				}
				
				else -> {
					view.secondaryText.text = "INPUT ERROR"
					return
				}
			}
		}
		
		print(expr)
		
		// smart iteration from back so operator operations won't have impact on our index
		// first * and /
		for (i in expr.lastIndex downTo 0) {
			when (expr[i]) {
				"*" -> applyOperator(expr, '*', i)
				"/" -> applyOperator(expr, '/', i)
			}
		}

		// repeat for + -
		for (i in expr.lastIndex downTo 0) {
			when (expr[i]) {
				"+" -> applyOperator(expr, '+', i)
				"-" -> applyOperator(expr, '-', i)
			}
		}
		
		// show result
		view.secondaryText.text = expr[0]
		view.secondaryText.positionCaret(9)
		
		println(" -> $expr")
	}
	
	private fun applyOperator(expr: ArrayList<String>, op: Char, pos: Int) {
		expr.removeAt(pos) // pos is now second operand

		// compute to first operand
		try {
			val A = expr[pos-1].toDouble()
			val B = expr[pos].toDouble()
			when (op) {
				
				'+' -> {
					expr[pos-1] = (A + B).toString()
				}
				
				'-' -> {
					expr[pos-1] = (A - B).toString()
				}

				'*' -> {
					expr[pos-1] = (A * B).toString()
				}

				'/' -> {
					expr[pos-1] = (A / B).toString()
				}
			}
		}
		
		catch (e: NumberFormatException) {
			view.secondaryText.text = "Parse error"
			return
		}
		
		
		// remove the second operand
		expr.removeAt(pos)
	}
	
}