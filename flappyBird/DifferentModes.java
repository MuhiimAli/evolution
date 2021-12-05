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
            case MULTIPLAYER:
                flappyBird=new MultiPlayerBird(gamePane,pipeManager);
                break;
            case SMART: default:
                flappyBird=new SmartBird(gamePane,pipeManager);
                break;

        }
        return flappyBird;

    }




}
