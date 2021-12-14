package evolution.flappyBird;

public enum SpeedLevels {
    /**
     * it sets the speed of the timeline based on the enum value
     */
    SPEED1("1x"), SPEED2("2x"), SPEED5("5x"), MAx("Max");
    private String name;

    public double setSpeed() {
        switch (this) {
            case SPEED1:
                 return 1/Constants.DURATION;
            case SPEED2:
                return 2/Constants.DURATION;
            case SPEED5:
               return 5/Constants.DURATION;
            case MAx: default:
                return 25/Constants.DURATION;
        }

    }
    SpeedLevels(String name){
        this.name=name;
    }

    /**
     * @return returns the enum constant
     */
    public String getName(){
        return this.name;
    }
}
