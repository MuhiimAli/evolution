package evolution.flappyBird;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pipe {
    private Pane gamePane;
    private Rectangle topPipe;
    private Rectangle bottomPipe;
    public Pipe(Pane gamePane) {
        this.gamePane=gamePane;
        this.setUpPipes();
    }

    private void setUpPipes(){
        int lowY=Constants.Y_OFFSET_MIN;
        int highY=Constants.Y_OFFSET_MAX;
        int topHeight=lowY + (int) ((highY - lowY + 1) * Math.random());
        this.topPipe=new Rectangle(40,0,Constants.PIPE_WIDTH,topHeight);
        this.bottomPipe=new Rectangle(40,topHeight+Constants.PIPE_GAP,Constants.PIPE_WIDTH,Constants.GAME_PANE_HEIGHT-topHeight-Constants.PIPE_GAP);
        this.gamePane.getChildren().addAll(topPipe,bottomPipe);
        topPipe.setFill(Color.GREEN);
        bottomPipe.setFill(Color.GREEN);
    }
    public double getXLoc(){
        return this.bottomPipe.getX();

    }
    public double getYLoc(){
        return this.bottomPipe.getY();

    }
    public void setXLoc(double x){
        this.topPipe.setX(x);
        this.bottomPipe.setX(x);

    }
    public void setYLoc(double y){
        this.bottomPipe.setY(y);


    }
    public void removeFromPane(){
        this.gamePane.getChildren().removeAll(this.topPipe,this.bottomPipe);
    }

}
