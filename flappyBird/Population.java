package evolution.flappyBird;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Population  implements Flappable{

    private Pane gamePane;
    private ArrayList<ManualBird> smartArrayList;
    private ManualBird smartBird;
    private PipeManager pipes;

    public Population(Pane gamePane, PipeManager pipes) {
        this.pipes=pipes;
        this.gamePane = gamePane;
        this.smartArrayList = new ArrayList<>();
        this.smartBird = new ManualBird(this.gamePane,pipes);
        this.smartArrayList.add(this.smartBird);
        this.createMultiPlayerBirds();
    }

    private void createMultiPlayerBirds() {
        for (int i = 0; i <51; i++) {
            ManualBird smartBirds = new ManualBird(this.gamePane,pipes);
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
    @Override
    public void createBirds(){

    }
}
