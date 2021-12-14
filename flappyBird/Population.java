package evolution.flappyBird;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
/**
 * this is my population class, which contains the smart Bird class
 * it first creates 50 birds with random weights
 * and for each subsequent generation, the weights of the "elite birds" are passed down to the next generation
 *and the weights of the children are then mutated so that they don't act and learn the same way
 */
public class Population implements Flappable{
    private ArrayList<SmartBird> populationArraylist, deadBirdsArraylist;
    private Pane gamePane;
    private PipeManager pipeManager;
    private int gens;
    private int fitness;
    private int bestFitnessLastGen;
    private Pane scorePane;
    private Pane labelPane;
    private HBox speedPane;
    private Label alive;
    private Label bestFitness;
    private Label bestFitnessAllTime;
    private int bestFitnessAllTimes;
    private Label currentFitness;
    private Label generation;
    private Label avgFitness;
    private Timeline timeline;
   // private int[] AvgFitness;
    private int sum;

    /**
     * I associated this gamePane, PipeManager class, scorePane and the timeline with this class
     * the constructor instantiates multiple labels for the stats bar
     * it creates the population arraylist and deadBird arraylist
     * @param gamePane
     * @param pipeManager
     * @param scorePane
     * @param timeline
     */
    public Population(Pane gamePane, PipeManager pipeManager, Pane scorePane, Timeline timeline){
        this.scorePane=scorePane;
        this.fitness=0;
        this.bestFitnessLastGen=0;
        this.bestFitnessAllTimes=0;
        this.sum=0;
        this.gamePane=gamePane;
        this.pipeManager=pipeManager;
        this.timeline=timeline;
        this.scorePane.getChildren().clear();
        this.populationArraylist = new ArrayList<>();//initializing an arraylist
        this.deadBirdsArraylist = new ArrayList<>();
      //  this.AvgFitness=new int[50];
        this.labelPane=new VBox();//initializing a VBox
        this.speedPane=new HBox();
        this.alive=new Label();//initializing a label
        this.bestFitness=new Label();
        this.bestFitnessAllTime=new Label();
        this.currentFitness=new Label();
        this.generation=new Label();
        this.avgFitness=new Label();
        this.scorePane.getChildren().addAll(speedPane, labelPane);//adding the HBox and VBox to the pane
        labelPane.getChildren().addAll(this.alive, this.bestFitness, this.bestFitnessAllTime, this.avgFitness, this.generation, this.currentFitness);//adding all the stats label to the pane
        speedPane.setAlignment(Pos.TOP_RIGHT);
        this.createSmartBirds();
        this.statsBar();
    }
    /**
     * creates 50 birds with random weights
     */
    private void createSmartBirds(){
        for(int i=0;i<50;i++){
            SmartBird newSmartBirds= new SmartBird(this.gamePane, this.pipeManager);//creates the smart birds
            this.populationArraylist.add(newSmartBirds);//adds the smart birds to the arraylist
        }
    }
    /**
     * this method creates 50 birds
     * gets the weights of the best-performing birds and pass them down to the next generation
     * And then slightly mutates the weights of the children so that they learn over time
     */
    public void nextGeneration(){
        this.gens++;
        for(int i=0; i< 50; i++) {
                if (i % 3 == 0) {
                    /*
                    gets a copies of the weights of the fittest birds
                     */
                    double[][] syn0 = this.deadBirdsArraylist.get(47).copy(this.deadBirdsArraylist.get(47).getSyn0());
                    double[][] syn1 = this.deadBirdsArraylist.get(47).copy(this.deadBirdsArraylist.get(47).getSyn1());
                    SmartBird groupOne = new SmartBird(this.gamePane, this.pipeManager, syn0, syn1);
                     /*
                    mutates the weights of the children
                     */
                    groupOne.mutateSynO();
                    groupOne.mutateSyn1();
                    this.populationArraylist.add(groupOne);//adds the new birds to the arraylist
                } else if (i % 3 == 1) {
                    double[][] syn0 = this.deadBirdsArraylist.get(48).copy(this.deadBirdsArraylist.get(48).getSyn0());
                    double[][] syn1 = this.deadBirdsArraylist.get(48).copy(this.deadBirdsArraylist.get(48).getSyn1());
                    SmartBird groupTwo = new SmartBird(this.gamePane, this.pipeManager, syn0, syn1);
                    groupTwo.mutateSynO();
                    groupTwo.mutateSyn1();
                    this.populationArraylist.add(groupTwo);
                } else {
                    double[][] syn0 = this.deadBirdsArraylist.get(49).copy(this.deadBirdsArraylist.get(49).getSyn0());
                    double[][] syn1 = this.deadBirdsArraylist.get(49).copy(this.deadBirdsArraylist.get(49).getSyn1());
                    SmartBird groupThree = new SmartBird(this.gamePane, this.pipeManager, syn0, syn1);
                    groupThree.mutateSynO();
                    groupThree.mutateSyn1();
                    this.populationArraylist.add(groupThree);
            }
        }
        this.deadBirdsArraylist.clear();//clears the dead bird arraylist
    }

