package evolution.tetris;

import evolution.Playable;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This is our topLevel logic class
 * It handles all the animation involving both the pieces and the board in the game.
 * It contains both the Pieces class and the Board class.
 */
public class Tetris implements Playable {
    private Pane gamePane;
    private Board board;
    private Timeline timeline;
    private Piece piece;
    private HBox buttonPane;
    private double score;
    private double highScore;
    private Label scoreLabel;
    private Label highScoreLabel;
    private Difficulty difficulty;
    private VBox buttonPane2;
    private BorderPane root;



    /**
     * this is the constructor of the Tetris class, it is associated with the gamePane and the buttonPane.
     */
    public Tetris(Timeline timeline,BorderPane root, Pane gamePane, HBox buttonPane) {
        this.timeline=timeline;
        this.root=root;
        this.gamePane = gamePane;
        this.gamePane.setPrefWidth(Constants.PANE_WIDTH);
        this.gamePane.setPrefHeight(Constants.PANE_HEIGHT);
        this.buttonPane2=new VBox();
        this.buttonPane2.setPrefWidth(Constants.BUTTON2_PANE_WIDTH);
        this.buttonPane2.setPrefHeight(Constants.BUTTON2_PANE_HEIGHT);
        this.buttonPane = buttonPane;
        this.root.setRight(this.buttonPane2);
        this.buttonPane2.setAlignment(Pos.CENTER);
        this.buttonPane2.setSpacing(Constants.BUTTON_SPACING);
        this.board = new Board(gamePane);
        this.piece = new Piece(gamePane, Color.RED, Constants.I_PIECE_COORDS, this.board);
        this.score = 0;
        this.highScore = 0;
        this.setUpScoreLabels();
        this.difficulty = Difficulty.EASY;
        this.difficultyButtons();
        this.gamePane.setFocusTraversable(true);
    }
    @Override

    public void handleKeyPressed(KeyEvent e) {
            KeyCode keyPressed = e.getCode();
                switch (keyPressed) {
                    case LEFT://this moves the pieces to the left.
                        this.piece.moveLeft();
                        break;
                    case RIGHT:
                        this.piece.moveRight();//this moves the pieces to the right.
                        break;
                    case UP:
                        this.piece.rotate();  //this rotates the pieces.
                        break;
                    case DOWN:
                        this.piece.moveDown();//this moves the piece down by an additional square.
                        break;
//                    case P:
//                        this.pause();
//                        break;
                    case SPACE: default:
                        this.piece.drop();
                }
                e.consume();
            //}

    }

    /**
     * This method randomly generates pieces on the screen.
     * It uses a switch statement so that pieces are not generated at the same time.
     */
    private void generatePieces() {
        int random = (int) (Math.random() * 7);
        Piece pieces = null;
        switch (random) {
            case 0:
                pieces = new Piece(gamePane, Color.RED, Constants.I_PIECE_COORDS, this.board);
                break;
                case 1:
                    pieces = new Piece(gamePane, Color.ORANGE, Constants.T_PIECE_COORDS,this.board);
                    break;
                    case 2:
                    pieces = new Piece(gamePane, Color.YELLOW, Constants.S_PIECE_COORDS,this.board);
                    break;
                case 3:
                    pieces = new Piece(gamePane, Color.BLUE, Constants.L_PIECE_COORDS,this.board);
                    break;
                case 4:
                    pieces = new Piece(gamePane, Color.PINK, Constants.J_PIECE_COORDS,this.board);
                    break;
                case 5:
                    pieces = new Piece(gamePane, Color.TURQUOISE, Constants.Z_PIECE_COORDS,this.board);
                    break;
                case 6:
                default:
                    pieces = new Piece(gamePane, Color.PURPLE, Constants.O_PIECE_COORDS,this.board);


        }
        this.piece = pieces;
    }

    /**
     * This is a helper method that handles all the functionalities that should be updated with the timeline(every second).
     */
    @Override
    public void updateGame() {
        if (this.piece.checkMoveValidity(0, 1)) {
            this.piece.moveDownWithTimeline();
        } else {
            this.incrementScore(5);
            this.piece.addPieceLogically();
            this.generatePieces();
        }
        this.rowScore();
        this.board.clearLine();
    }

    /**
     * This method ends the game when columns are full (pieces reach the top of the paneHeight).
     */
    @Override
    public boolean gameOver() {
        for (int col = 1; col < Constants.NUM_COLS-1; col++) {
            if (this.board.getBoard()[1][col] != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method restarts the game when the restart button is clicked.
     */
    @Override
    public void reStart() {
        this.timeline.stop();
        this.score = 0;
        this.gamePane.getChildren().clear();
        this.scoreLabel.setText("score: " + this.score);
        this.board.setUpBoard();
        this.generatePieces();
        System.out.println(this.gameOver());
        this.timeline.play();

    }

    /**
     * this  method changes the difficulty of the game by changing the rate of the timeline
     */
    private void difficultyButtons() {
        Button easy = new Button("Easy");
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");
        easy.setOnAction((ActionEvent e) -> this.switchDifficulty(Difficulty.EASY));
        medium.setOnAction((ActionEvent e) -> this.switchDifficulty(Difficulty.MEDIUM));
        hard.setOnAction((ActionEvent e) -> this.switchDifficulty(Difficulty.HARD));
        this.buttonPane2.getChildren().addAll(easy,medium,hard);
        this.buttonPane2.setFocusTraversable(false);
        easy.setFocusTraversable(false);
        medium.setFocusTraversable(false);
        hard.setFocusTraversable(false);

    }

    /**
     * This method allows switching of difficulty levels, it uses parameter passing.
     * It accesses the enums through parameter passing.
     * @param difficulty
     */
    private void switchDifficulty(Difficulty difficulty) {
        this.timeline.setRate(difficulty.setDifficulty());
        this.difficulty = difficulty;
    }


    /**
     * This method sets up the score labels
     */
    private void setUpScoreLabels() {
        this.scoreLabel = new Label("score: " + this.score);
        this.scoreLabel.setFont(Font.font(Constants.FONT_SIZE));
        this.highScoreLabel = new Label("High Score: " + this.highScore);
        this.highScoreLabel.setFont(Font.font(Constants.FONT_SIZE));
        this.buttonPane2.getChildren().addAll(this.scoreLabel,this.highScoreLabel);
    }

    /**
     * This method increments the score depending on difficulty levels.
     * It also sets up the high score and normal score.
     * @param x
     */
   private void incrementScore(double x){
       switch (this.difficulty) {
           case HARD:
               x=x*3;
           case MEDIUM:
               x=x*2;
           case EASY:
               x=x*1;
           default:
               break;
       }
       this.score=this.score+x;
       this.highScore=Math.max(this.highScore,this.score);
       this.scoreLabel.setText("score: " + this.score);
       this.highScoreLabel.setText("highScore"+ this.highScore);
    }


    /**
     * this method checks the number of the rows that are full
     */
    private int numFullRows() {
        int fullRows = 0;
        for (int row = 1; row < Constants.NUM_ROWS - 1; row++) {
            if (this.board.checkIfFull(row)) {
                fullRows=fullRows+1;
            }
        }
        return fullRows;
    }

    /**
     * this method updates the score when a row or four rows are full
     */
    private void rowScore() {
       this.incrementScore(this.numFullRows()* Constants.ONE_LINE);
        if(this.numFullRows()==4){
            this.incrementScore(Constants.FOUR_LINES);
        }
    }

}


