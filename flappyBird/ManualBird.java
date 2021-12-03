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
    private Pipe pipes;
    public ManualBird(Pane gamePane, Pipe pipes){
        this.pipes=pipes;
        this.gamePane=gamePane;
        this.createBird();
        this.setXLoc(0);
        this.setYLoc(0);
    }

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
    public void updateBirdVelocity(){//TODO this can move to the manual bird class
        this.velocity = this.velocity + Constants.GRAVITY * Constants.DURATION;
        this.position = this.position + this.velocity * Constants.DURATION;
        this.setYLoc(this.position);

    }
    public void jump(){//todo this can move to the manual birds class
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
    public double getYLoc(){
        return this.beak.getLayoutY();
    }
    public double getXLoc(){
        return this.body.getLayoutX();
    }
    public void removeFromPane(){
        this.gamePane.getChildren().removeAll(this.body,this.eye,this.pupil,this.beak);
    }
    public Boolean checkIntersection(Pipe top, Pipe bottom){
        if(this.body.intersects(top.getBoundsTopPipe()) || this.body.intersects(bottom.getBoundsBottomPipe())){
            return true;
        }
        return false;

    }
    @Override
    public Boolean gameOver(){
        if(this.checkIntersection(pipes, pipes)){
            return true;
        }
        return false;
    }
    @Override
    public void handleKeyPress(KeyEvent e) {
        System.out.println("hello");
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case SPACE: default:
                this.jump();
        }
    }
}
