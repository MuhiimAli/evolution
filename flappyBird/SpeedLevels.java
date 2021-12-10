package evolution.flappyBird;

public enum SpeedLevels {
    SPEED1, SPEED2, SPEED3, SPEED_MAx;

    public double setSpeed(double x) {
        switch (this) {
            case SPEED1:
                 return x*2.5;
            case SPEED2:
                return  x*3.5;
            case SPEED3:
               return 4.5;
            case SPEED_MAx: default:
                return 5.5;
        }

    }
}
