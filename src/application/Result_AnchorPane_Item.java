package application;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class Result_AnchorPane_Item extends AnchorPane{
	public Label part_Name = new Label();
	public Label information = new Label();
	public MenuButton detail = new MenuButton("detail");
	public int similarity_counts = 0;
	Map<String,File> Map_name_to_file = new HashMap<String,File>();
	float similarity_prob = 0;
	int number_of_total_file = 0;
	public Result_AnchorPane_Item(int similarity_counts, int number_of_total_files,String part_name, Map<String,File> map){
		this.number_of_total_file = number_of_total_files;
		this.part_Name.setText(part_name);
		this.similarity_prob = (float)(Math.round((float)similarity_counts/number_of_total_files*10000))/100;
		this.information.setText("Similarities: "+similarity_counts+"  Part probability: "+similarity_prob+"%");
		this.information.setPrefHeight(25);
		this.information.setPrefWidth(325);
		this.information.setLayoutX(160);
		this.information.setLayoutY(4);
		this.detail.setLayoutX(430);
		this.detail.setLayoutY(4);
		this.part_Name.setPrefHeight(25);
		this.part_Name.setPrefWidth(150);
		this.part_Name.setLayoutX(50);
		this.part_Name.setLayoutY(4);
		this.getChildren().add(this.part_Name);
		this.getChildren().add(this.information);
		this.getChildren().add(this.detail);

	}
	public void Recount(int counts){
		
		float new_prob = (float)(Math.round((float)counts/this.number_of_total_file*10000))/100;
		this.information.setText("Similarities: "+counts+"  Part probability: "+new_prob+"%");
		this.similarity_prob = new_prob;
		System.out.println(counts);
	}
}
