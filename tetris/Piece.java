package evolution.tetris;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This is the pieces class it represents the pieces in the game.
 * This class handles all the methods involved with the pieces - like movement and rotation.
 */


public class Piece {
    private BoardSquare[] tetrisSquares;//declaring the 1D array
    private Pane gamePane;
    private Color color;
    private int[][] coordsPieceI;
    private Board board;
    private boolean canRotate;
    private boolean isPaused;

    /**
     * this is the constructor of the pieces class. It is associated with the gamePane and the Board.
     * @param gamePane
     * @param color
     * @param coordsPieceI
     * @param board
     */

    public Piece(Pane gamePane, Color color, int[][] coordsPieceI, Board board) {
        this.board = board;
        this.color = color;
        this.coordsPieceI = coordsPieceI;
        this.gamePane = gamePane;
        this.setUpTetrisPieces();
        this.canRotate = true;
        this.isPaused=true;
    }

    /**
     * this method creates the tetris pieces, sets up their x and y location and graphically adds them to the pane
     */

    private void setUpTetrisPieces() {
        this.tetrisSquares = new BoardSquare[4];//initializes the 1D array
        for (int i = 0; i < this.tetrisSquares.length; i++) {
            this.tetrisSquares[i] = new BoardSquare(this.gamePane, i, 0);//adds the squares to the array
            this.tetrisSquares[i].setColor(this.color);//sets the color of the squares
            this.tetrisSquares[i].setXLoc(this.coordsPieceI[i][0]);//sets the x location of the squares
            this.tetrisSquares[i].setYLoc(this.coordsPieceI[i][1]);//sets the y location of the squares
            this.tetrisSquares[i].addToPane();//adds the pieces to the pane
        }
    }

    /**
     * this method moves the piece down by the square size, when a row below is null.
     */
    public void moveDownWithTimeline() {
       if(checkMoveValidity(0,1)) {
           for (int i = 0; i < this.tetrisSquares.length; i++) {
               this.tetrisSquares[i].setYLoc(this.tetrisSquares[i].getYLoc() + Constants.SQUARE_SIZE);
           }
       }
    }

    /**
     * This method moves the piece down by one more square.
     */
    public void moveDown() {
        if(checkMoveValidity(0,1)) {
            for (int i = 0; i < this.tetrisSquares.length; i++) {
                this.tetrisSquares[i].setYLoc(this.tetrisSquares[i].getYLoc() + Constants.SQUARE_SIZE);
            }
        }
    }

    /**
     * this method moves the piece to the right by the square size if there is space to move to.
     */
    public void moveRight(){
        if(this.checkMoveValidity(1,0)){
            for (int i = 0; i < this.tetrisSquares.length; i++) {
                this.tetrisSquares[i].setXLoc(this.tetrisSquares[i].getXLoc() + Constants.SQUARE_SIZE);
            }
        }
    }

    /**
     * this method moves the piece left by the square size if there is space to move to.
     */
    public void moveLeft(){
        if(this.checkMoveValidity(-1,0)){
            for (int i = 0; i < this.tetrisSquares.length; i++) {
                this.tetrisSquares[i].setXLoc(this.tetrisSquares[i].getXLoc() - Constants.SQUARE_SIZE);
            }
        }
    }

    /**
     * This method allows the piece to rotate.
     */
    public void rotate() {
        if(this.checkRotateValidity()){
            for (int i = 0; i < this.tetrisSquares.length; i++) {
                int centerOfRotationX = this.tetrisSquares[0].getXLoc();
                int centerOfRotationY = this.tetrisSquares[0].getYLoc();
                int oldYLocation;
                oldYLocation = this.tetrisSquares[i].getYLoc();
                int oldXLocation;
                oldXLocation = this.tetrisSquares[i].getXLoc();
                int newXLoc = centerOfRotationX - centerOfRotationY + oldYLocation;
                int newYLoc = centerOfRotationY + centerOfRotationX - oldXLocation;
                    this.tetrisSquares[i].setYLoc(newYLoc);
                    this.tetrisSquares[i].setXLoc(newXLoc);
                }
            }

    }

    /**
     * It checks the rotation validity.
     */
    private boolean checkRotateValidity() {
        for (int i = 0; i < this.tetrisSquares.length; i++) {
            int centerOfRotationX = this.tetrisSquares[0].getXLoc();
            int centerOfRotationY = this.tetrisSquares[0].getYLoc();
            int oldYLocation = this.tetrisSquares[i].getYLoc();
            int oldXLocation = this.tetrisSquares[i].getXLoc();
            int newXLoc = centerOfRotationX - centerOfRotationY + oldYLocation;
            int newYLoc = centerOfRotationY + centerOfRotationX - oldXLocation;
            int row = newYLoc / Constants.SQUARE_SIZE;
            int col = newXLoc / Constants.SQUARE_SIZE;
            if (this.board.tileAt(row,col) != null) {
                return false;
            }
        }
        return true;

    }

    /**
     * this method drops the piece
     */
    public void drop() {
        while(this.checkMoveValidity(0,1)){
            this.moveDownWithTimeline();
        }
    }

    /**
     * this method is a boolean that checks the move validity of the piece.
     */
     public boolean checkMoveValidity(int dx, int dy){
         for (int i = 0; i < this.tetrisSquares.length; i++) {
             int row=this.tetrisSquares[i].getYLoc()/ Constants.SQUARE_SIZE;
             int col=this.tetrisSquares[i].getXLoc()/ Constants.SQUARE_SIZE;
             if(this.board.getBoard()[row+dy][col+dx]!=null){
                 return false;
             }
        }
         return true;
     }

    /**
     * this method adds the pieces to the 2D array. It adds the piece to the board.
     */
    public void addPieceLogically() {
         for (int i = 0; i < this.tetrisSquares.length; i++) {
             int row = this.tetrisSquares[i].getYLoc() / Constants.SQUARE_SIZE;
             int col = this.tetrisSquares[i].getXLoc() / Constants.SQUARE_SIZE;
             this.board.getBoard()[row][col] = this.tetrisSquares[i];
         }
     }
}
