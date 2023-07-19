package it.unibo.model.impl;

import it.unibo.model.api.BoundingBox;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

public class CircleBoundingBox implements BoundingBox {

    private P2d center;
    private double radius;

    public CircleBoundingBox(P2d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean isCollidingWith(P2d p, double radius) {
        return new V2d(p, center).module() <= radius + this.radius;
    }

}
