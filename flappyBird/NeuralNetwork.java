package evolution.flappyBird;

import javafx.scene.layout.Pane;

public class NeuralNetwork {
    private int[] inputNodes;
    private Weights weights;
    private SmartBird smartBird;

    public NeuralNetwork(Pane gamePane,PipeManager pipeManager) {
        this.weights = new Weights();
        this.smartBird=new SmartBird(gamePane, pipeManager);
        //input nodes[the y location of the bird, the length of the gap, the x location of the nearest Pipe]

    }

    public double[][] dotProduct(double[][] A, double[] B) {
        for (int a = 0; a < A.length; a++)
            if (A[0].length == B.length) {//checks if the col of A, are equal to the rows of B
                double[][] C = new double[A.length][1];//creates an empty array
                for (int row = 0; row < A.length; row++) {//for each row in A
                    for (int i = 0; i < B.length; i++) {
                        C[row][1] += A[row][i] * B[i];
                    }

                }

                return C;
            }
        return null;




    }
//    public double forwardPropagation(double[] inputNodes){
//        double[][] hiddenLayer=this.dotProduct(this.weights.getSyn0(),inputNodes);
//
//    }

}
