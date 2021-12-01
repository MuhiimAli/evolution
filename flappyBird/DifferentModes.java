package evolution.flappyBird;

import javafx.scene.layout.Pane;

public enum DifferentModes {
    MANUAL, MULTIPLAYER,SMART;
    public ManualBird createBird(Pane gamePane) {
        ManualBird flappyBird = null;
        switch (this) {
            case MANUAL:
                flappyBird =new ManualBird(gamePane);
                break;
            case MULTIPLAYER:
                flappyBird =new MultiplayerBird(gamePane);
                break;
            case SMART: default:
                break;

        }
        return flappyBird;

    }




}
