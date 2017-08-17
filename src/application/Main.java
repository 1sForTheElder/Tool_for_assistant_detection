package application;
	
import java.io.File;
import java.net.URL;

import javax.swing.JTextPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			this.clean_total_files("src/uploadFiles/");
			this.clean_total_files(System.getProperty("user.dir")+"/items/");
			Parent root = FXMLLoader.load(getClass()
                    .getResource("/application/UI.fxml"));
			Scene t = new Scene(root);
			primaryStage.setScene(t);
			primaryStage.resizableProperty().set(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	///////clean total files/////
	public void clean_total_files(String the_clean_file_path){
		File a = new File(the_clean_file_path);
		if(a.exists()){
			this.invokedelete(a.getPath());
		}
	}
	
	public void invokedelete(String path){
	    File f=new File(path);
	    if(f.isDirectory()){
	        String[] list=f.list();
	        for(int i=0;i<list.length;i++){
	            invokedelete(path+"//"+list[i]);
	        }
	    }
	    if(f.getPath()!=path){
	    	f.delete();
	    }
	}

}
