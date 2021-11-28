package evolution.tetris;

public class Constants {

    // width of each square
    public static final int SQUARE_SIZE = 30;

    // coordinates for squares in each tetris piece
    public static final int[][] I_PIECE_COORDS = {{6*SQUARE_SIZE, SQUARE_SIZE}, {6*SQUARE_SIZE, 2*SQUARE_SIZE}, {6*SQUARE_SIZE, 3 * SQUARE_SIZE}, {6*SQUARE_SIZE, 4 * SQUARE_SIZE}};
    public static final int[][] T_PIECE_COORDS = {{6*SQUARE_SIZE, SQUARE_SIZE}, {6*SQUARE_SIZE, 2*SQUARE_SIZE}, {6*SQUARE_SIZE, 3 * SQUARE_SIZE}, {7*SQUARE_SIZE, 2*SQUARE_SIZE}};
    public static final int[][] O_PIECE_COORDS = {{6*SQUARE_SIZE, SQUARE_SIZE}, {6*SQUARE_SIZE, 2* SQUARE_SIZE}, {7*SQUARE_SIZE,SQUARE_SIZE}, {7*SQUARE_SIZE, 2*SQUARE_SIZE}};
    public static final int[][] J_PIECE_COORDS = {{6*SQUARE_SIZE, 3*SQUARE_SIZE}, {6*SQUARE_SIZE, SQUARE_SIZE}, {5*SQUARE_SIZE, SQUARE_SIZE}, {6*SQUARE_SIZE, 2*SQUARE_SIZE}};
    public static final int[][] L_PIECE_COORDS = {{6*SQUARE_SIZE, 2*SQUARE_SIZE},{6*SQUARE_SIZE, 3*SQUARE_SIZE}, {7*SQUARE_SIZE, 2*SQUARE_SIZE}, {6*SQUARE_SIZE, 4* SQUARE_SIZE}};
    public static final int[][] S_PIECE_COORDS = {{6*SQUARE_SIZE, SQUARE_SIZE}, {5*SQUARE_SIZE, 2*SQUARE_SIZE}, {6*SQUARE_SIZE, 2*SQUARE_SIZE}, {5*SQUARE_SIZE, 3*SQUARE_SIZE}};
    public static final int[][] Z_PIECE_COORDS = {{6*SQUARE_SIZE, SQUARE_SIZE}, {6*SQUARE_SIZE, 2*SQUARE_SIZE}, {7*SQUARE_SIZE, 2*SQUARE_SIZE}, {7*SQUARE_SIZE, 3*SQUARE_SIZE}};

    //these constants set up the pane's size
    public static final int PANE_WIDTH=520;
    public static final int PANE_HEIGHT=600;

    //these constants set up the number of rows and columns in the array.
    public static final int NUM_ROWS =20;
    public static final int NUM_COLS =14;

    //constants for buttonPane
    public static final int BUTTON2_PANE_WIDTH = 100;
    public static final int BUTTON2_PANE_HEIGHT =50;

    //constants for game over label
    public static final int GAME_OVER_X = 175;
    public static final int GAME_OVER_Y = 380;
    public static final int GAME_OVER_FONT = 30;

    //constants for line clearing scores
    public static final int FOUR_LINES = 200;
    public static final int ONE_LINE = 50;

    //constants for font size
    public static final int FONT_SIZE = 12;
    public static final int FONT_SIZE_PAUSED = 40;

    //constants for layout
    public static final int LAYOUT_X = 6;
    public static final int LAYOUT_Y = 12;

    //constants for stroke width
    public static final double STROKE_WIDTH = 0.5;
    public static final double BUTTON_SPACING = 20;

}
