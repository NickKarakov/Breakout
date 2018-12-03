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
public class Bat extends GCompound {

    private final double bat_width;

    /**
     * Creates a new ball with radius r centered at the origin
     * @param r The radius of the ball
     */
    public Bat() {
        bat_width = 100;
        GRect bat = new GRect(bat_width, 10);
        bat.setFilled(true);
        bat.setColor(Color.BLUE);
        add(bat);
        markAsComplete();
    }
    
    public double getLeft() {
        return getX();
    }
    
    public double getRight() {
        return getX() + getWidth();
    }
    
    public double getTop() {
        return getY();
    }
    
    public double getBottom() {
        return getY() + getHeight();
    }
    
}
