package evolution.flappyBird;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pipe {
    private Pane gamePane;
    private Rectangle topPipe;
    private Rectangle bottomPipe;
    private int pipeTopHeight;
    private int pipeBottomHeight;

    public Pipe(Pane gamePane) {
        this.gamePane=gamePane;
        this.setUpPipes();
    }

    private void setUpPipes(){
        int lowY=Constants.Y_OFFSET_MIN;
        int highY=Constants.Y_OFFSET_MAX;
        this.pipeTopHeight=lowY + (int) ((highY - lowY + 1) * Math.random());
        this.pipeBottomHeight=Constants.GAME_PANE_HEIGHT-this.pipeTopHeight-Constants.PIPE_GAP;
        this.topPipe=new Rectangle(Constants.GAME_PANE_WIDTH-Constants.PIPE_WIDTH,0,Constants.PIPE_WIDTH,this.pipeTopHeight);
        this.bottomPipe=new Rectangle(Constants.GAME_PANE_WIDTH-Constants.PIPE_WIDTH,this.pipeTopHeight+Constants.PIPE_GAP,Constants.PIPE_WIDTH, this.pipeBottomHeight);
        this.gamePane.getChildren().addAll(topPipe,bottomPipe);
        topPipe.setFill(Color.GREEN);
        bottomPipe.setFill(Color.GREEN);
    }

    /**
     * @return returns the x location of the pipe
     */
    public double getXLoc(){
        return this.bottomPipe.getX();

    }
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
     * @return
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
    public int getTopPipeHeight(){
        return this.pipeTopHeight;
    }
    public int getBottomPipeHeight(){
        return this.pipeBottomHeight;
    }


}
