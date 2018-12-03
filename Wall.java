import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a description of class Wall here.
 *
 * @author (Nick Karakov)
 * @version (a version number or a date)
 */
public class Wall
{
    private ArrayList<Brick[]> wall;
    private int numRowsInWall;
    private int numBricksInRow;
    private double brickWidth;
    private double brickHeight;
    private double startX;
    private double startY;

    /**
     * Constructor for objects of class Wall
     */
    public Wall(int numRows, int rowLength, double startX, double startY, double wallWidth)
    {
        // initialise instance variables
        numBricksInRow = rowLength;
        numRowsInWall = numRows;
        brickWidth = wallWidth / rowLength;
        brickHeight = brickWidth/3;
        this.startX = startX;
        this.startY = startY;
        makeWall();
    }

    public void showWall() {
        // see the instructions on how to complete this method
        double x = startX;
        double y = startY;
        for(int i=0; i<numRowsInWall; i++){

            for(int j=0; j<numBricksInRow; j++){
                wall.get(i)[j].showBrick(x,y);
                x = x+brickWidth;
            }
            y +=brickHeight;
            x=startX;

        }
    }

    private void makeWall() {
        // see the instructions for how to complete this method
        wall = new ArrayList<Brick[]>(numRowsInWall);
        for(int i=0; i<numRowsInWall; i++){
            Brick[] row = new Brick[numBricksInRow] ;
            wall.add(row);
            for(int y = 0; y<numBricksInRow; y++){
                Brick brick = new Brick(brickWidth, brickHeight);
                row[y] = brick;
            }

        }

    }   

    public boolean ballBrickCollide(GBall ball) {
        // see the instructions on how to complete this method
        for (int i=0;i<numRowsInWall ;i++ ) {
            for(int j=0;j<numBricksInRow; j++){
                Brick brick = wall.get(i)[j];
                if(brick.showing() && detectCollision(ball, brick.getX(),
                	brick.getY(), brick.getWidth(), brick.getHeight())){
                    brick.hideBrick();
                
                    return true;
                }

            }

        }
        return false;
    }

    /** 
     * Detect a collision between the ball (a circle) and a rectangle.  
     * If a collision is detected the ball will bounce up/down/left/right depending on which part
     * of the rectangle it hits.
     * @param ball The ball.
     * @param rx the x coordinate of the rectangle (top left).
     * @param ry the y coordinate of the rectangle (top left).
     * @param rw the width of the rectangle.
     * @param rh the height of the rectangle.
     * @return true when a collision is detected and false otherwise.
     */
    public boolean detectCollision(GBall ball, double rx, double ry, double rw, double rh) {
        // see the instructions for how to complete this method  
        double cx = ball.getX();
        double cy = ball.getY();
        double radius = ball.getRadius();

        double testX = cx;
        double testY = cy;

        if(cx<rx){
        	testX = rx;
        } else if (cx>rx+rw){
        	testX = rx+rw;
        }
         if(cy<ry){
        	testY = ry;
        } else if (cy>ry+rh){
        	testY = ry+rh;
        }
        double distX = cx-testX;
        double distY = cy-testY;
        double distance = Math.sqrt((distX*distX) + (distY*distY));

        if(distance <= radius){
        	if(testX !=cx){
        		if(testX==rx){
        			ball.bounceLeft();
        		}else if(testX == rx+rw){
        			ball.bounceRight();
        		}
        	}
       	if(testY !=cy){
        		if(testY==ry){
        			ball.bounceUp();
        		}else if(testY == ry+rh){
        			ball.bounceDown();
        		}
        	}        	
        	return true;

        }
       

        return  false; // ball  didn’t hit  anything
        
    }

    public boolean noBricksLeft() {
        // see the instructions for how to complete this method
        for (int i=0;i<numRowsInWall ;i++ ) {
            for(int j=0;j<numBricksInRow; j++){
            	Brick brick = wall.get(i)[j];
            	if(brick.showing()){
            		return false;
            	}
            	

            }
        }

        return true;
    }

    /**
     * Get the wall.  This is required so the Mover can add each brick to the canvas.
     * @return an ArrayList of rows of bricks.  Each row is an array of Brick objects.
     */
    public ArrayList<Brick[]> getWall() {
        return wall;
    }

}
