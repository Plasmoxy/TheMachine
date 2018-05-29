import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import tornadofx.*

class MainView : View() {

	val labelA = label("XD")
	
	val btnA = button("CLICK") {
		vboxConstraints { margin = Insets(20.0) }
		
		setOnAction { 
			labelA.text = Math.random().toString()
		}
	}
	
	override val root = vbox {
		prefWidth = 200.0
		prefHeight = 200.0
		
		paddingAll = 10
		alignment = Pos.CENTER
		
		style = "-fx-background-color: #444; "
		
		add(labelA)
		add(btnA)
	}
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java, *args)
}