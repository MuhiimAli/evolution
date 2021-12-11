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

public class Population implements Flappable{
    private ArrayList<SmartBird> populationArraylist, deadBirdsArraylist;
    private SmartBird smartBirds;
    private Pane gamePane;
    private PipeManager pipeManager;
    private int Gens;
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
    private Timeline timeline;
    private SpeedLevels speedLevels;




    public Population(Pane gamePane, PipeManager pipeManager, Pane scorePane, Timeline timeline){
        this.scorePane=scorePane;
        this.fitness=0;
        this.bestFitnessLastGen=0;
        this.bestFitnessAllTimes=0;
        this.gamePane=gamePane;
        this.pipeManager=pipeManager;
        this.timeline=timeline;
        this.scorePane.getChildren().clear();
        this.populationArraylist = new ArrayList<>();
        this.deadBirdsArraylist = new ArrayList<>();
        this.smartBirds=new SmartBird(this.gamePane,pipeManager);//instantiates the smart bird
        this.populationArraylist.add(this.smartBirds);
        this.labelPane=new VBox();
        this.speedPane=new HBox();
        this.alive=new Label();
        this.bestFitness=new Label();
        this.bestFitnessAllTime=new Label();
        this.currentFitness=new Label();
        this.generation=new Label();
        this.speedLevels=SpeedLevels.SPEED1;
        this.scorePane.getChildren().addAll(speedPane, labelPane);
        labelPane.getChildren().addAll(alive, bestFitness, bestFitnessAllTime, generation, currentFitness);
        speedPane.setAlignment(Pos.TOP_RIGHT);
        this.createSmartBirds();
        this.statsBar();

    }

    /**
     * creates 50 birds with random weights
     */
    private void createSmartBirds(){
        for(int i=0;i<49;i++){
            SmartBird newSmartBirds= new SmartBird(this.gamePane, this.pipeManager);
            this.populationArraylist.add(newSmartBirds);
            this.smartBirds=newSmartBirds;
        }

    }

    /**
     * this method creates 50 birds
     */
    public void nextGeneration(){//todo use a selection rate
        Gens++;
        for(int i=0; i< 50; i++) {
                if (i % 3 == 0) {
                    double[][] syn0 = this.deadBirdsArraylist.get(47).copy(this.deadBirdsArraylist.get(47).getSyn0());
                    double[][] syn1 = this.deadBirdsArraylist.get(47).copy(this.deadBirdsArraylist.get(47).getSyn1());
                    SmartBird groupOne = new SmartBird(this.gamePane, this.pipeManager, syn0, syn1);
                    groupOne.mutateSynO();
                    groupOne.mutateSyn1();
                    this.populationArraylist.add(groupOne);
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
        this.deadBirdsArraylist.clear();

    }
//
    public void calculateFitness(){//update this with the timeline
        fitness++;
    }
    public void createNextGeneration(){
        if (this.populationArraylist.size() == 0) {//checks if the arraylist is empty
            fitness=0;
            System.out.println("empty");
            this.pipeManager.removeFromPane();//removes the pipes graphically
            this.pipeManager.removeLogically();//removes the pipes logically
            this.pipeManager.generatePipes();//generates new pipes
            this.nextGeneration();//creates the next generation

        }

    }

    @Override
    public Boolean  gameOver(){
            return false;
    }
    @Override
    public void handleKeyPress(KeyEvent e){

    }
    public void collisionDetection(){
        for(int i=0;i<this.populationArraylist.size(); i++) {
            SmartBird deadBirds = this.populationArraylist.get(i);
            //System.out.println(this.populationArraylist.size());
            if (this.populationArraylist.get(i).checkIntersection()) {
                this.deadBirdsArraylist.add(deadBirds);
                this.populationArraylist.get(i).removeFromPane();
                this.populationArraylist.remove(i);
            }
        }
    }
    @Override
    public void updateWithTimeline(){
        this.statsLabel();
        this.calculateFitness();
        for(int i=0;i<this.populationArraylist.size(); i++){
            this.populationArraylist.get(i).reactAtOutputValue();
        }
        this.createNextGeneration();
        this.collisionDetection();


    }
    private void statsBar() {
        Button button1 = new Button("1x");
        Button button2 = new Button("2x");
        Button button3 = new Button("5x");
        Button button4 = new Button("Max");
        button1.setOnAction((ActionEvent e) ->this.switchSpeedLevels(SpeedLevels.SPEED1));
        button2.setOnAction((ActionEvent e) ->this.switchSpeedLevels(SpeedLevels.SPEED2));
        button3.setOnAction((ActionEvent e) ->this.switchSpeedLevels(SpeedLevels.SPEED3));
        button4.setOnAction((ActionEvent e) ->this.switchSpeedLevels(SpeedLevels.SPEED_MAx));
        button1.setFocusTraversable(false);
        button2.setFocusTraversable(false);
        button3.setFocusTraversable(false);
        button4.setFocusTraversable(false);
        speedPane.getChildren().addAll(button1, button2, button3, button4);
    }
    public void statsLabel(){
        this.alive.setText("Alive: "+ this.populationArraylist.size());//this.smartArraylist.size
        this.bestFitnessLastGen=Math.max(this.fitness, this.bestFitnessLastGen);
        this.bestFitness.setText("Best Fitness Last Gen: " + bestFitnessLastGen );
        this.bestFitnessAllTimes=Math.max(this.bestFitnessAllTimes, this.bestFitnessLastGen);
        this.bestFitnessAllTime.setText("Best Fitness All Time: "+ this.bestFitnessAllTimes);
        this.generation.setText("Gen: "+ this.Gens);
        this.currentFitness.setText("Current Fitness:" + fitness);
    }
    public void switchSpeedLevels(SpeedLevels speedLevels){
        this.timeline.setRate(speedLevels.setSpeed());

    }






}
