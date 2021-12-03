package evolution.flappyBird;

import javafx.scene.layout.Pane;

public enum DifferentModes {
    MANUAL, MULTIPLAYER,SMART;
    public Flappable createBird(Pane gamePane, PipeManager pipeManager) {
        Flappable flappyBird = null;
        switch (this) {
            case MANUAL:
                flappyBird =new ManualBird(gamePane, pipeManager);
                break;
            case MULTIPLAYER: default:
                flappyBird=new MultiPlayerBird(gamePane,pipeManager);
                break;
//            case SMART: default:
//                flappyBird=new Population(gamePane,pipes);
//                break;

        }
        return flappyBird;

    }




}
