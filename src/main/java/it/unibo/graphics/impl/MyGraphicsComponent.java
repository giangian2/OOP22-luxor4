package it.unibo.graphics.impl;

import it.unibo.model.*;

/**
 * An interface for defining a graphics component that can update the graphical representation of a GameObject.
 */

public interface MyGraphicsComponent {

     /**
     * Updates the graphical representation of a GameObject on the screen.
     *
     * @param obj The GameObject to be updated.
     * @param g   The Graphics object used for drawing.
     */
    public void update(GameObject obj, java.awt.Graphics2D c);
}
