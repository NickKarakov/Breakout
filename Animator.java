import acm.graphics.GObject;
import acm.graphics.GLabel;

/**
 *
 * @author Dr Tony Beaumont
 * @version September 2017
 */
public class Animator extends Canvas {

    private Mover mover;
    private Player player;
    private GLabel label;
    //private Walker mover;

    public Animator() {
        super();
    }

    public Animator(int numFrames) {
        super();
        this.setNumFrames(numFrames);
    }

    /**
     * This method is called to allow data to be initialised before the
     * animation begins.
     */
    public void init() {
        if (mover == null) {
            // note that width and height (of the canvas) are predefined in the Animator class
            mover = new Mover(width, height);
            // create a player
            player = new Player();
            // create a label to display the score on the screen
            label = new GLabel(player.getStatus());
            // font size 24 but default font family and weight
            label.setFont("*-*-24");
            //mover = new Walker(width, height);
        }
        // The image associated with out mover should be added to the canvas
        // at its starting position.
        // Note the mover now has a bat and a ball so returns a list of 
        // graphical objects to be added to the canvas
        for (GObject image : mover.getImage()) {
            add(image, image.getLocation());
        }
        add(label, 0,  label.getHeight());

    }

    /**
     * The draw method is called repeatedly until the stop button on the
     * animator window is clicked.
     */
    public void draw() {
        // each step of the animation requires us to move the mover.
        int score = mover.next(mouseX, mouseY);
        if (score > 0) {  // a hit or a bubble popped
            // record one hit
            if (score == 1) {
                player.hit();
            } else if (score >= 5) {
                player.pop();
            }
            // update the score on the screen
            label.setLabel(player.getStatus());
        }
        if (mover.missed()) {
            // record one life lost
            player.loseLife();
            // update the score on the screen
            label.setLabel(player.getStatus());
            // reset for the next life
            mover.reset();
            //  stop the animation if all lives are lost
            if (player.getLives() == 0) {
                this.running = false;
            }
        }
    }

}
