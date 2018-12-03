import acm.graphics.*;
import acm.util.RandomGenerator;

/**
 * Write a description of class Brick here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Brick extends GCompound
{
    private GRect brickShape;
    private static final RandomGenerator gen = new RandomGenerator();
    
    public Brick(double width, double height) {
        brickShape = new GRect(width, height);
        brickShape.setFilled(true);
        brickShape.setVisible(false);
        add (brickShape);
        markAsComplete();
    }
    
    public void showBrick(double x, double y) {
        setLocation(x, y);
        brickShape.setColor(gen.nextColor());
        brickShape.setVisible(true);
    }
    
    public void hideBrick() {
        brickShape.setVisible(false);
    }
    
    public boolean showing() {
        return brickShape.isVisible();
    }
    
    public double getTop() {
        return getY();
    }
    
    public double getBottom() {
        return getY()+ getHeight();
    }
    
    public double getLeft() {
        return getX();
    }
    
    public double getRight() {
        return getX() + getWidth();
    }
    
    public String toString() {
        return "Brick at " + getX() + ", " + getY();
    }
    
}
