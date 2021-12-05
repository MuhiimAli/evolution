package evolution.flappyBird;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class ManualBird implements Flappable{
    private Pane gamePane;
    private Circle body;
    private Circle eye;
    private Circle pupil;
    private Polygon beak;
    private double velocity;
    private double position;
    private PipeManager pipeManager;
    public ManualBird(Pane gamePane, PipeManager pipeManager){
        this.pipeManager=pipeManager;
        this.gamePane=gamePane;
        this.createBird();
        this.setXLoc(0);
        this.setYLoc(0);
    }

    /**
     * this method creates all the components of the bird
     */

    private void createBird(){
        int red = (int) (Math.random() * 256);
        int green = (int) (Math.random() * 256);
        int blue = (int) (Math.random() * 256);
        Color customColor = Color.rgb(red, green, blue);
        this.body=new Circle(100,200,10);
        this.eye=new Circle(104,196,4);
        this.pupil=new Circle(104,196,2);
        this.body.setFill(customColor);
        this.eye.setFill(Color.WHITE);
        this.beak=new Polygon(110,198,115,203,107,208);
        this.gamePane.getChildren().addAll(this.body,this.eye,this.pupil,this.beak);
    }

    @Override
    public void updateBirdVelocity(){
        this.velocity = this.velocity + Constants.GRAVITY * Constants.DURATION;
        this.position = this.position + this.velocity * Constants.DURATION;
        this.setYLoc(this.position);

    }

    /**
     * this method changes the velocity of the bird negative(makes it go up)
     */
    public void jump(){
        if(this.body.getCenterY()>=60){
            this.velocity=Constants.REBOUND_VELOCITY;
        }

    }
    public void setXLoc(double x){
        this.beak.setLayoutX(x);
        this.body.setCenterX(x+100);
        this.eye.setCenterX(x+104);
        this.pupil.setCenterX(x+104);

    }
    public void setYLoc(double y){
        this.beak.setLayoutY(y);
        this.body.setCenterY(y+200);
        this.pupil.setCenterY(y+196);
        this.eye.setCenterY(y+196);
        this.position=y;

    }

    /**
     * this method removes the bird graphically
     */
    public void removeFromPane(){
        this.gamePane.getChildren().removeAll(this.body,this.eye,this.pupil,this.beak);
    }

    /**
     * this method checks if the bird intersects with the top and bottom pipe
     * @param top top pipe
     * @param bottom bottom pipe
     * @return
     */
    public Boolean checkIntersection(Pipe top, Pipe bottom){
        if(this.body.intersects(top.getBoundsTopPipe())
                || this.body.getCenterY()>=Constants.GAME_PANE_HEIGHT
        ||this.body.intersects(bottom.getBoundsBottomPipe()) ){
            return true;
        }
        return false;
    }


    @Override
    public Boolean gameOver(){
        if(this.checkIntersection(this.pipeManager.nearestPipe(), this.pipeManager.nearestPipe())){
            this.removeFromPane();//this removes the bird graphically
            return true;
        }
        return false;
    }

    /**
     * this method makes the bird jump when the space bar is pressed
     * @param e
     */
    @Override
    public void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case SPACE: default:
                this.jump();
        }
    }
    public double getYLoc(){
       return this.body.getCenterY();
    }



}
