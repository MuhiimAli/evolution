package evolution.flappyBird;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class ManualBird {
    private Pane gamePane;
    private Circle body;
    private Circle eye;
    private Circle pupil;
    private Polygon beak;
    private double velocity;
    private double position;
    public ManualBird(Pane gamePane){
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

    private void trackScore() {
    }
    public void updateBirdVelocity(){
        this.velocity = this.velocity + Constants.GRAVITY * Constants.DURATION;
        this.position = this.position + this.velocity * Constants.DURATION;
        this.setYLoc(this.position);

    }
    public void jump(){
        this.velocity=Constants.REBOUND_VELOCITY;


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
    public Bounds getLayOutBounds(){
        return this.body.getLayoutBounds();
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


}
