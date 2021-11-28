package evolution.snake;

/**
 * Enum to represent direction of movement, with values UP, DOWN, LEFT, and
 * RIGHT.
 */
public enum Direction {

    UP, DOWN, LEFT, RIGHT;

    /**
     * Gets the opposite direction.
     *
     * @return the direction 180º away from direction
     */
    public Direction opposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            default:
                return LEFT;
        }
    }

    /**
     * Calculates the row index after one movement, given the current row index
     * and the current direction of movement. If the direction is left or right,
     * the output index will be the same as the current index.
     *
     * @param currRow the current row index
     * @return row index after moving one square in current direction
     */
    public int newRow(int currRow) {
        switch (this) {
            case UP:
                return currRow - 1;
            case DOWN:
                return currRow + 1;
            default:
                return currRow;
        }
    }

    /**
     * Calculates the column index after one movement, given the current column index
     * and the current direction of movement. If the direction is up or down,
     * the output index will be the same as the current index.
     *
     * @param currCol the current column index
     * @return column index after moving one square in current direction
     */
    public int newCol(int currCol) {
        switch (this) {
            case LEFT:
                return currCol - 1;
            case RIGHT:
                return currCol + 1;
            default:
                return currCol;
        }
    }
}
