import javafx.application.Application
import javafx.fxml.FXML
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
	
	fun btn0Pressed() = core.numBtn(0)
	fun btn1Pressed() = core.numBtn(1)
	fun btn2Pressed() = core.numBtn(2)
	fun btn3Pressed() = core.numBtn(3)
	fun btn4Pressed() = core.numBtn(4)
	fun btn5Pressed() = core.numBtn(5)
	fun btn6Pressed() = core.numBtn(6)
	fun btn7Pressed() = core.numBtn(7)
	fun btn8Pressed() = core.numBtn(8)
	fun btn9Pressed() = core.numBtn(9)
	
	fun operatorPlusPressed() = core.operatorBtn('+')
	
	fun btnEqualsPressed() = core.evaluate()
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