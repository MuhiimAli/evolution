package evolution;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
  * This is the main class where your Evolution program will start.
  * The main method of this application calls the App constructor. You
  * will need to fill in the constructor to instantiate your game.
  *
  * Class comments here...
  *this class adds the root to the scene, sets the scene and shows the stage.
  */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        Arcade arcade = new    Arcade(stage);
        Scene scene = new Scene(arcade.getRoot());
        stage.setScene(scene);
        stage.setTitle("Arcade");
        stage.show();
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
