package evolution.tetris;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


/**
 * This is the board class, it is made up of squares of type BoardSquares. It is the container of all the pieces in the game.
 * This class is created to create a layer of abstraction and implement loose coupling.
 * Methods that are only relevant to the board are defined and implemented here.
*/


public class Board {
    private Pane gamePane;
    private BoardSquare[][] board;//declaring the 2D array


    /**
     * * This is the board class' constructor. It sets up the board.
     * @param gamePane the gamePane is associated with the board
     */

    public Board(Pane gamePane) {
        this.gamePane = gamePane;
        this.setUpBoard();
    }

    /**
     * this method sets up the board
     */

    public void setUpBoard() {
        this.board = new BoardSquare[Constants.NUM_ROWS][Constants.NUM_COLS];
        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < Constants.NUM_COLS; col++) {
                if (row < 1 || row == Constants.NUM_ROWS - 1 || col == 0 || col == Constants.NUM_COLS - 1) {
                    this.board[row][col] = new BoardSquare(this.gamePane, row, col);
                    this.board[row][col].setColor(Color.GREY);
                    this.board[row][col].addToPane();
                }
            }
        }
    }

    /**
     * this is a getter method that returns the 2D array
     * @return the board, which is the 2D array
     */

    public BoardSquare[][] getBoard() {

        return this.board;
    }

    /**
     * this method clear lines when the row is full
     */
    public void clearLine() {
        for (int row = 1; row < Constants.NUM_ROWS - 1; row++) {
            if (this.checkIfFull(row)) {
                    for (int col = 1; col < Constants.NUM_COLS - 1; col++) {
                        this.board[row][col].removeFromPane();
                    }
                    for (int i = row; i > 1; i--) {
                        for (int j = 1; j < Constants.NUM_COLS - 1; j++) {
                            if (this.board[i][j] != null) {
                                this.board[i][j].setYLoc(this.board[i][j].getYLoc() + Constants.SQUARE_SIZE);
                            }
                            this.board[i][j] = this.board[i- 1][j];
                        }
                  }
            }
        }
    }

    /**
     * this method checks if the row is full
     * @param row
     * @return
     */

    public boolean checkIfFull(int row) {
        for (int col = 0; col < Constants.NUM_COLS; col++) {
            if (this.board[row][col] == null) {
                return false;
            }

        }
        return true;

    }

    /**
     * This method gets the row and column of the squares on the board when they are not null.
     * It returns null when the squares are not occupied.
     * @param row
     * @param col
     * @return
     */
    public BoardSquare tileAt(int row, int col) {
        if (row >= 0 && row < Constants.NUM_ROWS && col >= 0 && col < Constants.NUM_COLS) {
            return this.board[row][col];
            }
        return null;
        }
}

