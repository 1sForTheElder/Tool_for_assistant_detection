package application;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class GridPaneItem extends AnchorPane{
	
	Label lbl_file_name = new Label();
	CheckBox cb = new CheckBox();
	String file_Name;
	public GridPaneItem(String file_Name){
		this.cb.setSelected(true);
		this.getChildren().add(lbl_file_name);
		this.getChildren().add(cb);
		this.lbl_file_name.setText(file_Name);
		this.cb.setLayoutX(180);
		this.cb.setLayoutY(8);
		this.cb.setText("");
		this.lbl_file_name.setLayoutX(15);
		this.lbl_file_name.setLayoutY(5);
		this.lbl_file_name.setPrefSize(150, 23);
		this.file_Name = file_Name;
		
	}
	
	public void checkBoxTrue(){
		this.cb.setSelected(true);
	}
	public void checkBoxFalse(){
		this.cb.setSelected(false);
	}
	public void ToSearch(String s){
		System.out.println(this.lbl_file_name.getText().equals(s));
		if(this.lbl_file_name.getText().contains(s)){
			this.lbl_file_name.setStyle("-fx-background-color:GoldenRod");
		}

	}
	public void cleanSearch(){
		this.lbl_file_name.setStyle("-fx-background-color:none");
	}
}
