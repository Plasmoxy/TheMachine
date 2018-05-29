import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import tornadofx.*

class MainView : View() {

	val labelA = label("XD")
	
	val btnA = button("Random") {
		vboxConstraints { margin = Insets(20.0) }
		
		setOnAction { 
			labelA.text = Math.random().toString()
		}
	}
	
	override val root = vbox {
		prefWidth = 300.0
		prefHeight = 150.0
		
		alignment = Pos.CENTER
		
		title = "random"
		
		add(labelA)
		add(btnA)
	}
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java, *args)
}