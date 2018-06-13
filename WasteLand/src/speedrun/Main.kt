package speedrun

import javafx.application.Application
import tornadofx.*

class MainView : View() {
	
	val btn = button("RANDOM") {
		
		setOnAction {
			text = Math.random().toString()
		}
		
	}
	
	override val root = borderpane {
		
		title = "random"
		prefWidth = 300.0
		prefHeight = 200.0
		
		center = btn
	}
	
}

class MainApp : App(MainView::class) {
	
}

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java)
}