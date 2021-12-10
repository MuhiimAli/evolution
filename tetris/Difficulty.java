package evolution.tetris;

/**
 * This is the enum class, it represents the results of the easy, medium and hard buttons being clicked.
 */
public enum Difficulty {
    EASY, MEDIUM, HARD;//these are the enum constants

    public double setDifficulty() {
        switch (this) {
            case HARD:
                //this increases the speed by three on hard level
                return 3;

            case MEDIUM:
                //this increases the speed by 2 on medium level
                return 2;

            case EASY: default:
                //this increases the speed by 1 on easy level.
                return 1;
        }

    }






}