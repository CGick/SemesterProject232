package edu.cis232.CheckersSemesterProject;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
	Player player1, player2; //Players
	StringBuilder sb; //REQ#2 StringBuilder
	private final int COLUMN = 8, ROW = 8;
	private AnchorPane selected = null;
	private Board gameBoard;
	private boolean player; //Switches the player
	private int pCount;

	/**
	 * Initilizes the players and game board.
	 */
	public void initialize() {
		resetBoard();
		mnuNewGame.setDisable(true); //Can't start a new game in progress, must resign first.
		addPlayer1();
		addPlayer2();
		lblStatus.setText(player1.getPlayer() + ":  Make your move!");
	}

	/**
	 * Adds a win or lose to the players 
	 */
	public void endGame(){
		//If player1 resigns add lose, add win to player2.
		if (player)
		{
			player1.addLose();
			player2.addWin();
		}
		//If player2 resigns add lose, add win to player1.
		else if (!player)
		{
			player2.addLose();
			player1.addWin();
		}
		player1.updatePlayer();
		player2.updatePlayer();
	}
	
	/**
	 * Starts a new game.
	 * @param event
	 */
	@FXML
    void newGame(ActionEvent event) {
		selected = null;
		System.out.println("New Game");
		player = true;
		if (JOptionPane.showConfirmDialog(null, "Want to use the same players?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) 
		{
			System.out.println("Keeping current players!");
		} 
		else 
		{
			System.out.println("Using different players!");
			addPlayer1();
			addPlayer2();
		}
		lblStatus.setText(player1.getPlayer() + ":  Make your move!");
		mnuNewGame.setDisable(true); //Can't start a new game in progress, must resign first.
		mnuResign.setDisable(false); //Resign from game
		resetBoard();
    }

	/**
	 * Resigns the current player
	 * @param event
	 */
	@FXML
	void resign(ActionEvent event) {
		if (player)
		{
			lblStatus.setText(player1.getPlayer() + " resigns, " + player2.getPlayer() + " wins!");
		}
		else
		{
			lblStatus.setText(player2.getPlayer() + " resigns, " + player1.getPlayer() + " wins!");
		}
		endGame();
		lblPlayer1Stats.setText(String.format("Wins: %d, Loses: %d", player1.getWins(), player1.getLoses()));
		lblPlayer2Stats.setText(String.format("Wins: %d, Loses: %d", player2.getWins(), player2.getLoses()));
		mnuNewGame.setDisable(false); //Enables button to start a new game, if wanted to.
		mnuResign.setDisable(true); //Can't resign when a game ins't in progress.
		System.out.println("Resign");
		selected = null;
	}

	/**
	 * REQ#2
	 * uses a string builder to set the wins and loses of each player
	 */
	@FXML
	void addPlayer1() {
		try
		{
			String name = JOptionPane.showInputDialog("Enter Player1 Name");
			if (name == null || name.equals(""))
				throw new InvalidPlayerInputException();
			player1 = new Player(name);
			lblPlayer1Name.setText(player1.getPlayer());
			sb = new StringBuilder("Wins: " + String.valueOf(player1.getWins()) + ", Loses: " + String.valueOf(player1.getLoses())); //REQ#2 StringBuilder
			lblPlayer1Stats.setText(sb.toString());
		}
		catch (InvalidPlayerInputException e)
		{
			JOptionPane.showMessageDialog(null, "You must enter a player name!"); //REQ #12 Custom Exception
			addPlayer1();
		}		
	}

	/**
	 * REQ#2
	 * uses a string builder to set the wins and loses of each player
	 */
	@FXML
	void addPlayer2() {
		try 
		{
			String name = JOptionPane.showInputDialog("Enter Player2 Name");
			if(name == null || name.equals(""))
				throw new InvalidPlayerInputException();
			player2 = new Player(name);
			lblPlayer2Name.setText(player2.getPlayer());
			sb = new StringBuilder("Wins: " + String.valueOf(player2.getWins()) + ", Loses: " + String.valueOf(player2.getLoses())); //REQ#2 StringBuilder
			lblPlayer2Stats.setText(sb.toString());
		}
		catch (InvalidPlayerInputException e)
		{
			JOptionPane.showMessageDialog(null, "You must enter a player name!"); //REQ #12 Custom Exception
			addPlayer2();			
		}
	}

	/**
	 * Sets up the board after newGame is clicked or the game starts up.
	 */
	private void resetBoard() {
		pCount = 0;
		player = true;
		gameBoard = new Board();
		gameBoard.getBoard(); //Test to make sure the board array is generated correctly
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
	}

	private void setupMouseClickListener(AnchorPane p, final int row, final int col) {
		p.setOnMousePressed(new EventHandler<MouseEvent>() {						
			@Override
			public void handle(MouseEvent evt) {
				AnchorPane a = (AnchorPane) evt.getSource();
				a.setStyle("-fx-background-color: #7AFFE7");
				//gameBoard.clickSquare(row, col);
				if (selected != null) {
					selected.setStyle("-fx-background-color: black");

					if (player)
					{
						System.out.println("Player 1");
						lblStatus.setText(player2.getPlayer() + ":  Make your move!");
						if (!selected.getChildren().isEmpty()){
							ImageView image = (ImageView) selected.getChildren().remove(0);
							if (image.getImage() != null && image.getImage().equals(redChecker))
								System.out.println("Red Checker!");
							a.getChildren().add(image);	
							pCount++;
						}
						if (pCount == 2)
						{
							player = false;
							pCount = 0;
						}
					}
					
					else if (!player)
					{
						System.out.println("Player 2");
						lblStatus.setText(player1.getPlayer() + ":  Make your move!");
						if (!selected.getChildren().isEmpty()) {
							ImageView image = (ImageView) selected.getChildren().remove(0);
							if (image.getImage() != null && image.getImage().equals(blackChecker))
								System.out.println("Black Checker!");
							a.getChildren().add(image);	
							pCount++;
						}
						if (pCount == 2)
						{
							player = true;
							pCount = 0;
						}
					}
				} 
				a.setStyle("-fx-background-color: #7AFFE7");
				selected = a;
			}		
		});
	}
}