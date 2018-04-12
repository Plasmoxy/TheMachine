package io.github.plasmoxy.themachine.autistpad

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.stage.FileChooser
import java.io.File

typealias EXTFILTER = FileChooser.ExtensionFilter

class Controller {
	
	private lateinit var app : App
	private val fchoser = FileChooser()
	
	@FXML lateinit var maintext : TextArea

	// called after annotated fields are injected
	fun initialize() {
		
		
		fchoser.extensionFilters.addAll(
				EXTFILTER("BASIC OLD SCHUL TEXTZT FILE", "*.txt"),
				EXTFILTER("LITERALLY ANYTHING ( i dont care :))) )", "*.*")
		)
		
		println("CONTROLLER INITIALIZED")
	}
	
	fun link(a: App) {
		app = a
	}
	
	@FXML fun buttonClearPressed() {
		maintext.text = ""
	}
	
	@FXML fun buttonSavePressed() {
		try {
			var file : File = fchoser.showSaveDialog(app.stage)
			if (file != null) {
				file.writeText(maintext.text)
			}
		} catch (e : Exception) {
			println("file save cancelled")
		}
	}
	
	@FXML fun buttonOpenPressed() {
		try {
			var file : File = fchoser.showOpenDialog(app.stage)
			if (file != null) {
				maintext.text = file.readText()
			}
		} catch (e : Exception) {
			println("file open cancelled")
		}
	}
	
}