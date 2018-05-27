class Core(private val view : MainView) {
	
	var expression = ""
	
	fun update() {
		view.primaryText.text = expression
	}
	
	fun numBtn(n : Int) {
		expression += n
		update()
	}
	
	fun evaluate() {
		
	}
	
}