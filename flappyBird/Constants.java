package evolution.flappyBird;

public class Constants {
    public static final int PIPE_GAP=100;//vertical distance between the pipes
    public static final int GAME_PANE_HEIGHT=500;
    public static final int GAME_PANE_WIDTH=500;
    public static final int PIPE_HORIZONTAL_DISTANCE =200;//horizontal distance between the pipes
    public static final int PIPE_WIDTH=40;
    public static final int Y_OFFSET_MIN=200;
    public static final int Y_OFFSET_MAX=GAME_PANE_HEIGHT-200;
    public static final int SCROLL_RATE=4;
    public static final int GRAVITY = 1000; // acceleration constant (UNITS: pixels/s^2)
    public static final double REBOUND_VELOCITY = -400; // initial jump velocity (UNITS: pixels/s)
    public static final double DURATION = 0.016;
}
