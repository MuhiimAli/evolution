package evolution.flappyBird;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Population  implements Flappable{

    private Pane gamePane;
    private ArrayList<ManualBird> smartArrayList;
    private ManualBird smartBird;
    private Pipe pipes;

    public Population(Pane gamePane, Pipe pipes) {
        this.pipes=pipes;
        this.gamePane = gamePane;
        this.smartArrayList = new ArrayList<>();
        //this.smartBird = new ManualBird(this.gamePane,pipes);
        this.smartArrayList.add(this.smartBird);
        this.createMultiPlayerBirds();
    }

    private void createMultiPlayerBirds() {
        for (int i = 0; i < this.smartArrayList.size(); i++) {
          //  ManualBird smartBirds = new ManualBird(this.gamePane,pipes);
           // this.smartArrayList.add(smartBirds);
           // this.smartBird = smartBirds;
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

    }
    @Override
    public void createBirds(){

    }
}
