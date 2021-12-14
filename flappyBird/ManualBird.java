package evolution.flappyBird;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 * This is class creates the bird, and contains all the methods that control the bird's movement and position and let
 * the bird respond to the key events (when the spaceBar is pressed, the bird jumps up)
 */

public class ManualBird implements Flappable{
    private Pane gamePane;
    private Circle body;
    private Circle eye;
    private Circle pupil;
    private Polygon beak;
    private double velocity;
    private double position;
    private PipeManager pipeManager;

    /**
     * The constructor method calls the createBird method, which creates all the shapes that make up the bird
     * it sets the y and x location of the bird
     * calls teh setOpacity method, which sets the opacity of the composite shape
     * @param gamePane
     * @param pipeManager
     */
    public ManualBird(Pane gamePane, PipeManager pipeManager){
        this.pipeManager=pipeManager;
        this.gamePane=gamePane;
        this.createBird();
        this.setXLoc(0);
        this.setYLoc(0);
        this.setOpacity();
    }

    /**
     * this method creates the bird, and controls the birds velocity and position
     */

    private void createBird(){
        int red = (int) (Math.random() * Constants.COLOR_PALETTE);
        int green = (int) (Math.random() * Constants.COLOR_PALETTE);
        int blue = (int) (Math.random() * Constants.COLOR_PALETTE);
        Color customColor = Color.rgb(red, green, blue);
        this.body=new Circle(Constants.BIRD_CENTER_X,Constants.BIRD_CENTER_Y,Constants.BIRD_RADIUS);
        this.eye=new Circle(Constants.EYE_CENTER_X,Constants.EYE_CENTER_Y,Constants.EYE_RADIUS);
        this.pupil=new Circle(Constants.PUPIL_CENTER_X,Constants.PUPIL_CENTER_Y,Constants.PUPIL_RADIUS);
        this.body.setFill(customColor);
        this.eye.setFill(Color.WHITE);
        this.beak=new Polygon(Constants.BEAK_X1,Constants.BEAK_Y1,Constants.BEAK_X2
                ,Constants.BEAK_Y2, Constants.BEAK_X3,Constants.BEAK_Y3);
        this.gamePane.getChildren().addAll(this.body,this.eye,this.pupil,this.beak);
    }

    /**
     * sets the opacity of each shape
     */
    public void setOpacity(){
        this.body.setOpacity(1);
        this.beak.setOpacity(1);
        this.eye.setOpacity(1);
        this.pupil.setOpacity(1);
    }
    /**
     * this method assigns and updates the bird's velocity and its y position
     */
    public void updateBirdVelocity(){
        this.velocity = this.velocity + Constants.GRAVITY * Constants.DURATION;
        this.position = this.position + this.velocity * Constants.DURATION;
        this.setYLoc(this.position);
    }
    /**
     * this method gets updated with the timeline
     */
    @Override
    public void updateWithTimeline(){
        this.updateBirdVelocity();
    }

    /**
     * this method changes the velocity of the bird to negative and makes it go up.
     */
    public void jump(){
        if(this.body.getCenterY()>=Constants.JUMP_THRESHOLD){
            this.velocity=Constants.REBOUND_VELOCITY;
            this.updateBirdVelocity();
        }
//
    }

    /**
     * sets the x location of the bird
     * @param x
     */
    public void setXLoc(double x){
        this.beak.setLayoutX(x);
        this.body.setCenterX(x+Constants.BIRD_CENTER_X);
        this.eye.setCenterX(x+Constants.EYE_CENTER_X);
        this.pupil.setCenterX(x+Constants.PUPIL_CENTER_X);

    }
    /**
     * sets the y location of the bird
     */
    public void setYLoc(double y){
        this.beak.setLayoutY(y);
        this.body.setCenterY(y+Constants.BIRD_CENTER_Y);
        this.pupil.setCenterY(y+Constants.PUPIL_CENTER_Y);
        this.eye.setCenterY(y+Constants.EYE_CENTER_Y);
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
                || this.body.getCenterY()>Constants.GAME_PANE_HEIGHT
        ||this.body.intersects(bottom.getBoundsBottomPipe()) ){
            return true;
        }
        return false;
    }

    /**
     * ends the game when the bird either falls or intersects with the pipes
     * @return
     */
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
        if(e.getCode()==KeyCode.SPACE){
                this.jump();
        }
    }

    /**
     * a getter method that gets the current y location of the bird
     * @return
     */
    public double getYLoc(){
       return this.body.getCenterY();
    }



}
