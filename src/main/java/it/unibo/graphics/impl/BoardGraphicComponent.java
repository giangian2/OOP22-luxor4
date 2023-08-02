package it.unibo.graphics.impl;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import it.unibo.model.GameObject;

/**
 * A graphics component responsible for rendering the background image of the game board.
 */

public class BoardGraphicComponent implements MyGraphicsComponent {

    private String backgroundPath;
    private Image img;

    /**
     * Constructs a BoardGraphicComponent with the specified background image path.
     *
     * @param backgorundPath The path of the background image.
     */

    public BoardGraphicComponent(final String backgorundPath) {
        super();
        this.backgroundPath = backgorundPath;
        this.loadImage();
    }

    /**
     * Loads the background image from the specified path and stores it in the 'img' variable.
     * If the image loading fails, an IOException is caught and printed.
     */
    private void loadImage() {
        Image image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource(this.backgroundPath));
            this.img = image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Renders the background image on the game board using the Graphics2D object provided.
     *
     * @param obj The GameObject to be updated (not used in this context).
     * @param g   The Graphics2D object used for drawing the background image.
     */
    @Override
    public void update(final GameObject obj, final java.awt.Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }

    /**
     * Gets the background image used by this BoardGraphicComponent.
     *
     * @return The background image.
     */
    public Image getBackgorundImg() {
        return this.img;
    }

}
