/**
 * REQ#1 & REQ#9
 * creates and javafx GUI
 * displays the names of the group members when the program executes.
 */
package edu.cis232.CheckersSemesterProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{
    
	/**
	 * Launches the application
	 * @param args
	 */
	public static void main(String[] args ){
        launch(args);
    }

	@Override
	/**
	 * Creates the stage to display the program.
	 */
	public void start(Stage arg0) throws Exception {
		Parent parent = FXMLLoader.load(MainController.class.getResource("checkers.fxml"));
		
		Scene scene = new Scene(parent);
		
		arg0.setScene(scene);
		//REQ#1
		arg0.setTitle("Checkers by Chris Gick and James Williams");
		arg0.setResizable(false);
		arg0.show();		
	}
}
