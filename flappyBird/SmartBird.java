package evolution.flappyBird;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class SmartBird implements Flappable{

    private Pane gamePane;
    private ArrayList<ManualBird> smartArrayList;
    private ManualBird smartBird;
    private PipeManager pipeManager;
    private double[] inputArray;

    public SmartBird(Pane gamePane, PipeManager pipeManager) {
        this.inputArray=new double[4];
        this.pipeManager = pipeManager;
        this.gamePane = gamePane;
        this.smartArrayList = new ArrayList<>();
        this.smartBird = new ManualBird(this.gamePane, pipeManager);
        this.smartArrayList.add(this.smartBird);
        this.createMultiPlayerBirds();
        this.createInputNodes();
    }

    private void createMultiPlayerBirds() {
        for (int i = 0; i <51; i++) {
            ManualBird smartBirds = new ManualBird(this.gamePane, pipeManager);
            this.smartBird = smartBirds;
            this.smartArrayList.add(smartBirds);
        }
    }
    @Override
    public Boolean gameOver(){
        return false;

    }
    @Override
    public void handleKeyPress(KeyEvent e){

    }
    @Override
    public void updateBirdVelocity(){
        for (int i = 0; i <51; i++) {
            this.smartArrayList.get(i).updateBirdVelocity();
        }

    }
    private void createInputNodes(){
        for(int i=0; i< this.inputArray.length; i++){
          double birdYLocation=this.smartBird.getYLoc();
           double nearestPipeXLoc=this.pipeManager.nearestPipe().getXLoc();
           this.inputArray[0]=birdYLocation/Constants.GAME_PANE_HEIGHT;
           this.inputArray[1]=nearestPipeXLoc/Constants.GAME_PANE_HEIGHT;
           this.inputArray[2]=Constants.PIPE_GAP/Constants.GAME_PANE_HEIGHT;
           this.inputArray[3]=Constants.PIPE_HORIZONTAL_DISTANCE;

        }
    }
    public double[] getInputArray(){
        return this.inputArray;
    }
}
