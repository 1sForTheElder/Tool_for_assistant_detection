package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class whole_Probability_AnchorPane extends AnchorPane {
	Button generate = new Button("Compute whole Probability");
	Label information = new Label();
	public whole_Probability_AnchorPane(){
		this.getChildren().add(generate);
		this.getChildren().add(information);
		this.generate.setLayoutX(20);
		this.generate.setLayoutY(4);
		this.information.setLayoutX(230);
		this.information.setLayoutY(5);
		
	}
	
}
