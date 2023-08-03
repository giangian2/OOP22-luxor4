package it.unibo.model.collisions.impl;

import it.unibo.model.collisions.api.BoundingBox;
import it.unibo.utils.P2d;

/**
 * Class that models a rectangular bounding box, therefore identified by the 2
 * vertices of the rectangle p0 and p1.
 */
public class RectBoundingBox implements BoundingBox {

    private final P2d p0, p1;

    /**
     * Constructor.
     * 
     * @param p0 vertex 1
     * @param p1 vertex 2
     */
    public RectBoundingBox(final P2d p0, final P2d p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    /**
     * @Getter
     * @return P2d
     */
    public P2d getULCorner() {
        return p0;
    }

    /**
     * @Getter
     * @return P2d
     */
    public P2d getBRCorner() {
        return p1;
    }

    /**
     * In this case the method exposed by the BoundingBox interface has not been
     * implemented as in the game it is not necessary to detect collisions between
     * rectangular bounding boxes and circular bounding boxes.
     * 
     * @return boolean
     */
    @Override
    public boolean isCollidingWith(final P2d p, final double radius) {
        return false;
    }

}
