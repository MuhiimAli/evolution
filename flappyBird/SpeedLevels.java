package evolution.flappyBird;

public enum SpeedLevels {
    SPEED1("1x"), SPEED2("2x"), SPEED3("3x"), SPEED_MAx("Max");
    private String name;

    public double setSpeed() {
        switch (this) {
            case SPEED1:
                 return 1/Constants.DURATION;
            case SPEED2:
                return 2/Constants.DURATION;
            case SPEED3:
               return 3/Constants.DURATION;
            case SPEED_MAx: default:
                return 7/Constants.DURATION;
        }

    }
    SpeedLevels(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
