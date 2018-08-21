import javafx.application.Application
import javafx.geometry.Pos
import javafx.scene.control.Alert
import javafx.stage.DirectoryChooser
import tornadofx.*
import java.io.File


class MainView : View() {
	
	val filez = observableList<File>()
	
	override val root = borderpane {
		
		title = "ListFiles"
		paddingAll = 10
		
		style {
			backgroundColor += c("#ccc")
		}
		
		center {
			listview(filez) {
				prefHeight = 300.0
				prefWidth = 300.0
				cellFormat {
					text = if (it.isDirectory) "${it.name}/" else it.name
				}
			}
		}
		
		top {
			hbox(10, Pos.CENTER) {
				paddingBottom = 10
				button("Open dir").action {
					val dir = DirectoryChooser().apply {
						title = "Open dir"
					}.showDialog(primaryStage)
					
					if (dir != null && dir.exists() && dir.isDirectory) {
						val all = dir.listFiles()
						filez.clear()
						filez.addAll(all.filter { it.isDirectory })
						filez.addAll(all.filter { !it.isDirectory })
					} else {
						alert(Alert.AlertType.ERROR, "No directory!")
					}
				}
			}
		}
		
	}
}

class MainApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(MainApp::class.java)
}