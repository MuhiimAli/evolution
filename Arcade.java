package evolution;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Arcade {
    private BorderPane root;
    public HBox buttonPane;
    private VBox arcadePane;
    private Pane gamePane;
    private Timeline timeline;
    private Stage stage;
    private Button quitButton;
    private boolean isPaused;
    private Label paused;
    private Playable playable;
   private Label gameOverLabel;
   private Boolean gameOver;


    public Arcade(Stage stage) {
        this.stage=stage;
        this.timeline=new Timeline();
        this.root = new BorderPane();
        this.gamePane = new Pane();//instantiates the gamePane
        this.buttonPane = new HBox();//instantiates the buttonPane
        this.arcadePane = new VBox();//Contains the different arcade games
        this.timeline=new Timeline();
        this.root.setCenter(this.arcadePane);
        this.styleGameOVerLabel();
        this.buttonPane.setSpacing(Constants.BUTTON_PANE_SPACING);
        this.styleArcadeLabel();
        this.setUpGames();
        this.createQuitButton();
        this.stage.sizeToScene();
        this.isPaused=true;
        this.paused=new Label("PAUSED");
        this.gameOver=true;
    }

    /**
     * @return returns the root of the arcade class
     */
    public BorderPane getRoot(){
        return this.root;
    }

    /**
     * this method styles the gameOver label
     */
    private void styleGameOVerLabel(){
        this.gameOverLabel=new Label("GAME OVER!!");
        this.gameOverLabel.setTranslateX(evolution.tetris.Constants.GAME_OVER_X);
        this.gameOverLabel.setTranslateY(evolution.tetris.Constants.GAME_OVER_Y);
        this.gameOverLabel.setAlignment(Pos.CENTER);
        this.gameOverLabel.setStyle("-fx-font: italic bold 40px arial, serif;-fx-text-alignment: center;-fx-text-fill: white;");
        Color[] colors = new Color[]{Color.web("#E00009"), Color.web("#E47C00"), Color.web("#ECEF02"),
                Color.web("#65F400"), Color.web("#51B5FF")};
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.web("#E02EF3"),
                0, 0, 2, 2);
        for (Color color : colors) {
            DropShadow temp = new DropShadow(BlurType.GAUSSIAN, color, 1, 10, 2, 2);
            temp.setInput(shadow);
            shadow = temp;
        }
        this.gameOverLabel.setEffect(shadow);
    }

    /**
     * this method styles the arcade label and the arcadePane
     */
    public void styleArcadeLabel(){
        this.arcadePane.setSpacing(Constants.ARCADE_PANE_SPACING);
        this.arcadePane.setPrefHeight(Constants.ARCADE_PANE_HEIGHT);
        this.arcadePane.setPrefWidth(Constants.ARCADE_PANE_WIDTH);
        this.arcadePane.setAlignment(Pos.CENTER);
        BackgroundFill fill = new BackgroundFill(Color.BLACK,null,null);
        this.arcadePane.setBackground(new Background(fill));
        Label arcadeLabel=new Label("Arcade");
        arcadeLabel.setStyle("-fx-font: italic bold 50px arial, serif;-fx-text-alignment: center;-fx-text-fill: white;");
        Color[] colors = new Color[]{Color.web("#E00009"), Color.web("#E47C00"), Color.web("#ECEF02"),
                Color.web("#65F400"), Color.web("#51B5FF")};
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, Color.web("#E02EF3"),
                0, 10, 2, 2);
        for (Color color : colors) {
            DropShadow temp = new DropShadow(BlurType.GAUSSIAN, color, 1, 10, 2, 2);
            temp.setInput(shadow);
            shadow = temp;
        }
        arcadeLabel.setEffect(shadow);
            this.arcadePane.getChildren().add(arcadeLabel);
            this.arcadePane.setFocusTraversable(false);
            this.stage.sizeToScene();
    }
    /**
     * this method creates the quit button
     */
    private void createQuitButton(){
        this.quitButton=new Button("Quit");
        this.quitButton.setOnAction((ActionEvent e) -> System.exit(0));
        this.quitButton.setFocusTraversable(false);
        this.quitButton.setFont(Font.font(15));
        this.arcadePane.getChildren().add(this.quitButton);
        this.stage.sizeToScene();
    }
    /**
     * this method loops through the enum values, creates a button for each game class
     * and calls the start method
     */
    public void setUpGames() {//TODO maybe change the name of this method. can call it arcadeGames
        for (Game game : Game.values()) {
            Button button = new Button(game.getName());
            button.setOnAction((ActionEvent e) -> startGame(game));
            button.setFocusTraversable(false);
            button.setFont(Font.font("ARIAL", FontWeight.BOLD, 30));
            button.setStyle("-fx-font: italic bold 30px arial, serif;-fx-text-alignment: center;-fx-text-fill: white;");
            button.setOnMouseEntered(e ->button.setStyle("-fx-font: italic bold 30px arial, serif;-fx-text-alignment: center;-fx-text-fill: red;"));
            button.setOnMouseExited(e ->button.setStyle("-fx-font: italic bold 30px arial, serif;-fx-text-alignment: center;-fx-text-fill: white;"));
            BackgroundFill fill = new BackgroundFill(Color.BLACK,null,null);
            button.setBackground(new Background(fill));
            this.arcadePane.getChildren().add(button);
            this.arcadePane.setFocusTraversable(false);
            this.stage.sizeToScene();
        }
    }
    /**
     * this method creates game instances
     * it sets up the timeline
     * creates the restart and back buttons and call the restart and back method
     * @param game takes in enum
     */
    public void startGame(Game game) {
        this.playable = game.createGame(this.timeline, this.root, this.gamePane, this.buttonPane);
        this.root.setCenter(this.gamePane);
        KeyFrame keyframe = new KeyFrame(Duration.seconds(1), (ActionEvent e) -> this.updateGame());
        this.timeline.getKeyFrames().add(keyframe);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
        this.createGameButtons();
        this.gamePane.setOnKeyPressed((KeyEvent e) -> this.handlePause(e));
        this.gamePane.setFocusTraversable(true);
        this.stage.sizeToScene();
    }

    /**
     * this method creates the game buttons: restart, quit and back buttons adds them to the buttonPane.
     */
    private void createGameButtons(){
        Button reStartButton = new Button("Restart");//creates the restart button
        Button backButton = new Button("Back");//this creates the back method
        reStartButton.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        this.buttonPane.setFocusTraversable(false);
        reStartButton.setOnAction((ActionEvent e) -> this.playable.reStart());
        backButton.setOnAction((ActionEvent e) -> this.back());
        this.buttonPane.getChildren().addAll(backButton, quitButton, reStartButton);
        this.buttonPane.setPrefWidth(Constants.BUTTON_PANE_WIDTH);
        this.buttonPane.setPrefHeight(Constants.BUTTON_PANE_HEIGHT);
        this.root.setTop(this.buttonPane);//puts the buttonPane on top
        this.buttonPane.setAlignment(Pos.CENTER);


    }

    /**
     * this method pauses the game when the p key is pressed
     */

    private void pause() {
        this.paused.setFont(Font.font("ARIAL", FontPosture.ITALIC, evolution.tetris.Constants.FONT_SIZE_PAUSED));
        this.paused.setLayoutX(evolution.tetris.Constants.LAYOUT_X * evolution.tetris.Constants.SQUARE_SIZE);
        this.paused.setLayoutY(evolution.tetris.Constants.LAYOUT_Y * evolution.tetris.Constants.SQUARE_SIZE);
        this.paused.setTextFill(Color.RED);
        if (this.isPaused) {
            this.timeline.pause();
            this.gamePane.getChildren().add(paused);
            this.isPaused = false;
        } else {
            this.timeline.play();
            this.gamePane.getChildren().remove(paused);
            this.gamePane.setFocusTraversable(true);
            this.isPaused = true;
        }
    }

    /**
     * this method stops all the key inputs except the p key when the game is paused
     * @param e
     */
    private void handlePause(KeyEvent e) {
            KeyCode keyPressed = e.getCode();
            if (keyPressed == KeyCode.P) {
                this.pause();
        } else if(this.isPaused) {
            this.playable.handleKeyPressed(e);

        }
    }
    private void back(){//TODO BUG: when you go from the manual bird to tetris: the timeline increases and the background image is stil there
        this.gamePane.getChildren().clear();
        this.buttonPane.getChildren().clear();
        this.root.getChildren().clear();
        this.root.setCenter(this.arcadePane);
        this.stage.sizeToScene();
        this.timeline.stop();

    }
    /**]\tyuh
     * this method adds the gameOver label to the gamePane when the game is over
     */
    private void gameOver(){
        this.gameOver=this.playable.gameOver();
        if(this.gameOver){
            this.timeline.stop();
            this.gamePane.getChildren().add(this.gameOverLabel);
            this.gameOver=false;//resetting the gameOver instance variable
            this.gamePane.setOnKeyPressed(null);

        }

    }
    private void updateGame(){
        this.gameOver();
        this.playable.updateGame();

    }




}