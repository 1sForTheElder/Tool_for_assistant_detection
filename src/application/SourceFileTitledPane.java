package application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;

public class SourceFileTitledPane {
	Color c;
	String inner_color_name;
	String title_name;
	TitledPane innerPane;
	HTMLEditor Editor = new HTMLEditor(); 
	AnchorPane AP = new AnchorPane(); 
	CheckBox checkBox = new CheckBox();
	Button btn_delete = new Button("Edit selection(Delete)");
	CheckBox checkBox_Top = new CheckBox();
	public SourceFileTitledPane(String title_name,Color c,String inner_color_name, int resolution_mode){
		this.inner_color_name = inner_color_name;
		this.title_name = title_name;
		this.c = c;
		this.deleteBars(Editor);
		Editor.setPrefSize(300, 220);
		Editor.setLayoutX(14);
		Editor.setLayoutY(20);
		this.checkBox.setLayoutX(240);
		this.checkBox.setLayoutY(250);
		this.checkBox.setText("Include");
		this.btn_delete.setLayoutX(35);
		this.btn_delete.setLayoutY(247);


		AP.getChildren().add(Editor);
		AP.getChildren().add(checkBox);
		AP.getChildren().add(btn_delete);
		this.innerPane = new TitledPane(title_name,AP);
		this.innerPane.setGraphic(checkBox_Top);
		this.innerPane.setId(title_name);
		if(resolution_mode==0){
			this.checkBox.setLayoutY(270);
			this.btn_delete.setLayoutY(267);
		}
		if(resolution_mode==1){
			Editor.setPrefSize(300, 140);
			this.checkBox.setLayoutY(170);
			this.btn_delete.setLayoutY(167);
			this.innerPane.setPrefHeight(150);
			this.innerPane.setMaxHeight(150);
		}
		this.checkBox.selectedProperty().bindBidirectional(this.checkBox_Top.selectedProperty());
		
	}

	
	
    public void deleteBars(HTMLEditor ThehtmlEditor){
        for(Node node: ThehtmlEditor.lookupAll(".tool-bar")){   	    		  
            node.setManaged(false);
            node.setDisable(true);
            node.setVisible(false);       	     	    	  
        };
    }
    
    public void setEditorText(String text,String color){
    	this.Editor.setHtmlText(text);
    }
}
