import javafx.application.Application
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import tornadofx.App
import tornadofx.View

class MainView : View() {
	override val root : BorderPane by fxml()
	
	val core = Core(this)
	
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
	
	fun btnEqualsPressed() = core.evaluate()
	
	init {
		with (currentStage!!) {
			isResizable = false
		}
		title = "TornadoCalc"
	}
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java, *args)
}