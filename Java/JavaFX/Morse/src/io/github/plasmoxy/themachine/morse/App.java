package io.github.plasmoxy.themachine.morse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		
		stage.setMinWidth(800);
		stage.setMinHeight(500);
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
