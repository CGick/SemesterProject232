package edu.cis232.CheckersSemesterProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    
	public static void main(String[] args ){
        launch(args);
    }

	@Override
	public void start(Stage arg0) throws Exception {
		Parent parent = FXMLLoader.load(MainController.class.getResource("checkers.fxml"));
		
		Scene scene = new Scene(parent);
		
		arg0.setScene(scene);
		arg0.setTitle("Checkers");
		arg0.setResizable(false);
		arg0.show();
		
	}
}
