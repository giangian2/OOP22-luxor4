package it.unibo.model.api;

import it.unibo.utils.P2d;

/**
 * Interface that models the 2D physical space occupied by a game object.
 */
public interface BoundingBox {
    /**
     * Allows you to check at a specific instant whether the current bounding box
     * instance collides with an object having the point "p" as its center and
     * "radius" as its radius.
     * 
     * @param p
     * @param radius
     * @return boolean
     */
    boolean isCollidingWith(P2d p, double radius);
}
