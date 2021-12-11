package evolution.flappyBird;

public enum SpeedLevels {
    SPEED1, SPEED2, SPEED3, SPEED_MAx;

    public double setSpeed() {
        switch (this) {
            case SPEED1:
                 return 1/Constants.DURATION;
            case SPEED2:
                return 2/Constants.DURATION;
            case SPEED3:
               return 3/Constants.DURATION;
            case SPEED_MAx: default:
                return 5/Constants.DURATION;
        }

    }
}
