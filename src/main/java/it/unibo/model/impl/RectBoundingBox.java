package it.unibo.model.impl;

import it.unibo.model.api.BoundingBox;
import it.unibo.utils.P2d;

public class RectBoundingBox implements BoundingBox {

    private P2d p0, p1;

    public RectBoundingBox(P2d p0, P2d p1) {
        this.p0 = p0;
        this.p1 = p1;
    }

    @Override
    public boolean isCollidingWith(P2d p, double radius) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCollidingWith'");
    }

}
