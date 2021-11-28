package evolution.snake;

import java.util.ArrayList;

/**
 * Represents the snake moving around the board that can change direction,
 * move across the board, and grow in length whenever it moves into a square
 * with food.
 */
public class Snake {

    private ArrayList<BoardSquare> snakeTiles;
    private Board board;
    /*
    keep track of next direction and direction currently moving to account for edge case
    where user quickly presses two direction keys to move 180º which is not legal
     */
    private Direction directionMoving;
    private Direction nextDirection;

    /**
     * Constructs the snake with its initial position in the left middle of the board.
     *
     * @param board the game board of squares
     */
    public Snake(Board board) {
        this.board = board;
        this.snakeTiles = new ArrayList<>();
        this.directionMoving = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;

        /*
        initialize the snake graphically and logically based on coordinate constant,
        currently 3 squares in the left middle of the board
         */
        for (int[] coord : Constants.SNAKE_INITIAL_COORDINATES) {
            BoardSquare tile = this.board.tileAt(coord[0], coord[1]);
            tile.addSnake();
            this.snakeTiles.add(tile);
        }
    }

    /**
     * Changes the direction the snake is moving in, only if the direction
     * is not 180º from its current direction.
     *
     * @param dir desired direction to move the snake
     */
    public void changeDirection(Direction dir) {
        if (!(dir == this.directionMoving.opposite())) {
            this.nextDirection = dir;
        }
    }

    /**
     * Moves the snake one tile in the next direction it should move. If snakes moves off screen
     * or into tile already in snake body, returns game over result. Otherwise, the snake moves
     * successfully.
     *
     * @return SnakeMoveResult that captures state of move (game over or successful move) and the
     *          score increase obtained from move
     */
    public SnakeMoveResult move() {
        this.directionMoving = this.nextDirection;
        // uses direction to calculate the coordinates of tile snake moves into
        int newRow = this.directionMoving.newRow(this.snakeTiles.get(0).getRow());
        int newCol = this.directionMoving.newCol(this.snakeTiles.get(0).getCol());

        BoardSquare tile = this.board.tileAt(newRow, newCol);

        // snake doesn't move if tile is null (off screen) or already part of snake body
        if (tile == null || this.snakeTiles.contains(tile)) {
            return SnakeMoveResult.GAME_OVER;
        }

        if (tile.isEmpty()) {
            // only removes and resets the back tile if tile is empty
            BoardSquare backTile = this.snakeTiles.remove(this.snakeTiles.size() - 1);
            backTile.reset();
        }

        // add square to the front of the snake and mark square contents as snake
        this.snakeTiles.add(0, tile);
        SnakeMoveResult result = SnakeMoveResult.SUCCESS;
        result.setScoreIncrease(tile.addSnake());
        return result;
    }
}
