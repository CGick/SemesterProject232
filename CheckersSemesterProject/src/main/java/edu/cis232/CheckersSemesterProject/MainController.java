package edu.cis232.CheckersSemesterProject;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainController {

	@FXML
	private GridPane grid;

	public void initialize() {

		Image redChecker = new BetterImage("RedCheckerPiece.png");
		Image blackChecker = new BetterImage("BlackCheckerPiece.png");
		final int COLUMN = 8, ROW = 8;

		
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COLUMN; c++) {
				AnchorPane p = new AnchorPane();
				ImageView img = new ImageView();
				img.setFitHeight(90.00);
				img.setFitWidth(90.00);
//				p.setMaxSize(size, size);
	
				AnchorPane.setTopAnchor(img, 5.0);
				AnchorPane.setRightAnchor(img, 5.0);
				AnchorPane.setBottomAnchor(img, 5.0);
				AnchorPane.setLeftAnchor(img, 5.0);
				p.getChildren().add(img);
				if (r % 2 == c % 2) {
					p.setStyle("-fx-background-color: red");
					grid.add(p, c, r);
					
				}else{
					p.setStyle("-fx-background-color: black");
					if(r < 3){
						img.setImage(blackChecker);
					}else if(r > 4){
						img.setImage(redChecker);
					}
					grid.add(p, c, r);
				}
			}
		}
	}
	
	

}
