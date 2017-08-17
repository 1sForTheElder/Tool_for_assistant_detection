package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;

public class Report_Menu_Item extends MenuItem {
	File file;
	String name;
	CheckBox checkBox = new CheckBox();
	public Report_Menu_Item(String name,File file){
		super();
		this.checkBox.setSelected(true);
		this.file = file;
		this.name = name;
		this.setGraphic(checkBox);
		
		this.setText(name);
		this.setOnAction(evt -> {});
	}
}
