package it.unibo.graphics.impl;

import java.awt.Color;
import java.awt.Image;

import it.unibo.model.*;

/**
 * A graphics interface for rendering images and shapes on the screen.
 */
public interface Graphics {
    
    /**
     * Draws an image on the screen.
     *
     * @param image  The image to be drawn.
     * @param x      The x-coordinate of the top-left corner of the image.
     * @param y      The y-coordinate of the top-left corner of the image.
     * @param width  The width of the image to be drawn.
     * @param height The height of the image to be drawn.
     */

    public void drawImage(Image image, int x, int y, int width, int height);
    /**
     * Updates the graphical representation of a GameObject on the screen.
     *
     * @param obj The GameObject to be updated.
     * @param g   The Graphics object used for drawing.
     */
    public void update(GameObject obj, Graphics c);

     /**
     * Sets the color to be used for drawing a Ball.
     *
     * @param colorForBall The color to be used for drawing a Ball.
     */
    public void setColor(Color colorForBall);

     /**
     * Draws a filled oval on the screen.
     *
     * @param i The x-coordinate of the upper left corner of the bounding rectangle of the oval.
     * @param j The y-coordinate of the upper left corner of the bounding rectangle of the oval.
     * @param k The width of the oval.
     * @param l The height of the oval.
     */
    public void fillOval(int i, int j, int k, int l);



}