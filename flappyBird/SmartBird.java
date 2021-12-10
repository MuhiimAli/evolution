package evolution.flappyBird;

import javafx.scene.layout.Pane;

public class SmartBird{
    private Pane gamePane;
    private ManualBird smartBird;
    private NeuralNetwork brain;
    private PipeManager pipeManager;
    private double[][] inputArray;
    private double[][] syn0;
    private double[][] syn1;
    public SmartBird(Pane gamePane, PipeManager pipeManager){
        this.syn0 = new double[Constants.SYN0_ROW][Constants.SYN0_COL];//syn0= new double[hiddenNodes=3][inputNodes=4]//todo const
        this.syn1 = new double[Constants.SYN1_ROW][Constants.SYN1_COL];//syn1=new double[outputNodes=1][hiddenNodes=3]
        this.setSyn0();
        this.setSyn1();
        this.brain=new NeuralNetwork(syn0, syn1);
        this.pipeManager=pipeManager;
        this.gamePane = gamePane;
        this.smartBird = new ManualBird(this.gamePane, pipeManager);


    }
    public SmartBird(Pane gamePane, PipeManager pipeManager, double[][] syn0, double[][]syn1){//todo am not really passing down the weights
        this.syn0 = syn0;
        this.syn1 =syn1;
        this.brain=new NeuralNetwork(syn0, syn1);
        this.pipeManager=pipeManager;
        this.gamePane = gamePane;
        this.smartBird = new ManualBird(this.gamePane, pipeManager);
    }

    public void reactAtOutputValue(){
        double[][] outputs=this.brain.forwardPropagation(this.createInputNodes());
        if(outputs[0][0]>0.5) {//the bird will flap
            this.smartBird.jump();
        }
        else {
                this.smartBird.updateBirdVelocity();
        }
    }
    private double[][] createInputNodes() {
        this.inputArray = new double[Constants.INPUT_ROW][Constants.INPUT_COL];//this instantiates the input array
        double birdYLocation = this.smartBird.getYLoc();
        double nearestPipeXLoc = this.pipeManager.nearestPipe().getXLoc();
        double topPipeHeight=this.pipeManager.nearestPipe().getTopPipeHeight();
        double bottomPipeHeight=this.pipeManager.nearestPipe().getBottomPipeHeight();
        inputArray[0][0] = birdYLocation / Constants.GAME_PANE_HEIGHT;//todo figure out why this value is negative
        inputArray[1][0] = nearestPipeXLoc / Constants.GAME_PANE_WIDTH;
        inputArray[2][0] = bottomPipeHeight/Constants.GAME_PANE_HEIGHT;//height of the bottom pipe
        inputArray[3][0] = topPipeHeight/Constants.GAME_PANE_HEIGHT;//the height of the top pipe
        return inputArray;

    }
    public double getYLoc(){
        return this.smartBird.getYLoc();

    }
    public void setYLoc(double y){
        this.smartBird.setYLoc(y);

    }
    public boolean checkIntersection(){
        if(this.smartBird.checkIntersection(pipeManager.nearestPipe(),pipeManager.nearestPipe())){
            this.smartBird.removeFromPane();
            return true;

        }
        return false;
    }
    public void removeFromPane(){
        this.smartBird.removeFromPane();
    }
    public double[][] getSyn0(){
        return this.syn0;
    }
    public  double[][] getSyn1(){
        return this.syn1;
    }
    public double[][] copy(double[][] copyArray){
        double[][] newMatrix= new double[copyArray.length][copyArray[0].length];
        for(int i=0; i<copyArray.length; i++){
            for(int j=0; j< copyArray[0].length;j++ ){
                newMatrix[i][j]=copyArray[i][j];
            }
        }
        return newMatrix;
    }
    private void setSyn0() {
        for (int row = 0;row < this.syn0.length; row++) {
            for (int col = 0; col < this.syn0[0].length; col++) {
                this.syn0[row][col]=Math.random()*2-1;//randomizing the syn0 weights
            }
        }
    }
    private void setSyn1() {
        for (int row = 0; row < this.syn1.length; row++) {
            for (int col = 0; col < this.syn1[0].length; col++) {
                this.syn1[row][col] =Math.random()*2-1;//randomizing the syn1 weights
            }
        }

    }
    public void mutateSynO(){
        for(int i=0;i<this.syn0.length; i++ ){
            for(int j=0;j<this.syn0[0].length;j++)
            if(Math.random()<Constants.MUTATION_RATE){
                this.syn0[i][j]=Math.random()*2-1;
            }
        }
    }
    public void mutateSyn1(){
        for(int i=0;i<this.syn1.length; i++ ){
            for(int j=0;j<this.syn1[0].length;j++)
                if(Math.random()<Constants.MUTATION_RATE){
                    this.syn1[i][j]=Math.random()*2-1;
                }
        }
    }





}


