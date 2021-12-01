package evolution.snake;

import evolution.Playable;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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
    private BorderPane root;
    private SnakeMoveResult result;
    /**
     * Sets up the game by registering the KeyEvent handler on the gamePane and starting
     * the timeline that triggers the game update.
     *
     * @param gamePane the pane on which to add game elements
     */
    public SnakeGame(Timeline timeline, BorderPane root,Pane gamePane) {
        this.timeline=timeline;
        this.root=root;
        this.gamePane = gamePane;
        this.gamePane.setPrefHeight(Constants.GAME_PANE_HEIGHT);
        this.gamePane.setFocusTraversable(true);
        this.board = new Board(this.gamePane);
        this.snake = new Snake(this.board);
        this.scoreSnake = new Label(evolution.snake.Constants.SCORE_LABEL_TEXT + 0);
        this.root.setBottom(this.scoreSnake);
        this.score = 0;
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPressed(e));
        this.timeline.setRate(1/ Constants.TIMELINE_DURATION);

    }

    @Override
    public void updateGame(){
        this.updateScore();
    }
    /**
     * Updates the state of the game, by moving the snake, updating the score,
     * and spawning new food when necessary.
     */
    @Override
    public boolean gameOver() {
        this.result=this.snake.move();
        if (this.result == SnakeMoveResult.GAME_OVER) {
            return true;
        }
        return false;
    }
    public void updateScore() {
        if (this.result.getScoreIncrease() > 0) {
            this.score += this.result.getScoreIncrease();
            this.scoreSnake.setText(Constants.SCORE_LABEL_TEXT + this.score);
            this.board.spawnFood();
        }
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
            default:
                break;
        }
    }

    @Override
    public void reStart(){//TODO restart method bug: sometimes the food doesn't appear
        System.out.println(this.gameOver());
        this.timeline.pause();
        this.score = 0;
        this.gamePane.getChildren().clear();
        this.scoreSnake.setText("score: " + this.score);
        this.board.setUpBoard();
        this.board.spawnFood();
        this.snake=new Snake(this.board);
        this.timeline.play();

    }


}
