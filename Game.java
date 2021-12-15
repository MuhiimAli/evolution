package evolution;

import evolution.flappyBird.DifferentModes;
import evolution.flappyBird.FlappyBird;
import evolution.snake.SnakeGame;
import evolution.tetris.Tetris;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * this enum instantiates the different game classes
 */
public enum Game {
    SMART("SMART BIRD"),MULTIPLAYER("MULTIPLAYER"), MANUAL("MANUAL FLAPPY BIRD"),SNAKE("SNAKE"),TETRIS("TETRIS");
    private String name;
    public Playable createGame(Timeline timeline, BorderPane root, Pane gamePane) {
        Playable playable;
        switch (this) {
            case SMART:
                playable=new FlappyBird(timeline,root, gamePane, DifferentModes.SMART);
                break;
            case MULTIPLAYER:
                playable=new FlappyBird(timeline, root, gamePane, DifferentModes.MULTIPLAYER);
                break;
            case MANUAL:
                playable=new FlappyBird(timeline,root,gamePane,DifferentModes.MANUAL);
                break;
            case SNAKE:
                playable = new SnakeGame(timeline, root, gamePane);
                break;
            case TETRIS: default:
                playable = new Tetris(timeline, root, gamePane);
                break;
            }
            return playable;
        }
    Game(String name){
        this.name=name;
    }

    /**
     * gets the name of the game class
     * @return
     */
    public String getName(){
        return this.name;
    }

}
