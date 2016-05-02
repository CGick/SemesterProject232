package edu.cis232.CheckersSemesterProject;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private GridPane grid;

    @FXML
    private VBox player1VBox;

    @FXML
    private AnchorPane anchPanePlayer1;

    @FXML
    private Label lblPlayer1Name;

    @FXML
    private Label lblPlayer1Stats;

    @FXML
    private VBox player2VBox;

    @FXML
    private AnchorPane anchPanePlayer2;

    @FXML
    private Label lblPlayer2Name;

    @FXML
    private Label lblPlayer2Stats;

    @FXML
    private MenuItem mnuNewGame;

    @FXML
    private MenuItem mnuResign;
    
    Image redChecker = new BetterImage("RedCheckerPiece.png");
    Image blackChecker = new BetterImage("BlackCheckerPiece.png");
    
    private final int COLUMN = 8, ROW = 8;
    private Region selected = null;

	public void initialize() {
		resetBoard();
		addPlayer1();
		addPlayer2();
	}
	
	@FXML
	void newGame() {
		System.out.println("New Game");
	}
	
	@FXML
	void resign() {
		System.out.println("Resign");
	}
	
	@FXML
	void addPlayer1(){
		//hard coded for testing
		ResultSet player1 = PlayerDBAccessor.lookUpPlayer("Chris Gick");
		try {
			player1.next();
			lblPlayer1Name.setText(player1.getString("PlayerName"));
			lblPlayer1Stats.setText(String.format("Wins: %d, Loses: %d",
					player1.getInt("Wins"), player1.getInt("Loses")));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	@FXML
	void addPlayer2(){
		//hard coded for testing
		ResultSet player2 = PlayerDBAccessor.lookUpPlayer("Dan Rusk");
		
		try {
			player2.next();
			lblPlayer2Name.setText(player2.getString("PlayerName"));
			lblPlayer2Stats.setText(String.format("Wins: %d, Loses: %d",
					player2.getInt("Wins"), player2.getInt("Loses")));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	private void resetBoard() {
		grid.getChildren().clear();
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COLUMN; c++) {
				AnchorPane p = new AnchorPane();
				ImageView img = new ImageView();
				img.setFitHeight(90.00);
				img.setFitWidth(90.00);
				final int row = r, col = c;
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
					p.setOnMousePressed(new EventHandler <MouseEvent>(){
						
						@Override
						public void handle(MouseEvent evt) {
							System.out.printf("%d, %d%n", row, col);
							AnchorPane a = (AnchorPane)evt.getSource();
							if(selected != null){
								
								selected.setStyle("-fx-background-color: black");
							}
							a.setStyle("-fx-background-color: #7AFFE7");
							selected = a;
						}
					});
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
