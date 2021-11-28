package evolution.snake;

import javafx.scene.paint.Color;

/**
 * Stores constants used throughout the program.
 */
public class Constants {

    public static final int NUM_ROWS = 15;
    public static final int NUM_COLS = 15;
    public static final int SQ_WIDTH = 35;
    public static final Color ODD_SQUARE_COLOR = Color.GREEN;
    public static final Color EVEN_SQUARE_COLOR = Color.GREENYELLOW;
    public static final int SCORE_PANE_HEIGHT = 60;
    public static final int GAME_PANE_HEIGHT = SQ_WIDTH * NUM_ROWS;


    public static final int FRUIT_RADIUS = 12;
    public static final int FRUIT_OFFSET = SQ_WIDTH / 2;
    public static final int NUM_FRUIT = 3;

    public static final double TIMELINE_DURATION = 0.4;

    public static final int[][] SNAKE_INITIAL_COORDINATES = {{NUM_ROWS/2, 2}, {NUM_ROWS/2, 1}, {NUM_ROWS/2, 0}};

    public static final String SCORE_LABEL_TEXT = "Score: ";

    public static final int FOOD_1_SCORE = 10;
    public static final int FOOD_2_SCORE = 25;
    public static final int FOOD_3_SCORE = 50;
    public static final int FOOD_4_SCORE = 100;
    public static final int LAYOUT_X = 180;
    public static final int LAYOUT_Y = 360;
}
