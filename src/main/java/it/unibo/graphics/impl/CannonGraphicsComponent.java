package it.unibo.graphics.impl;

import it.unibo.model.Cannon;
import it.unibo.model.GameObject;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A graphics component responsible for rendering the Cannon GameObject on the screen.
 */
public class CannonGraphicsComponent implements MyGraphicsComponent {

    private Image icon;
    /**
     * Constructs a CannonGraphicsComponent and loads the cannon icon image.
     */

    public CannonGraphicsComponent() {
        super();
        this.loadImage();
    }

    /**
     * Loads the cannon icon image from the specified resource path.
     * If the image loading fails, an IOException is caught and printed.
     */
    private void loadImage() {
        Image image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource("images/cannone.png"));
            this.icon = image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Updates the graphical representation of a Cannon GameObject on the screen.
     *
     * @param obj The GameObject to be updated. Must be an instance of Cannon.
     * @param g   The Graphics2D object used for drawing the Cannon.
     * @throws IllegalArgumentException If the given GameObject is not a Cannon.
     */
    @Override
    public void update(final GameObject obj, final java.awt.Graphics2D g) {
        if (!(obj instanceof Cannon)) {
            throw new IllegalArgumentException("GameObject is not a Cannon");
        }
        Cannon cannon = (Cannon) obj;
        // Cast the GameObject to a Cannon object.

        g.drawImage(this.icon, (int) cannon.getCurrentPos().x, (int) cannon.getCurrentPos().y, null);
        // Draw the cannon icon at the current position of the Cannon object.
    }

}
