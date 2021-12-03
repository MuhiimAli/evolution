package evolution.flappyBird;

import javafx.scene.layout.Pane;

public enum DifferentModes {
    MANUAL, MULTIPLAYER,SMART;
    public Flappable createBird(Pane gamePane, Pipe pipes) {
        Flappable flappyBird = null;
        switch (this) {
            case MANUAL:
                flappyBird =new ManualBird(gamePane,pipes);
                break;
            case MULTIPLAYER:
                flappyBird=new MultiPlayerBird(gamePane,pipes);
                break;
            case SMART: default:
                flappyBird=new Population(gamePane,pipes);
                break;

        }
        return flappyBird;

    }




}
