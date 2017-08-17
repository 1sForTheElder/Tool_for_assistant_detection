package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TanChuang extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       final Button b2 = new Button("  O K  ");
        
        b2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                   Stage stage = new Stage();
                   Label l = new Label("You need to choose a part before highlighting");
                   Scene s = new Scene(l,200,100);
                   stage.setScene(s);
                   stage.show();
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(b2);
        
        Scene scene = new Scene(root, 500, 350);
        
        primaryStage.setTitle("Error");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static void showWind(String[] args){
    	launch(args);
    }
}