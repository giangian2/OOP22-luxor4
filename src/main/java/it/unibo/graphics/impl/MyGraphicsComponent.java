package it.unibo.graphics.impl;

import it.unibo.model.impl.GameObject;

/**
 * An interface for defining a graphics component that can update the graphical
 * representation of a GameObject.
 */

public interface MyGraphicsComponent {

    /**
     * Updates the graphical representation of a GameObject on the screen.
     *
     * @param obj The GameObject to be updated.
     * @param c   The Graphics object used for drawing.
     */
    void update(GameObject obj, java.awt.Graphics2D c);
}
