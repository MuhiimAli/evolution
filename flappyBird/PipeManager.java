package evolution.flappyBird;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

/**
 * this class manages the pipes.
 * It generates them, makes them scroll and deletes them
 */
public class PipeManager {
    private Pipe pipes;
    private ArrayList<Pipe> pipeArraylist;
    private Pane gamePane;
    /**
     * In the constructor method, I associate the gamePane with the PipeManager class
     * I create an instance of the pipe class and a new arraylist
     * @param gamePane
     */
    public PipeManager(Pane gamePane){
        this.gamePane=gamePane;
        this.pipeArraylist = new ArrayList<>();//initializing the arraylist
        this.pipes = new Pipe(this.gamePane);
        this.pipeArraylist.add(this.pipes);//adding the pipe to the arraylist
    }
    /**
     * this method generates pipes
     */
    public Pipe generatePipes() {
        Pipe newPipes=null;
        while (this.pipes.getXLoc() <= Constants.GAME_PANE_WIDTH) {
            newPipes = new Pipe(this.gamePane);
            newPipes.setXLoc(this.pipes.getXLoc()+Constants.PIPE_HORIZONTAL_DISTANCE);
            this.pipeArraylist.add(newPipes);
            this.pipes = newPipes;
        }
        return newPipes;
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
     * this method deletes the pipes when they're off the screen
     */
    public void deletePipes(){
        for(int i=0;i<this.pipeArraylist.size();i++){
            if (this.pipeArraylist.get(i).getXLoc() <=-Constants.PIPE_WIDTH){
                this.pipeArraylist.get(i).removeFromPane();
                this.pipeArraylist.remove(i);
            }
        }
    }

    /**
     * this method keeps track of the nearest Pipe
     * @return the pipe that is closest to the bird
     */
    public Pipe nearestPipe(){
        return this.pipeArraylist.get(0);

    }

    /**
     * this method removes the pipes graphically
     */
    public void removeFromPane(){
        for(int i=0;i<this.pipeArraylist.size(); i++){
            this.pipeArraylist.get(i).removeFromPane();
        }
    }

    /**
     * this method deletes the pipes logically
     */
    public void removeLogically(){
       this.pipeArraylist.clear();//clears everything from the arraylist

        /*
        Because I created my first pipe in the constructor, I have to create a pipe here too or else I would run into
        array indexOutOfBoundsException whenever I'm trying to access my nearest pipe, which is at index 0.

         */
        this.pipeArraylist = new ArrayList<>();
        this.pipes = new Pipe(this.gamePane);
        this.pipeArraylist.add(this.pipes);
    }

}
