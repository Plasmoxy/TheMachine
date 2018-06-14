package interfucc

class View {

	fun show() {
		println("View.show()");
	}

}

class Screen(private var view : View) {

	fun show() {
		view.show();
	}

}


fun main(args: Array<String>) {
	var v = View()
	var sc = Screen(v) // delegate to screen
	
	sc.show()
}