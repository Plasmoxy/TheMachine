package io.github.plasmoxy.themachine.morse;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Controller {
	
	private App app;
	private MorseParser mparser = MorseParser.getInstance();
	
	
	// FIELDS : FXML
	@FXML TextArea normalText;
	@FXML TextArea morseText;
	
	public void link(App a) {
		app = a;
	}
	
	@FXML protected void toMorseAction() {

		morseText.setText(mparser.toMorse(normalText.getText()));
		
	}

	@FXML protected void toTextAction() {

		normalText.setText(mparser.toText(morseText.getText()));

	}
	
}
