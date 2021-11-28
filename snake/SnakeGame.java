package evolution.snake;

import evolution.Playable;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 * Handles the high-level logic of the program, by running a timeline that updates
 * the snake and board state at each tick of the timeline and by updating the
 * snake direction of key input.
 */
public class SnakeGame implements Playable {

    private Snake snake;
    private Board board;
    private int score;
    private Label scoreSnake;
    private Timeline timeline;
    private Pane gamePane;
    private HBox buttonPane;
    private Label paused;
    private boolean isPaused;
    private BorderPane root;
    private SnakeMoveResult result;
    private Pane arcadePane;
    /**
     * Sets up the game by registering the KeyEvent handler on the gamePane and starting
     * the timeline that triggers the game update.
     *
     * @param gamePane the pane on which to add game elements
     */
    public SnakeGame(Timeline timeline, BorderPane root,Pane gamePane, HBox buttonPane, Pane arcadePane) {
        this.timeline=timeline;
        this.root=root;
        this.gamePane = gamePane;
        this.arcadePane=arcadePane;
        this.gamePane.setPrefHeight(Constants.GAME_PANE_HEIGHT);
        this.buttonPane=buttonPane;//TODO is this association really necessary
        this.gamePane.setFocusTraversable(true);
        this.board = new Board(this.gamePane);
        this.snake = new Snake(this.board);
        this.scoreSnake = new Label(evolution.snake.Constants.SCORE_LABEL_TEXT + 0);
        this.root.setBottom(this.scoreSnake);
        this.score = 0;
        this.paused = new Label("PAUSED");
        this.isPaused=true;
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPressed(e));
        this.timeline.setRate(1/ Constants.TIMELINE_DURATION);

    }

    @Override
    public void updateGame(){
        this.gameOver();
        this.updateScore();
    }
    /**
     * Updates the state of the game, by moving the snake, updating the score,
     * and spawning new food when necessary.
     */
    @Override
    public void gameOver() {
        this.result=this.snake.move();
        if (this.result == SnakeMoveResult.GAME_OVER) {
            this.timeline.stop();
            Label label = new Label("Game Overr!");
            VBox labelBox = new VBox(label);
            labelBox.setAlignment(Pos.CENTER);
            labelBox.setPrefWidth(this.gamePane.getWidth());
            labelBox.setPrefHeight(this.gamePane.getHeight());
            label.setStyle("-fx-font: italic bold 75px arial, serif;-fx-text-alignment: center;-fx-text-fill: white;");
            Color[] colors = new Color[]{Color.web("#E00009"), Color.web("#E47C00"), Color.web("#ECEF02"),
                    Color.web("#65F400"), Color.web("#51B5FF")};
            DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.web("#E02EF3"),
                    0, 10, 2, 2);
            for (Color color : colors) {
                DropShadow temp = new DropShadow(BlurType.GAUSSIAN, color, 0, 10, 2, 2);
                temp.setInput(shadow);
                shadow = temp;
            }
            label.setEffect(shadow);
            this.gamePane.getChildren().add(labelBox);
        }
    }
    public void updateScore() {
        if (this.result.getScoreIncrease() > 0) {
            this.score += this.result.getScoreIncrease();
            this.scoreSnake.setText(Constants.SCORE_LABEL_TEXT + this.score);
            this.board.spawnFood();
        }
    }
    @Override
    public void back(){
        this.timeline.pause();
        this.root.getChildren().clear();
        this.score=0;
        this.scoreSnake.setText("score: " + this.score);
        this.gamePane.getChildren().clear();
        this.buttonPane.getChildren().clear();
       // this.reStart();
        this.root.getChildren().add(this.arcadePane);

    }

    /**
     * Handles key input by changing direction of snake on up, down, left, and
     * right arrow keys.
     *
     * @param e of the key pressed
     */
    @Override
    public void handleKeyPressed(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case UP:
                this.snake.changeDirection(Direction.UP);
                break;
            case DOWN:
                this.snake.changeDirection(Direction.DOWN);
                break;
            case LEFT:
                this.snake.changeDirection(Direction.LEFT);
                break;
            case RIGHT:
                this.snake.changeDirection(Direction.RIGHT);
                break;
            case P:
                this.pause();
            default:
                break;
        }
    }

    @Override
    public void reStart(){
        this.timeline.pause();
        this.score = 0;
        this.gamePane.getChildren().clear();
        this.scoreSnake.setText("score: " + this.score);
        this.board.setUpBoard();
        this.board.spawnFood();
        this.snake=new Snake(this.board);
        this.timeline.play();

    }
    @Override
    public void pause(){
        this.paused.setFont(Font.font("ARIAL", FontPosture.ITALIC, 40));
        this.paused.setLayoutX(evolution.snake.Constants.LAYOUT_X);
        this.paused.setLayoutY(evolution.snake.Constants.LAYOUT_Y);
        this.paused.setTextFill(Color.RED);
        if (this.isPaused) {
            this.timeline.stop();
            this.gamePane.getChildren().add(this.paused);
            this.isPaused = false;
        } else {
            this.timeline.play();
            this.gamePane.getChildren().remove(this.paused);
            this.gamePane.setFocusTraversable(true);
            this.isPaused = true;
        }

    }


}
