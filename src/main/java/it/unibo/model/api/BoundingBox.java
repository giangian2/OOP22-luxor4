package it.unibo.model.api;

import it.unibo.utils.P2d;

public interface BoundingBox {
    boolean isCollidingWith(P2d p, double radius);
}
