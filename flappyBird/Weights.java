package evolution.flappyBird;

public class Weights {
    private double[][] syn0;
    private double[][] syn1;
    //private SmartBird;

    public Weights() {
        this.syn0 = new double[3][4];// the row will depend on how many nodes you want to have in your hidden layer
        this.syn1 = new double[3][4];
        this.setSyn0();
       this.setSyn1();

    }

    private void setSyn0() {
        int smallestWeight = -1;
        int biggestWeight = 1;
        int syn0Weights = smallestWeight + (int) ((biggestWeight - smallestWeight + 1) * Math.random());
        for (int row = 0;row < this.syn0.length; row++) {
            for (int col = 0; col < this.syn0[0].length; col++) {
                this.syn0[row][col] = syn0Weights;

            }
        }
    }

    private void setSyn1() {
        int smallestWeight = -1;
        int biggestWeight = 1;
        int syn1Weights = smallestWeight + (int) ((biggestWeight - smallestWeight + 1) * Math.random());
        for (int row = 0; row < this.syn1.length; row++) {
            for (int col = 0; col < this.syn1[0].length; col++) {
                this.syn0[row][col] = syn1Weights;
            }
        }
    }
    public double[][] getSyn0(){
        return this.syn0;
    }
    public double[][] getSyn1(){
        return this.syn1;
    }





}
