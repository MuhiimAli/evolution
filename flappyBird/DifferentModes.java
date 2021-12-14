package evolution.flappyBird;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;

/**
 * this enum instantiates the different Flappy Bird modes
 */
public enum DifferentModes {
    MANUAL, MULTIPLAYER,SMART;
    public Flappable createBird(Pane gamePane, PipeManager pipeManager, Pane scorePane, Timeline timeline) {
        Flappable flappyBird = null;
        switch (this) {
            case MANUAL:
                flappyBird =new ManualBird(gamePane, pipeManager);//creates instance of the manualBird class
                break;
            case MULTIPLAYER:
                flappyBird=new MultiPlayerBird(gamePane,pipeManager);//initializes an instance of the multiplayer class
                break;
            case SMART: default:
                flappyBird=new Population(gamePane,pipeManager, scorePane, timeline);//creates an instance of the population class
                break;

        }
        return flappyBird;

    }




}