    /**
     * this method keeps track of the birds' fitness
     * the bird's fitness increases by 1 at each iteration of the timeline
     */
    public void fitness(){//update this with the timeline
        this.fitness++;
    }

    /**
     * this method calls the nextGeneration method when the all the birds from previous generation die
     */
    public void createNextGeneration() {
        if (this.populationArraylist.size() == 0){//checks if the arraylist is empty
            if (this.fitness >Constants.GEN0_PASS) {//400 is how long it takes a bird to get to the second pipe
                this.fitness = 0;
                this.pipeManager.removeFromPane();//removes the pipes graphically
                this.pipeManager.removeLogically();//removes the pipes logically
                this.pipeManager.generatePipes();//generates new pipes
                this.nextGeneration();//creates the next generation
            } else {
                /*
                this clears the population arraylist if none of the
                birds from gen 0 didn't go through at least one pipe
                and creates a new generation with random weights
                 */
                this.populationArraylist.clear();
                this.deadBirdsArraylist.clear();
                this.pipeManager.removeFromPane();//removes the pipes graphically
                this.pipeManager.removeLogically();//removes the pipes logically
                this.pipeManager.generatePipes();//generates new pipes
                this.createSmartBirds();//creates new birds
            }
    }

    }

    /**
     * this method gets rid of the optimal birds
     */
    public void killGeniusBird(){
        if(this.fitness>=10000){
            for(int i=0;i<this.populationArraylist.size(); i++){
                SmartBird deadBirds = this.populationArraylist.get(i);
                this.deadBirdsArraylist.add(deadBirds);
                this.populationArraylist.get(i).removeFromPane();
                this.populationArraylist.remove(i);
            }
        }

    }

     /**
     *there is no true 'game over' for smart Flappy Bird since each time all the birds from generation die, we create
      * a new generation
     */
    @Override
    public Boolean  gameOver(){
        return false;
    }

    /**
     * Because this class implements the flappable interface,
     * I had to define the handleKeypress too even though I didn't need it.
     *
     * @param e
     */
    @Override
    public void handleKeyPress(KeyEvent e){
    }
    /**
     * this method removes the birds graphically and logically when they intersect with the pipes or fall down
     */
    public void collisionDetection(){
        for(int i=0;i<this.populationArraylist.size(); i++) {
            SmartBird deadBirds = this.populationArraylist.get(i);//creates a deadBird arraylist
            if (this.populationArraylist.get(i).checkIntersection()) {
                this.sum+=this.fitness;//the summation of all the birds' fitness.
              /*
              this adds the birds to the deadBirdArraylist if they collide with a pipe or fall down
               */
                this.deadBirdsArraylist.add(deadBirds);
                this.populationArraylist.get(i).removeFromPane();//removes the birds that collide with the pipe graphically
                this.populationArraylist.remove(i);//this removes the birds logically
            }
        }
    }

    /**
     * this method gets updated with the timeline
     */
    @Override
    public void updateWithTimeline(){
        this.statsLabel();
        this.fitness();
        for(int i=0;i<this.populationArraylist.size(); i++){
            this.populationArraylist.get(i).reactAtOutputValue();
        }
        this.createNextGeneration();
        this.collisionDetection();
        this.killGeniusBird();
    }

    /**
     * this method creates the speed buttons.
     */
    private void statsBar() {
        for(SpeedLevels speedLevels: SpeedLevels.values()) {
            Button button1 = new Button(speedLevels.getName());
            button1.setOnAction((ActionEvent e) -> this.switchSpeedLevels(speedLevels));
            button1.setFocusTraversable(false);
            speedPane.getChildren().addAll(button1);
        }

    }

    /**
     * changes the rate of the timeline
     * @param speedLevels takes an enum
     */
    public void switchSpeedLevels(SpeedLevels speedLevels){
        this.timeline.setRate(speedLevels.setSpeed());
    }
    /**
     * this method creates all the labels in the stats bar.
     */
    public void statsLabel(){
        this.alive.setText("Alive: "+ this.populationArraylist.size());
        this.bestFitnessLastGen=Math.max(this.fitness, this.bestFitnessLastGen);
        this.bestFitness.setText("Best Fitness Last Gen: " + bestFitnessLastGen );
        this.bestFitnessAllTimes=Math.max(this.bestFitnessAllTimes, this.bestFitnessLastGen);
        this.bestFitnessAllTime.setText("Best Fitness All Time: "+ this.bestFitnessAllTimes);
        this.avgFitness.setText("AvgFitness: ");
        this.generation.setText("Gen: "+ this.gens);
        this.currentFitness.setText("Current Fitness:" + fitness);
    }







}
