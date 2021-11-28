package evolution.tetris;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * this class contains the rectangle that both the pieces and the board are made out of
 */
public class BoardSquare {
    private Rectangle rect;
    private Pane gamePane;
    private int row;
    private int col;

    public BoardSquare(Pane gamePane, int row, int col){
        this.row=row;
        this.col=col;
        this.gamePane=gamePane;
        //instantiating the rectangle and changing its location from pixels to rows and cols
        this.rect=new Rectangle(col* Constants.SQUARE_SIZE, row* Constants.SQUARE_SIZE, Constants.SQUARE_SIZE, Constants.SQUARE_SIZE);
        //setting the stroke of the rectangle
        this.rect.setStroke(Color.BLACK);
        //setting the width of the stroke of the rectangle
        this.rect.setStrokeWidth(Constants.STROKE_WIDTH);


    }

    /**
     * this method sets the color of the rectangle
     * @param color takes in a color
     */
    public void setColor(Color color){

        this.rect.setFill(color);
    }

    /**
     * this method graphically adds the rectangle to the pane.
     */
    public void addToPane(){

        this.gamePane.getChildren().add(this.rect);
    }

    /**
     * this method sets the y location of the rectangle
     * @param y
     */
    public void setYLoc(double y){
        this.rect.setY(y);

    }

    /**
     * this method sets the x location of the rectangle
     * @param x
     */
    public void setXLoc(double x){

        this.rect.setX(x);
    }

    /**
     * This method gets the Y location of BoardSquares.
     * @return the current y location of the rectangle
     */
    public int getYLoc(){

        return (int)this.rect.getY();
    }

    /**
     * This method gets the X location of the BoardSquares.
     * @return the current x location of the rectangle
     */
    public int getXLoc(){

        return (int)this.rect.getX();
    }

    /**
     * this method removes the squares graphically.
     */
    public void removeFromPane(){
        this.gamePane.getChildren().remove(this.rect);
    }



}

