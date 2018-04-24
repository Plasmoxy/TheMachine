/*
 * Morse code parser fx app by Plasmoxy
 */

package io.github.plasmoxy.themachine.morse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
	
	public Stage stage;
	public Parent root;
	public Scene scene;
	public Controller controller;
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
		root = loader.load();
		
		scene = new Scene(root);
		
		controller = loader.getController();
		controller.link(this);
		
		stage.setScene(scene);
		
		stage.setTitle("MORZEOWGA KOVERTOR v1.2.3 | by Plasmoxy");
		
		stage.setMinWidth(800);
		stage.setMinHeight(500);
		
		stage.getIcons().add(new Image(getClass().getResourceAsStream("vor.png")));
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
