package evolution;

import evolution.flappyBird.FlappyBird;
import evolution.snake.SnakeGame;
import evolution.tetris.Tetris;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;




public enum Game {
    MANUAL("MANUAL FLAPPY BIRD"),SNAKE("SNAKE"),TETRIS("TETRIS");
    private String name;
    public Playable createGame(Timeline timeline, BorderPane root, Pane gamePane, HBox buttonPane,Pane arcadePane) {
        Playable playable;
        switch (this) {
            case MANUAL:
                playable=new FlappyBird(timeline,root,gamePane);
                break;
            case SNAKE:
                playable = new SnakeGame(timeline, root, gamePane, buttonPane,arcadePane);
                break;
            case TETRIS: default:
                playable = new Tetris(timeline, root, gamePane, buttonPane);
                break;
            }
            return playable;
        }
    Game(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }

}
