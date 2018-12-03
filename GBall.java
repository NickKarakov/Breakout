import java.awt.Color;

/*
 * File: GBall.java
 * ----------------
 * This file defines a GObject class that represents a ball.
 */
import acm.graphics.*;

/**
 * This class defines a GObject subclass that represents a ball whose reference
 * point is the center rather than the upper left corner.
 */
public class GBall extends GCompound {

    private final double ball_radius;
    /* Velocity delta in the x direction */
    private double dx;
    /* Velocity delta in the y direction */
    private double dy;

    /**
     * Creates a new ball with radius r centered at the origin
     * @param r The radius of the ball
     */
    public GBall() {
        ball_radius = 20;
        GOval ball = new GOval(2 * ball_radius, 2 * ball_radius);
        ball.setFilled(true);
        ball.setColor(Color.GREEN);
        add(ball, -ball_radius, -ball_radius);
        markAsComplete();
        dx = 7;
        dy = 6;
    }

    /**
     * Creates a new ball with radius r centered at (x, y)
     * @param r The radius of the ball
     * @param x The X coordinate of the ball
     * @param y the Y coordinate of the ball
     */
    public GBall(double x, double y) {
        this();
        setLocation(x, y);
    }

    /**
     * Find out the radius of the ball.
     * @return The radius of the ball.
     */
    public double getRadius() {
        return ball_radius;
    }

    public double getLeft() {
        return getX() - ball_radius;
    }

    public double getRight() {
        return getX() + ball_radius;
    }

    public double getTop() {
        return getY() - ball_radius;
    }

    public double getBottom() {
        return getY() + ball_radius;
    }

    public double getCenterX() {
        return getX();
    }

    public double getCenterY() {
        return getY();
    }

    /**
     * The ball moves towards the right.
     */
    public void bounceRight() {
        if (dx < 0) {
            dx = -dx;
        }
    }

    /**
     * The ball moves towards the left.
     */
    public void bounceLeft() {
        if (dx > 0) {
            dx = -dx;
        }
    }

    /**
     * The ball moves down.
     */
    public void bounceDown() {
        if (dy < 0) {
            dy = -dy;
        }
    }

    /**
     * The ball moves up.
     */
    public void bounceUp() {
        if (dy > 0) {
            dy = -dy;
        }
    }
    
    public void move() {
        move(dx, dy);
    }
}
