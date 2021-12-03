package evolution.flappyBird;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class MultiPlayerBird implements Flappable{
    private Pane gamePane;
    private ManualBird[] multiPlayer;
    private Pipe pipes;
    public MultiPlayerBird(Pane gamePane, Pipe pipes) {
        this.pipes=pipes;
        this.gamePane = gamePane;
        this.createMultiPlayerBird();
        this.multiPlayer[0].setYLoc(10);
    }

    /**
     * this method creates two instances of manual bird
     */
    public void createMultiPlayerBird(){
        this.multiPlayer=new ManualBird[2];
        for(int i=0; i< this.multiPlayer.length;i++){
            this.multiPlayer[i]=new ManualBird(this.gamePane, pipes);
        }
    }


    /**
     * this method ends the game when both birds die
     */
    @Override
    public Boolean gameOver() {
        for (int i = 0; i < this.multiPlayer.length; i++) {
            if (this.multiPlayer[i].checkIntersection(pipes, pipes) || this.multiPlayer[i].getYLoc()>=Constants.GAME_PANE_HEIGHT) {
                this.multiPlayer[i].removeFromPane();
                this.multiPlayer[i] = null;
                System.out.println(this.multiPlayer[i]=null);

            }
        }
        if(this.multiPlayer.length==0){
            return true;
        }
        return false;
    }

    @Override//todo perfect
    public void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case SPACE:
                this.multiPlayer[0].jump();
                break;
            case UP: default:
                this.multiPlayer[1].jump();
                break;
        }
    }
    @Override// todo perfect
    public void updateBirdVelocity(){
        System.out.println(this.gameOver());
        for(int i=0;i< this.multiPlayer.length;i++){
            this.multiPlayer[i].updateBirdVelocity();


        }
    }

}
