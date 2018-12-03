/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.graphics.GCompound;
import java.util.ArrayList;

/**
 *
 * @author Dr Tony Beaumont
 * @version September 2017
 */
public class Mover {

    /* Canvas Dimensions */
    private double canvasWidth;
    private double canvasHeight;

    /* Private instance variables */
    /* The ball object */
    private GBall ball;
    private Bat bat;
    private double batY;
    private Wall wall;
    private boolean touchedGround;
    private int count;

    /* Canvas dimensions */

    /**
     * Create new mover that moves a ball across the canvas, bouncing off the edges.
     * The ball is initially in the centre of the canvas.
     * @param width the width of the canvas.
     * @param height the height of the canvas.
     */
    public Mover(double width, double height) {
        canvasWidth = width;
        canvasHeight = height;
        ball = new GBall(canvasWidth / 2, canvasHeight / 2);
        bat = new Bat();
        int numRowsInWall = 4;
        int numBricksInRow = 10;
        double brickWidth = width/numBricksInRow;
        double brickHeight = brickWidth/3;
        double firstRowY = ball.getRadius()*4;
        wall = new Wall(numRowsInWall, numBricksInRow, 0, firstRowY, canvasWidth);
        wall.showWall();
        batY = height - (bat.getHeight()*2);
        bat.setLocation(width/2, batY);
        touchedGround = false;
        count = 0;
    }

    /**
     * The next frame of animation.  
     * Move the bat.  Then move the ball one step and bounce when it hits 
     * the top or side of the canvas or the top of the bat.
     * @return false if the ball did not touch the bat.  
     * Returns the score during this move.  1 for a hit, 5 for a pop.
     */
    public int next(double mouseX, double mouseY) {
        int score = 0;
        moveBat(mouseX);
        if (checkBounce()) {
            score += 1;
        }
        ball.move(); 
        if (wall.ballBrickCollide(ball)) {
            score += 5;
            if (wall.noBricksLeft()) {
                wall.showWall();
            }
        }
        return score;
    }

    private boolean checkBounce() {
        boolean hit = false;
        double ballLeft = ball.getLeft();
        double ballRight = ball.getRight();
        double ballTop = ball.getTop();
        double ballX = ball.getCenterX();
        double batLeft = bat.getLeft();
        double batRight = bat.getRight();
        double ballBottom = ball.getBottom();
        double batTop = bat.getTop();
        // ball at left edge or ball at right edge
        if (ballLeft <= 0) { 
            ball.bounceRight();
        } else if (ballRight >= canvasWidth) {
            ball.bounceLeft();
        }
        if (ball.getTop() <= 0) { // at the top edge
            ball.bounceDown();
        }
        // did it hit the bat (reuse detectCollision from the wall because bat is a rectangle!)
        else if (wall.detectCollision(ball, bat.getX(), bat.getY(), bat.getWidth(), bat.getHeight())) {
            hit = true; // the player hit the ball with the bat
        }
        else if (ball.getBottom() >= canvasHeight) {
            // the ball hit the ground (the bottom of the canvas)
            touchedGround = true;
        } 
        return hit;
    }

    /**
     * The bat moves 20 pixels towards the mouse in the X direction only.
     * The bat never moves out of the canvas.
     */
    private void moveBat(double mouseX) {
        double batLeft = bat.getLeft();
        double batRight = bat.getRight();
        double batTop = bat.getTop();
        if (batRight < mouseX  && batRight < canvasWidth) {
            // bat moves right towards the mouse
            bat.setLocation(batLeft + 20, batTop);
        } else if (batLeft > mouseX && batLeft > 0) {
            // bat moves left towards the mouse
            bat.setLocation(batLeft - 20, batTop);
        }
    }

    /**
     * Called after the ball touches the ground.  Move the ball to the center
     * and set touchedGround to be false;
     */
    public void reset() {
        //touchedGround = false;
        ball.setLocation(canvasWidth / 2, canvasHeight / 2);
    }

    /**
     * Record that the ball touched the ground in the last life.
     * @return true when the ball touched the ground and false otherwise.
     */
    public boolean missed() {
        if (touchedGround) {
            // the ball touched the ground in the last move.
            // set touchedGround to false ready for the next life.
            touchedGround = false;
            return true;
        } else {
            // the ball didn't touch the ground
            return false;
        }
    }

    /* ------------- methods below this line used by the Animator. Do not change --------*/

    /**
     * Get the visible image used by this mover. In this example the GObject is
     * a blue circle.
     * @return The graphics object representing the ball on the canvas.
     */
    public GObject[] getImage() {
        ArrayList<GObject> objs = new ArrayList<>();
        objs.add(ball);
        objs.add(bat);
        for (Brick[] row : wall.getWall()) {
            for (GObject o : row) {
                objs.add(o);
            }
        }
        return objs.toArray(new GObject[objs.size()]);
    }

}
