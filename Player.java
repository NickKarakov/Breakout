
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private int hits;
    private int bubbles;
    private int lives;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        hits = 0;
        bubbles = 0;
        lives = 3;
    }
    
    public void hit() {
        hits = hits + 1;
    }
    
    public void pop() {
        bubbles = bubbles + 1;
    }
    
    public int getScore() {
        return hits + (bubbles * 5);
    }
    
    public int getLives() {
        return lives;
    }
    
    public void loseLife() {
        lives--;
    }
    
    public String getStatus() {
        return "Score=" +getScore() + ", Lives=" + lives;
    }
}
