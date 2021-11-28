package evolution.snake;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Wraps one square of the board and tracks its contents (pellet, snake, or empty).
 */
public class BoardSquare {

    private Rectangle rect;
    private Pellet pellet;
    private Color originalColor;
    private int row;
    private int col;

    /**
     * Constructs the empty board square.
     *
     * @param gamePane the pane on which to add the square
     * @param odd flag to indicate color of the square as even or odd
     * @param row row index of the square
     * @param col column index of the square
     */
    public BoardSquare(Pane gamePane, boolean odd, int row, int col) {
        this.row = row;
        this.col = col;
        this.rect = new Rectangle(col * Constants.SQ_WIDTH, row * Constants.SQ_WIDTH,
                Constants.SQ_WIDTH, Constants.SQ_WIDTH);
        this.pellet = null;

        if (odd) {
            this.originalColor = Constants.ODD_SQUARE_COLOR;
        } else {
            this.originalColor = Constants.EVEN_SQUARE_COLOR;
        }

        this.reset();
        gamePane.getChildren().add(this.rect);
    }

    /**
     * Resets the square to be empty.
     */
    public void reset() {
        this.rect.setFill(this.originalColor);
    }

    /**
     * Sets content of the square to hold snake body. If the square held
     * pellet previously, the pellet is eaten and the pellet's score is returned.
     *
     * @return score of pellet eaten from the square; if no pellet, 0 is returned
     */
    public int addSnake() {
        this.rect.setFill(Color.BLACK);

        if (this.pellet != null) {
            int score = this.pellet.eat();
            this.pellet = null;
            return score;

        }
        return 0;
    }

    /**
     * Sets content of the square to hold a pellet item.
     *
     * @param pellet pellet item for the square to hold
     */
    public void addPellet(Pellet pellet) {
        this.pellet = pellet;
    }

    /**
     * Indicates if square is empty or not (otherwise has snake or pellet).
     *
     * @return boolean to indicate square is empty (true) or not (false)
     */
    public boolean isEmpty() {
        return this.rect.getFill().equals(this.originalColor) && this.pellet == null;
    }

    /**
     * Gets row index of square.
     *
     * @return square's row index
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Gets column index of square.
     *
     * @return square's column index
     */
    public int getCol() {
        return this.col;
    }
}
