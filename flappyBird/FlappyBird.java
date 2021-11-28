package evolution.flappyBird;

import evolution.Playable;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import java.util.ArrayList;

public class FlappyBird implements Playable {
    private Pane gamePane;
    private Timeline timeline;
    private ArrayList<Pipe> pipeArraylist;
    private Pipe pipes;
    private Pane scorePane;
    private BorderPane root;
    public FlappyBird(Timeline timeline,BorderPane root,Pane gamePane){
        this.root=root;
        this.timeline=timeline;
        this.timeline.setRate(1.5);
        this.gamePane=gamePane;
        this.scorePane=new Pane();
        this.scorePane.setPrefWidth(30);
        this.scorePane.setPrefHeight(40);
        this.root.setBottom(this.scorePane);
        this.gamePane.setPrefHeight(Constants.GAME_PANE_HEIGHT);
        this.gamePane.setPrefWidth(Constants.GAME_PANE_WIDTH);
        this.pipeArraylist =new ArrayList<>();
        this.pipes=new Pipe(this.gamePane);
        this.pipeArraylist.add(this.pipes);
        this.setUpBackground();


    }

    /**
     * this method sets up the background image
     */
    private void setUpBackground(){//TODO figure out how to make images scroll
        Image image = new Image("./evolution/flappyBird/image.png", 900, 900, true, false);//this instantiates an image
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.gamePane.setBackground(new Background(background));//this sets the background of the gamePane for flappyBird
    }
    @Override
    public void updateGame(){
       this.scrollPipes();
       this.deletePipes();

    }

    /**
     * this method generates new pipes
     */


    private void generatePipes() {
        while (this.pipes.getXLoc() <Constants.GAME_PANE_WIDTH) {
            for (int i = 0; i < Constants.GAME_PANE_WIDTH; i++) {//TODO NOT SURE ABOUT THIS LINE
                Pipe pipes = new Pipe(this.gamePane);
                this.pipeArraylist.add(pipes);
                pipes.setXLoc(pipeArraylist.get(i).getXLoc() + Constants.PIPE_HORIZONTAL_DISTANCE);
                this.pipes = pipes;
            }
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
        for(int i=0;i<this.pipeArraylist.size();i++) {
            while (this.pipeArraylist.get(i).getXLoc() <-Constants.GAME_PANE_WIDTH){
                this.pipeArraylist.get(0).removeFromPane();
                this.pipeArraylist.remove(0);
            }
        }

    }
    @Override//TODO something wrong with the start method
    public void reStart(){
        this.gamePane.getChildren().clear();
        this.pipes=new Pipe(this.gamePane);
        this.pipeArraylist.add(this.pipes);
        this.updateGame();


    }
    @Override
    public void gameOver(){

    }
    @Override
    public void pause(){

    }

    @Override
    public void back(){
    }
    @Override
    public void handleKeyPressed(KeyEvent e){

    }
}
