import javafx.application.Application
import javafx.geometry.Pos
import tornadofx.*

class MainView : View() {
	
	val btn1 = button("Press") {
		minWidth = 100.0
		setOnAction {
			text = (Math.random()*10).toInt().toString()
		}
	}

	override val root = vbox {
		prefWidth = 300.0
		prefHeight = 200.0
		paddingAll = 30
		alignment = Pos.CENTER
		
		this += btn1
	}
	
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java)
}