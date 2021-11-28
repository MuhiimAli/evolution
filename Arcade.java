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
    private Pane pausedPane;

    public Arcade(Stage stage) {
        this.stage=stage;
        this.timeline=new Timeline();
        this.root = new BorderPane();
        this.gamePane = new Pane();//instantiates the gamePane
        this.buttonPane = new HBox();//instantiates the buttonPane
        this.arcadePane = new VBox();//Contains the different arcade games
        this.root.setTop(this.buttonPane);//puts the buttonPane on top
        this.buttonPane.setAlignment(Pos.CENTER);
        this.root.setCenter(this.arcadePane);
        this.buttonPane.setSpacing(Constants.BUTTON_PANE_SPACING);
        this.timeline=new Timeline();
        this.styleArcadeLabel();
        this.setUpGames();
        this.createQuitButton();
        this.stage.sizeToScene();
        this.isPaused=true;
        this.paused=new Label("PAUSED");
        this.pausedPane=new Pane();

    }

    /**
     * @return returns the root of the arcade class
     */
    public BorderPane getRoot(){
        return this.root;
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
    public void startGame(Game game) {//this method is getting long. I need to delegate the work
        Playable playable = game.createGame(this.timeline, this.root, this.gamePane, this.buttonPane, this.arcadePane);
        Button reStartButton = new Button("Restart");//creates the restart button
        Button backButton = new Button("Back");//this creates the back method
        reStartButton.setFocusTraversable(false);
        backButton.setFocusTraversable(false);
        this.buttonPane.setFocusTraversable(false);
        reStartButton.setOnAction((ActionEvent e) -> playable.reStart());
        backButton.setOnAction((ActionEvent e) -> playable.back());//TODO problem with this one
        this.buttonPane.getChildren().addAll(backButton, quitButton, reStartButton);
        this.buttonPane.setPrefWidth(Constants.BUTTON_PANE_WIDTH);
        this.buttonPane.setPrefHeight(Constants.BUTTON_PANE_HEIGHT);
        KeyFrame keyframe = new KeyFrame(Duration.seconds(1), (ActionEvent e) -> playable.updateGame());
        this.timeline.getKeyFrames().add(keyframe);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
        this.root.setCenter(this.gamePane);
        this.stage.sizeToScene();
        this.gamePane.setOnKeyPressed((KeyEvent e) -> playable.handleKeyPressed(e));
        this.gamePane.setFocusTraversable(true);


    }
    private void pause() {//TODO makes sense why the pause should be in the arcade class
        this.paused.setFont(Font.font("ARIAL", FontPosture.ITALIC, evolution.tetris.Constants.FONT_SIZE_PAUSED));
        this.paused.setLayoutX(evolution.tetris.Constants.LAYOUT_X * evolution.tetris.Constants.SQUARE_SIZE);
        this.paused.setLayoutY(evolution.tetris.Constants.LAYOUT_Y * evolution.tetris.Constants.SQUARE_SIZE);
        this.paused.setTextFill(Color.RED);
        if (this.isPaused) {
            this.timeline.pause();
            this.pausedPane.getChildren().add(paused);
            this.isPaused = false;
            System.out.println(this.isPaused);
        } else {
            this.timeline.play();
            this.gamePane.getChildren().remove(paused);
            this.gamePane.setFocusTraversable(true);
            this.isPaused = true;
        }
    }
    private void handleKeyPressed(KeyEvent e){
        KeyCode keyPressed = e.getCode();
        System.out.println("hello");
        switch (keyPressed) {
            case P:
                System.out.println("hello");
               // this.pause();
                break;
        }

    }


}