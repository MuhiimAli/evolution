package evolution.flappyBird;

public class NeuralNetwork {
    private double[][] syn0;
    private double[][] syn1;



    public NeuralNetwork(double[][] syn0, double[][] syn1) {
        this.syn0=syn0;
        this.syn1=syn1;

    }


    public double[][] dotProduct(double[][] A, double[][]B) {
        int m = A.length;//rows of A
        int n = A[0].length;// cols of A
        int p = B.length;// rows of B
        int q = B[0].length;//the cols of B
        if (n == p) {//checks if the cols of A, are equal to the rows of B
            double[][] newMatrix = new double[m][q];//creates an empty array
            for (int row = 0; row < m; row++) {//for each row in A from 0 to m
                for (int col = 0; col < q; col++) { //for each col in B from 0 to q
                    for (int i = 0; i < p; i++) {//for each row in B
                        newMatrix[row][col] += A[row][i] * B[i][col];
                    }

                     }
                }
                return newMatrix;
            }
            return null;

    }
    public double[][] forwardPropagation(double[][] inputNodes){
        double[][] hiddenLayer=this.dotProduct(this.syn0,inputNodes);//this creates the hidden layer
        for(int row=0;row<hiddenLayer.length;row++){//hiddenLayer.length=3
            for(int col = 0; col<hiddenLayer[0].length;col++){//col=1
               hiddenLayer[row][col]=this.sigmoid(hiddenLayer[row][col]);//this activates the hidden layer (returns values between 0 and 1)
            }
            }
        double[][] outputNode=this.dotProduct(this.syn1, hiddenLayer);

        for(int row=0;row< outputNode.length; row++){//for each row in the output layer
            for(int col=0;col<outputNode[0].length; col++){//for each col in the output layer
                outputNode[row][col]=this.sigmoid(outputNode[row][col]);//this activates the output layer
            }
        }
        return outputNode;
    }

    /**
     * this method creates the sigmoid funtion
     * @param x
     * @return
     */
    private double sigmoid(double x){
        return 1/(1+Math.exp(-x));
    }



}
