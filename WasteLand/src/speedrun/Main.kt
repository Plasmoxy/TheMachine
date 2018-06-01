package speedrun
import javafx.application.Application
import tornadofx.*

class MainView : View() {
	
	val btn = button("CLICC") {
		
		prefWidth = 100.0
		
		setOnAction {
			text = Math.random().toString()
		}
		
	}
	
	override val root = borderpane {

		title = "Random"

		prefHeight = 200.0
		prefWidth = 300.0

		center = btn
	}

}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java)
}