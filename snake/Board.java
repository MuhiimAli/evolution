package evolution.snake;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Represents the game board of squares.
 */
public class Board {

    private BoardSquare[][] tiles;
    private Pane gamePane;

    /**
     * Constructs the board logically and graphically.
     *
     * @param gamePane the pane on which to add the board
     */
    public Board(Pane gamePane) {
        this.gamePane = gamePane;
        this.setUpBoard();
        this.setSpawnFood();
    }
    public void setUpBoard(){
        this.tiles = new BoardSquare[Constants.NUM_ROWS][Constants.NUM_COLS];
        for (int row = 0; row < Constants.NUM_ROWS; row++) {
            for (int col = 0; col < Constants.NUM_COLS; col++) {
                if ((row + col) % 2 == 0) {
                    this.tiles[row][col] = new BoardSquare(this.gamePane, false, row, col);
                } else {
                    this.tiles[row][col] = new BoardSquare(this.gamePane, true, row, col);
                }
            }
        }

    }
    private void setSpawnFood(){
        for (int i = 0; i < Constants.NUM_FRUIT; i++) {
            this.spawnFood();
        }


    }

    /**
     * Retrieves the BoardSquare at particular indices of the board.
     *
     * @param row row of tile to get
     * @param col column of tile to get
     * @return the BoardSquare at the given indices, or null if indices are out of bounds
     */
    public BoardSquare tileAt(int row, int col) {
        if (row >= 0 && row < Constants.NUM_ROWS && col >= 0 && col < Constants.NUM_COLS) {
            return this.tiles[row][col];
        }
        return null;
    }

    /**
     * Spawns a random new food item on a random tile that is empty.
     */
    public void spawnFood() {
        BoardSquare tile = this.getRandomEmptyTile();
        Pellet pellet;
        switch ((int) (Math.random() * 10)) {
            case 0:
            case 1:
                pellet = new Pellet(this.gamePane, Color.GOLDENROD, Constants.FOOD_3_SCORE, tile.getRow(), tile.getCol());
                break;
            case 2:
                pellet = new Pellet(this.gamePane, Color.MINTCREAM, Constants.FOOD_4_SCORE, tile.getRow(), tile.getCol());
                break;
            case 3:
            case 4:
            case 5:
                pellet = new Pellet(this.gamePane, Color.BLACK, Constants.FOOD_2_SCORE, tile.getRow(), tile.getCol());
                break;
            default:
                pellet = new Pellet(this.gamePane, Color.RED, Constants.FOOD_1_SCORE, tile.getRow(), tile.getCol());
                break;
        }

        tile.addPellet(pellet);
    }

    /**
     * Gets a random tile that has no contents (no food or snake body)
     *
     * @return a random empty BoardSquare
     */
    private BoardSquare getRandomEmptyTile() {
        int row;
        int col;
        // generate random coordinates until the coordinates yield an empty square
        do {
            row = (int) (Math.random() * Constants.NUM_ROWS);
            col = (int) (Math.random() * Constants.NUM_COLS);
        } while (!this.tiles[row][col].isEmpty());

        return this.tiles[row][col];
    }
}
