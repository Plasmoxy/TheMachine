import javafx.application.Application
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.App
import tornadofx.View

class MainView : View() {
	override val root : BorderPane by fxml("gui.fxml")
	
	init {
		with (currentStage!!) {
			isResizable = false
		}
		title = "uyy tornadofx"
	}
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java, *args)
}