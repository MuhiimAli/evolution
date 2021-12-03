package evolution.flappyBird;

import evolution.Playable;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class FlappyBird implements Playable {
    private Pane gamePane;
    private Timeline timeline;
    private ArrayList<Pipe> pipeArraylist;
    private Pipe pipes;
    private VBox scorePane;
    private BorderPane root;
    private Flappable bird;
    private Label scoreLabel;
    private Label highScoreLabel;
    private int score;
    private int highScore;
    private Boolean isRunning;

    public FlappyBird(Timeline timeline, BorderPane root, Pane gamePane,DifferentModes birds) {
        this.root = root;
        this.gamePane = gamePane;
        this.scorePane = new VBox();
        this.timeline = timeline;
        this.scorePane.setPrefWidth(30);
        this.scorePane.setPrefHeight(40);
        this.root.setBottom(this.scorePane);
        this.gamePane.setPrefHeight(Constants.GAME_PANE_HEIGHT);
        this.gamePane.setPrefWidth(Constants.GAME_PANE_WIDTH);
        this.pipeArraylist = new ArrayList<>();
        this.pipes = new Pipe(this.gamePane);
        this.pipeArraylist.add(this.pipes);
        this.setUpBackground();
        this.score = 0;
        this.highScore = 0;
        this.scoreLabel = new Label("score: " + this.score);
        this.scoreLabel.setFont(Font.font(evolution.tetris.Constants.FONT_SIZE));
        this.highScoreLabel = new Label("High Score: " + this.highScore);
        this.scorePane.getChildren().addAll(this.scoreLabel, this.highScoreLabel);
        this.highScoreLabel.setFont(Font.font(evolution.tetris.Constants.FONT_SIZE));
        this.timeline.setRate(1 / Constants.DURATION);
        this.bird=birds.createBird(this.gamePane,pipes);
        this.isRunning=true;



    }

    /**
     * this method sets up the background image
     */
    private void setUpBackground() {//TODO figure out how to make images scroll
        Image image = new Image("./evolution/flappyBird/image.png", 900, 900, true, false);//this instantiates an image
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.gamePane.setBackground(new Background(background));//this sets the background of the gamePane for flappyBird

    }

    @Override
    public void updateGame() {
        this.bird.updateBirdVelocity();
        this.scrollPipes();
        this.deletePipes();
        this.setScoreLabel();
    }

    /**
     * this method generates new pipes
     */
    private void generatePipes() {
        while (this.pipes.getXLoc() <= Constants.GAME_PANE_WIDTH) {
                Pipe pipes = new Pipe(this.gamePane);
                pipes.setXLoc(pipes.getXLoc()+Constants.PIPE_HORIZONTAL_DISTANCE);
                this.pipeArraylist.add(pipes);
                this.pipes = pipes;
            }
    }

    /**
     * this method makes the pipes scroll across the screen at a constant rate
     */
    private void scrollPipes(){
        for(int i=0;i<this.pipeArraylist.size();i++) {
            this.pipeArraylist.get(i).setXLoc(this.pipeArraylist.get(i).getXLoc()-Constants.SCROLL_RATE);
        }
        this.generatePipes();


    }

    /**
     * this method deletes the pipes when they're done scrolling, when they're off the screen
     */
    private void deletePipes(){
            if (this.pipeArraylist.get(0).getXLoc() <=-Constants.PIPE_WIDTH){
                this.pipeArraylist.get(0).removeFromPane();
                this.pipeArraylist.remove(0);
            }
    }
    @Override
    public void reStart(){//TODO restart method bug
        this.timeline.stop();
        this.score = 0;
        this.gamePane.getChildren().clear();
        this.scoreLabel.setText("score: " + this.score);
        this.pipes = new Pipe(this.gamePane);
        this.pipeArraylist.add(this.pipes);
        //this.bird=birds.createBird(this.gamePane);
        this.scrollPipes();
        this.deletePipes();
        this.timeline.play();
    }
    @Override
    public boolean gameOver(){
        if(this.bird.gameOver()){
            return true;
        }

        return false;
    }
    @Override
    public void handleKeyPressed(KeyEvent e) {
        this.bird.handleKeyPress(e);
    }
    private void setScoreLabel(){
        if (this.pipeArraylist.get(0).getXLoc()==Constants.PIPE_WIDTH) {
            this.score=this.score+1;
            this.highScore=Math.max(this.highScore,this.score);
            this.scoreLabel.setText("score: " + this.score);
            this.highScoreLabel.setText("highScore: "+ this.highScore);

        }
    }



}
