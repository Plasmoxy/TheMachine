package io.github.plasmoxy.themachine.hellofxworld

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage


class App : Application() {

	lateinit var root : Parent
	lateinit var scene : Scene

	override fun start(stage: Stage) {

		root = FXMLLoader.load(javaClass.getResource("gui.fxml"))
		scene  = Scene(root)
		
		stage.title = "XDDD"
		stage.scene = scene
		stage.show()
	}
	
}

fun main(args: Array<String>) {
	Application.launch(App::class.java, *args)
}