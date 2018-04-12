package io.github.plasmoxy.themachine.autistpad

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage


class App : Application() {

	lateinit var stage : Stage
	lateinit var root : Parent
	lateinit var scene : Scene
	lateinit var controller : Controller

	override fun start(stage: Stage) {
		
		this.stage = stage
		
		val loader = FXMLLoader(javaClass.getResource("gui.fxml"))
		root = loader.load()
		scene  = Scene(root)
		
		controller = loader.getController() as Controller
		controller.link(this)
		
		stage.title = "AutistPad v0.6.9"
		stage.scene = scene
		
		stage.icons.add(Image(javaClass.getResourceAsStream("besticonever.png")))
		
		stage.minWidth = 500.0
		stage.minHeight = 300.0
		stage.show()
		
	}
	
}

fun main(args: Array<String>) {
	Application.launch(App::class.java, *args)
}