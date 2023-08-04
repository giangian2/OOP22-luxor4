package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.BoundingBox;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

/**
 * 
 * Class that models a circular bounding box, identified by its center and
 * radius.
 */
public class CircleBoundingBox implements BoundingBox {

    private final P2d center;
    private final double radius;

    /**
     * Constructor.
     * 
     * @param center center of the bbox
     * @param radius radius of the bbox
     */
    public CircleBoundingBox(final P2d center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Allows you to check if there is a collision between the current instance of
     * the CIrcleBoundingBox class and a circle identified by its center and radius.
     * 
     * @param p      center
     * @param radius radius
     * @return boolean
     */
    @Override
    public boolean isCollidingWith(final P2d p, final double radius) {
        return new V2d(p, center).module() <= radius + this.radius;
    }

}
