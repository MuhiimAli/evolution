package evolution.snake;

/**
 * Represents the result of the snake moving one square, with values GAME_OVER
 * and SUCCESS, and an associated score based on any food eaten on the move.
 */
public enum SnakeMoveResult {
    GAME_OVER, SUCCESS;

    private int scoreIncrease;

    /**
     * Sets the score increase of the move result. If the result was GAME_OVER,
     * the score increase must be 0.
     *
     * @param scoreIncrease the amount to add to the score
     */
    public void setScoreIncrease(int scoreIncrease) {
        if (this == GAME_OVER) {
            this.scoreIncrease = 0;
        } else {
            this.scoreIncrease = scoreIncrease;
        }
    }

    /**
     * Gets the score increase of the move result.
     *
     * @return the amount to add to the score
     */
    public int getScoreIncrease() {
        return this.scoreIncrease;
    }
}
