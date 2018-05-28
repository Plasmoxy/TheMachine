import javafx.application.Application
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.App
import tornadofx.View

class MainView : View() {
	
	val core : Core
	
	// fxml
	override val root : BorderPane by fxml()
	val primaryText : TextField by fxid()
	val secondaryText : TextField by fxid()
	
	// fxml event routing, tornado will reflect these functions
	fun btn0Pressed() = core.primaryAppend("0")
	fun btn1Pressed() = core.primaryAppend("1")
	fun btn2Pressed() = core.primaryAppend("2")
	fun btn3Pressed() = core.primaryAppend("3")
	fun btn4Pressed() = core.primaryAppend("4")
	fun btn5Pressed() = core.primaryAppend("5")
	fun btn6Pressed() = core.primaryAppend("6")
	fun btn7Pressed() = core.primaryAppend("7")
	fun btn8Pressed() = core.primaryAppend("8")
	fun btn9Pressed() = core.primaryAppend("9")
	fun btnDotPressed() = core.primaryAppend(".")
	
	fun operatorPlusPressed() = core.primaryAppend("+")
	
	fun evaluateAction() = core.evaluate()
	fun btnClearPressed() = core.clear()
	
	init {
		with (currentStage!!) {
			isResizable = false
		}
		title = "TornadoCalc"
		
		core = Core(this)
	}
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java, *args)
}