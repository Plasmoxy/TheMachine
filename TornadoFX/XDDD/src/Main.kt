import javafx.application.Application
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import tornadofx.App
import tornadofx.View

class MainView : View() {
	
	override val root : VBox by fxml()
	
	val znacka : Label by fxid()
	
	init {
		title = "AHOJ"
	}
	
	fun buttonPressed() {
		znacka.text = Math.random().toString()
	}
	
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java)
}