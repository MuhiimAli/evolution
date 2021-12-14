package evolution.flappyBird;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
/**
 * this is the “multiplayer” Flappy Bird, which operates just like regular Flappy Bird
 * but with two birds: one that reacts to the spaceBar and one that reacts to the up arrow key.
 */
public class MultiPlayerBird implements Flappable{
    private Pane gamePane;
    private ManualBird[] multiPlayer;
    private PipeManager pipeManager;

    /**
     * the gamePane and the PipeManager class are associated with this class
     * the constructor calls the createMultiPlayerBird();
     * @param gamePane
     * @param pipeManager
     */
    public MultiPlayerBird(Pane gamePane, PipeManager pipeManager) {
        this.pipeManager = pipeManager;
        this.gamePane = gamePane;
        this.createMultiPlayerBird();
        this.multiPlayer[0].setYLoc(Constants.Y_LOC_BIRD2);
    }

    /**
     * this method creates two instances of manual bird
     */
    public void createMultiPlayerBird(){
        this.multiPlayer=new ManualBird[2];
        for(int i=0; i< this.multiPlayer.length;i++){
            this.multiPlayer[i]=new ManualBird(this.gamePane, pipeManager);
        }
    }

    /**
     * this method ends the game when both birds die
     */
    @Override
    public Boolean gameOver() {
        for (int i = 0; i < this.multiPlayer.length; i++) {
            if (this.multiPlayer[i]!= null) {
                if (this.multiPlayer[i].checkIntersection(this.pipeManager.nearestPipe(), this.pipeManager.nearestPipe())) {
                    this.multiPlayer[i].removeFromPane();
                    this.multiPlayer[i] = null;
                }
                if (this.multiPlayer[0] == null && this.multiPlayer[1] == null) {
                    return true;
                }
            }
        }
            return false;
    }
    /**
     * this method make one of the birds jump with the spaceBar and the other one with the up arrow key
     * @param e
     */
    @Override
    public void handleKeyPress(KeyEvent e) {
            KeyCode keyPressed = e.getCode();
            switch (keyPressed) {
                case SPACE:
                    if(this.multiPlayer[0]!=null) {
                        this.multiPlayer[0].jump();
                    }
                    break;
                case UP: default:
                    if(this.multiPlayer[1]!=null) {
                        this.multiPlayer[1].jump();
                    }
                    break;
            }
    }

    /**
     * this method updates the velocity of both birds. It makes them go down
     */
    @Override
    public void updateWithTimeline(){
        for(int i=0;i< this.multiPlayer.length;i++){
            if(this.multiPlayer[i]!= null) {
                this.multiPlayer[i].updateBirdVelocity();
            }

        }
    }


}
