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

    /**
     * this is our smart bird class,
     * it initializes the neural network class
     * it creates the input array and the weights
     * and takes care of everything a smart bird should have/know about
     */

    public SmartBird(Pane gamePane, PipeManager pipeManager){
        this.syn0 = new double[Constants.SYN0_ROW][Constants.SYN0_COL];//syn0= new double[hiddenNodes=3][inputNodes=4]/
        this.syn1 = new double[Constants.SYN1_ROW][Constants.SYN1_COL];//syn1=new double[outputNodes=1][hiddenNodes=3]
        this.setSyn0();
        this.setSyn1();
        this.brain=new NeuralNetwork(syn0, syn1);
        this.pipeManager=pipeManager;
        this.gamePane = gamePane;
        this.smartBird = new ManualBird(this.gamePane, pipeManager);
    }

    /**
     * @param gamePane
     * @param pipeManager
     * @param syn0
     * @param syn1
     * this method takes in syn0 and syn1 because we want to pass down the weights of the "elite birds" to the next generation
     */
    public SmartBird(Pane gamePane, PipeManager pipeManager, double[][] syn0, double[][]syn1){
        this.syn0 = syn0;
        this.syn1 =syn1;
        this.brain=new NeuralNetwork(syn0, syn1);
        this.pipeManager=pipeManager;
        this.gamePane = gamePane;
        this.smartBird = new ManualBird(this.gamePane, pipeManager);
    }

    /**
     * this method makes the bird jump when it is less than 0.5
     */
    public void reactAtOutputValue(){
        double[][] outputs=this.brain.forwardPropagation(this.createInputNodes());
        if(outputs[0][0]>0.5) {//the bird will flap
            this.smartBird.jump();
        }
        else {
                this.smartBird.updateBirdVelocity();//the bird will fall down
        }
    }

    /**
     * this method initializes the input array and creates its values
     * @return
     */
    private double[][] createInputNodes() {
        this.inputArray = new double[Constants.INPUT_ROW][Constants.INPUT_COL];//this instantiates the input array
        double birdYLocation = this.smartBird.getYLoc();
        double nearestPipeXLoc = this.pipeManager.nearestPipe().getXLoc();//the x location of the nearest pipe
        double topPipeHeight=this.pipeManager.nearestPipe().getTopPipeHeight();
        double bottomPipeHeight=this.pipeManager.nearestPipe().getBottomPipeHeight();
        inputArray[0][0] = birdYLocation / Constants.GAME_PANE_HEIGHT;//y location of the bird
        inputArray[1][0] = nearestPipeXLoc / Constants.GAME_PANE_WIDTH;//x location of the nearest pipe
        inputArray[2][0] = bottomPipeHeight/Constants.GAME_PANE_HEIGHT;//height of the bottom pipe
        inputArray[3][0] = topPipeHeight/Constants.GAME_PANE_HEIGHT;//the height of the top pipe
        return inputArray;

    }

    /**
     * this method removes the bird graphically when it intersects with a pipe or falls down
     * @return returns true if the bird intersects with the pipes or falls down
     */
    public boolean checkIntersection(){
        if(this.smartBird.checkIntersection(pipeManager.nearestPipe(),pipeManager.nearestPipe())){
            this.smartBird.removeFromPane();
            return true;

        }
        return false;
    }

    /**
     * this method removes the smart bird graphically
     */
    public void removeFromPane(){
        this.smartBird.removeFromPane();
    }

    /**
     * a getter method that returns syn0
     * @return
     */
    public double[][] getSyn0(){
        return this.syn0;
    }

    /**
     * a getter method that returns syn1
     * @return
     */
    public  double[][] getSyn1(){
        return this.syn1;
    }

    /**
     * the method makes a copy of a 2D array
     * @param copyArray
     * @return
     */
    public double[][] copy(double[][] copyArray){
        double[][] newMatrix= new double[copyArray.length][copyArray[0].length];
        for(int i=0; i<copyArray.length; i++){
            for(int j=0; j< copyArray[0].length;j++ ){
                newMatrix[i][j]=copyArray[i][j];
            }
        }
        return newMatrix;
    }

    /**
     * this method assigns syn0 to random values between -1 and 1
     */
    private void setSyn0() {
        for (int row = 0;row < this.syn0.length; row++) {
            for (int col = 0; col < this.syn0[0].length; col++) {
                this.syn0[row][col]=Math.random()*2-1;//randomizing the syn0 weights
            }
        }
    }

    /**
     * initializing the weights(syn1) with random values between -1 and 1
     */
    private void setSyn1() {
        for (int row = 0; row < this.syn1.length; row++) {
            for (int col = 0; col < this.syn1[0].length; col++) {
                this.syn1[row][col] =Math.random()*2-1;//randomizing the syn1 weights
            }
        }

    }

    /**
     * this method mutates the values of syn0 that are less than the mutation rate
     * We mutate because we don't want all the birds to learn and act the same
     */
    public void mutateSynO(){
        for(int i=0;i<this.syn0.length; i++ ){
            for(int j=0;j<this.syn0[0].length;j++)
            if(Math.random()<Constants.MUTATION_RATE){//checks if math.random() is less than the mutation rate
                this.syn0[i][j]=Math.random()*2-1;//mutates the weight
            }
        }
    }

    /**
     * this method mutates the values of syn1 that are less than the mutation rate
     */
    public void mutateSyn1(){
        for(int i=0;i<this.syn1.length; i++ ){
            for(int j=0;j<this.syn1[0].length;j++)
                if(Math.random()<Constants.MUTATION_RATE){
                    this.syn1[i][j]=Math.random()*2-1;
                }
        }
    }





}


