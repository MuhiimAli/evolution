package evolution.flappyBird;
import evolution.Playable;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * this is the top-level logic class for the different birds. The gameOVer and the different handleKeyPresses
 * of the birds are called here
 */

public class FlappyBird implements Playable {
    private Pane gamePane;
    private Timeline timeline;
    private VBox scorePane;
    private BorderPane root;
    private Flappable bird;
    private Label scoreLabel;
    private Label highScoreLabel;
    private int score;
    private int highScore;
    private PipeManager pipeManager;
    private DifferentModes modes;

    public FlappyBird(Timeline timeline, BorderPane root, Pane gamePane,DifferentModes birds) {
        this.root = root;
        this.gamePane = gamePane;
        this.scorePane = new VBox();
        this.timeline = timeline;
        this.scorePane.setPrefWidth(Constants.SCORE_PANE_WIDTH);
        this.scorePane.setPrefHeight(Constants.SCORE_PANE_HEIGHT);
        this.root.setBottom(this.scorePane);
        this.gamePane.setPrefHeight(Constants.GAME_PANE_HEIGHT);
        this.gamePane.setPrefWidth(Constants.GAME_PANE_WIDTH);
        this.setUpBackground();
        this.score = 0;
        this.highScore = 0;
        this.scoreLabel = new Label("score: " + this.score);
        this.scoreLabel.setFont(Font.font(evolution.tetris.Constants.FONT_SIZE));
        this.highScoreLabel = new Label("High Score: " + this.highScore);
        this.scorePane.getChildren().addAll(this.scoreLabel, this.highScoreLabel);
        this.highScoreLabel.setFont(Font.font(evolution.tetris.Constants.FONT_SIZE));
        this.timeline.setRate(1 / Constants.DURATION);
        this.pipeManager =new PipeManager(this.gamePane);
        this.modes=birds;
        this.bird=this.modes.createBird(this.gamePane,this.pipeManager, scorePane, this.timeline);
    }

    /**
     * this method sets up the background image
     */
    private void setUpBackground() {
        Image image = new Image("./evolution/flappyBird/image.png", Constants.IMAGE_WIDTH, Constants.IMAGE_HEIGHT, true, false);//this instantiates an image
        BackgroundImage background = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        this.gamePane.setBackground(new Background(background));//this sets the background of the gamePane for flappyBird
    }

    /**
     * this method updates the state of the game with the timeline
     */
    @Override
    public void updateGame() {
        this.pipeManager.scrollPipes();
        this.pipeManager.deletePipes();
        this.bird.updateWithTimeline();//updates with the timeline
        this.setScoreLabel();
    }
    /**
     * This method restarts the game when the restart button is clicked.
     */
    @Override
    public void reStart(){
        this.timeline.stop();
        this.score = 0;
        this.gamePane.getChildren().clear();
        this.scoreLabel.setText("score: " + this.score);
        this.pipeManager=new PipeManager(this.gamePane);
        this.bird=this.modes.createBird(this.gamePane,this.pipeManager, scorePane, this.timeline);//this creates birds
        this.timeline.play();
    }
    /**
     * this method ends the game when the gameOver condition is true for the game that is currently playing
     * @return
     */
    @Override
    public boolean gameOver(){
        if(this.bird.gameOver()){
            return true;
        }

    return false;
    }

    /**
     * this method calls the handleKeypress methods defined in the different bird classes
     * @param e
     */
    @Override
    public void handleKeyPressed(KeyEvent e) {
        this.bird.handleKeyPress(e);
    }

    /**
     * this method tracks the bird’s score, which increases by one for each pipe the bird passes through
     */
    private void setScoreLabel(){
        if (this.pipeManager.nearestPipe().getXLoc()==Constants.PIPE_WIDTH) {
            this.score=this.score+1;
            this.highScore=Math.max(this.highScore,this.score);
            this.scoreLabel.setText("score: " + this.score);
            this.highScoreLabel.setText("highScore: "+ this.highScore);

        }
    }



}
