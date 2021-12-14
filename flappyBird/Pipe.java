package evolution.flappyBird;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * this is a wrapper class that creates the top pipe and bottom pipe and makes the gap between them constant.
 * it sets their location and color
 */
public class Pipe {
    private Pane gamePane;
    private Rectangle topPipe;
    private Rectangle bottomPipe;
    private int pipeTopHeight;
    private int pipeBottomHeight;
/**
the gamePane is associated with this class
the constructor method calls the setUpPipe
 */
    public Pipe(Pane gamePane) {
        this.gamePane=gamePane;
        this.setUpPipes();
        this.setColor();
    }
    /**
     * this method creates the pipe, which consists of two rectangles
     */

    private void setUpPipes() {
        int lowY = Constants.Y_OFFSET_MIN;//the minimum height of the top pipe
        int highY = Constants.Y_OFFSET_MAX;//the maximum height of the top pipe
        this.pipeTopHeight = lowY + (int) ((highY - lowY + 1) * Math.random());//the height of the top pipe
        this.pipeBottomHeight = Constants.GAME_PANE_HEIGHT - this.pipeTopHeight - Constants.PIPE_GAP;//the height of the bottom pipe
        /*
        instantiating the two rectangles
         */
        this.topPipe = new Rectangle(Constants.GAME_PANE_WIDTH - Constants.PIPE_WIDTH,
                0, Constants.PIPE_WIDTH, this.pipeTopHeight);
        this.bottomPipe = new Rectangle(Constants.GAME_PANE_WIDTH - Constants.PIPE_WIDTH,
                this.pipeTopHeight + Constants.PIPE_GAP, Constants.PIPE_WIDTH, this.pipeBottomHeight);

        this.gamePane.getChildren().addAll(topPipe, bottomPipe);//adding the two rectangles to the gamePane
    }
      /**
       this sets the color of the two pipes
        */
    public void setColor(){
        topPipe.setFill(Color.GREEN);
        bottomPipe.setFill(Color.GREEN);
    }

    /**
     * @return returns the x location of the pipe
     */
    public double getXLoc(){
        return this.bottomPipe.getX();
    }

    /**
     * this method sets the x location of the top and bottom pipe
     * @param x
     */
    public void setXLoc(double x){
        this.topPipe.setX(x);
        this.bottomPipe.setX(x);
    }
    /**
     * this method removes the pipes graphically
     */
    public void removeFromPane(){
        this.gamePane.getChildren().removeAll(this.topPipe,this.bottomPipe);
    }

    /**
     * this method gets the bounds of the bottom pipe
     */
    public Bounds getBoundsTopPipe(){
        return this.topPipe.getLayoutBounds();
    }
    /**
     * this method returns the bounds of the top pipe
     * @return
     */
    public Bounds getBoundsBottomPipe(){
        return this.bottomPipe.getLayoutBounds();
    }
    /**
     * @return this method returns the height of the top pipe
     */
    public int getTopPipeHeight(){
        return this.pipeTopHeight;
    }
    /**
     * @return this method returns the height of the bottom pipe
     */
    public int getBottomPipeHeight(){
        return this.pipeBottomHeight;
    }
}
