package evolution.flappyBird;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class PipeManager {
    private Pipe pipes;
    private ArrayList<Pipe> pipeArraylist;
    private Pane gamePane;
    public PipeManager(Pane gamePane){
        this.gamePane=gamePane;
        this.pipeArraylist = new ArrayList<>();
        this.pipes = new Pipe(this.gamePane);
        this.pipeArraylist.add(this.pipes);
    }

    /**
     * this method generates pipes
     */
    public void generatePipes() {
        while (this.pipes.getXLoc() <= Constants.GAME_PANE_WIDTH) {
            Pipe newPipes = new Pipe(this.gamePane);
            newPipes.setXLoc(this.pipes.getXLoc()+Constants.PIPE_HORIZONTAL_DISTANCE);
            this.pipeArraylist.add(newPipes);
            this.pipes = newPipes;
        }
    }

    /**
     * this method makes the pipes scroll across the screen at a constant rate
     */
    public void scrollPipes(){
        for(int i=0;i<this.pipeArraylist.size();i++) {
            this.pipeArraylist.get(i).setXLoc(this.pipeArraylist.get(i).getXLoc()-Constants.SCROLL_RATE);
        }
        this.generatePipes();


    }

    /**
     * this method deletes the pipes when they're done scrolling, when they're off the screen
     */
    public void deletePipes(){
        if (this.pipeArraylist.get(0).getXLoc() <=-Constants.PIPE_WIDTH){
            this.pipeArraylist.get(0).removeFromPane();
            this.pipeArraylist.remove(0);
        }
    }

    /**
     * this method keeps track of the nearest Pipe
     * @return the pipe that is closest to the bird
     */
    public Pipe nearestPipe(){
       // System.out.println(this.pipeArraylist.size());
        return this.pipeArraylist.get(0);
    }
    public void removeFromPane(){
        for(int i=0;i<this.pipeArraylist.size(); i++){
            this.pipeArraylist.get(i).removeFromPane();
        }
    }
    public void removeLogically(){
        for(int i=0;i<this.pipeArraylist.size(); i++){
            this.pipeArraylist.remove(i);
        }
    }

}
