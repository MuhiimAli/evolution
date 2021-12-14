package evolution.flappyBird;

/**
 * this class stores all the constants used in the classes in the flappy bird package
 */
public class Constants {
    /*
    gamePane constants
     */
    public static final int GAME_PANE_HEIGHT=500;
    public static final int GAME_PANE_WIDTH=600;

    /*
    Pipe constants
     */
    public static final int PIPE_GAP=150;//vertical distance between the pipes
    public static final int Y_OFFSET_MIN=150;
    public static final int Y_OFFSET_MAX=250;
    public static final int PIPE_HORIZONTAL_DISTANCE =200;//horizontal distance between the pipes
    public static final int PIPE_WIDTH=40;
    public static final int SCROLL_RATE=4;

   /*
   physics constants
    */

    public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
    public static final double REBOUND_VELOCITY = -400; // initial jump velocity (UNITS: pixels/s)
    public static final double DURATION = 0.016;
    /*
    Mutation rate constant
     */

    public static final double MUTATION_RATE=0.04;
    /*
    scorePane constants
     */
    public static final double SCORE_PANE_WIDTH=30;
    public static final double SCORE_PANE_HEIGHT=40;
    /*
    background constants
     */
    public static final double IMAGE_WIDTH=900;
    public static final double IMAGE_HEIGHT=900;

    /*
    the weights constants
     */
    public static final int SYN0_ROW=3;
    public static final int SYN0_COL=4;
    public static final int SYN1_ROW=1;
    public static final int SYN1_COL=3;
    /*
    the inputNode constants
     */
    public static final int INPUT_ROW=4;
    public static final int INPUT_COL=1;
    /*
    Bird constants
     */
    public static final int BIRD_CENTER_X=100;
    public static final int BIRD_CENTER_Y=200;
    public static final int BIRD_RADIUS=10;
    public static final int EYE_CENTER_X=104;
    public static final int EYE_CENTER_Y=196;
    public static final int EYE_RADIUS=4;
    public static final int PUPIL_CENTER_X=104;
    public static final int PUPIL_CENTER_Y=196;
    public static final int PUPIL_RADIUS=2;
    public static final int BEAK_X1=110;
    public static final int BEAK_Y1=198;
    public static final int BEAK_X2=115;
    public static final int BEAK_Y2=203;
    public static final int BEAK_X3=107;
    public static final int BEAK_Y3=208;
    public static final int COLOR_PALETTE=256;
    public static final int JUMP_THRESHOLD=100;
    public static final double Y_LOC_BIRD2=20;
    public static final double GEN0_PASS=150;
    public static final double KILL_SWITCH=10000;



}

