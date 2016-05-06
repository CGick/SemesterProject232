package edu.cis232.CheckersSemesterProject;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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

	@FXML
	private Label lblStatus;

	Image redChecker = new BetterImage("RedCheckerPiece.png");
	Image blackChecker = new BetterImage("BlackCheckerPiece.png");
	Player player1, player2;
	public CheckersMove[] legal;
	private final int COLUMN = 8, ROW = 8;
	private AnchorPane selected = null;
	private Board board;
	private CheckersData game;
	private boolean player = true;
	private ImageView[][] i = new ImageView[8][8];

	public void initialize() {
		resetBoard();
//		addPlayer1();
//		addPlayer2();
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
	void addPlayer1() {
		String name = JOptionPane.showInputDialog("Enter Player1 Name");
		player1 = new Player(name);
		lblPlayer1Name.setText(player1.getPlayer());
		lblPlayer1Stats.setText(String.format("Wins: %d, Loses: %d", player1.getWins(), player1.getLoses()));

	}

	@FXML
	void addPlayer2() {
		String name = JOptionPane.showInputDialog("Enter Player2 Name");
		player2 = new Player(name);
		lblPlayer2Name.setText(player2.getPlayer());
		lblPlayer2Stats.setText(String.format("Wins: %d, Loses: %d", player2.getWins(), player2.getLoses()));
	}

	
	private void resetBoard() {
		board = new Board();
		game = new CheckersData();
		game.newGame();
		game.getBoard();
		grid.getChildren().clear();
		for (int r = 0; r < ROW; r++) {
			for (int c = 0; c < COLUMN; c++) {
				AnchorPane p = new AnchorPane();

				if (r % 2 == c % 2) {
					p.setStyle("-fx-background-color: red");
					grid.add(p, c, r);
				} else {
					ImageView img = new ImageView();
					img.setFitHeight(90.00);
					img.setFitWidth(90.00);
					final int row = r, col = c;
					AnchorPane.setTopAnchor(img, 5.0);
					AnchorPane.setRightAnchor(img, 5.0);
					AnchorPane.setBottomAnchor(img, 5.0);
					AnchorPane.setLeftAnchor(img, 5.0);
					p.getChildren().add(img);

					p.setStyle("-fx-background-color: black");
					setupMouseClickListener(p, row, col);
					if (r < 3) {
						img.setImage(blackChecker);
//						i[r][c] = blackChecker;

					} else if (r > 4) {

						img.setImage(redChecker);
					}
					grid.add(p, c, r);
				}
			}
		}
		lblStatus.setText("Player 1: Make your move!");
	}

	private void setupMouseClickListener(AnchorPane p, final int row, final int col) {
		p.setOnMousePressed(new EventHandler<MouseEvent>() {			

			@Override
			public void handle(MouseEvent evt) {
				AnchorPane a = (AnchorPane) evt.getSource();
				//a.setStyle("-fx-background-color: #7AFFE7");
				//System.out.printf("%d, %d%n", row, col);
				if (selected != null) {
					selected.setStyle("-fx-background-color: black");

					if (player)
					{
						System.out.println("Player 1");
						ImageView image = (ImageView) selected.getChildren().remove(0);
						a.getChildren().add(image);
						System.out.println(GridPane.getRowIndex(selected));					
						
						player = false;
					}
					
					else if (!player)
					{
						System.out.println("Player 2");
						if (!selected.getChildren().isEmpty()) {
							ImageView image = (ImageView) selected.getChildren().remove(0);
							a.getChildren().add(image);
							System.out.println(GridPane.getRowIndex(selected));					
						}
						player = true;
					}
					

				} 
				a.setStyle("-fx-background-color: #7AFFE7");
				selected = a;
			}
			
		});
	}
	
	private void runGame()
	{
		boolean gameInProgress;
		int currentPlayer, selectedRow, selectedCol;
		CheckersData board;
		CheckersMove[] legalMoves;		
	}
}