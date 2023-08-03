package it.unibo.physics.impl;

import it.unibo.utils.P2d;

/**
 * The BoundaryCollision class represents a collision with the boundaries of the game world.
 * It provides information about the edge of the boundary and the collision position.
 */
public class BoundaryCollision {

    /**
     * Enum representing the edge of the boundary where the collision occurred.
     */
    public enum CollisionEdge {
        TOP,    // Collision occurred at the top boundary
        BOTTOM, // Collision occurred at the bottom boundary
        LEFT,   // Collision occurred at the left boundary
        RIGHT   // Collision occurred at the right boundary
    }

    private CollisionEdge edge; // The edge of the boundary where the collision occurred
    private P2d where;         // The collision position in 2D coordinates (P2d is likely a 2D point class)

    /**
     * Constructs a BoundaryCollision object with the specified collision edge and position.
     *
     * @param edge  The CollisionEdge representing the edge of the boundary where the collision occurred.
     * @param where The P2d representing the collision position in 2D coordinates.
     */
    public BoundaryCollision(CollisionEdge edge, P2d where) {
        this.edge = edge;
        this.where = where;
    }

    /**
     * Gets the edge of the boundary where the collision occurred.
     *
     * @return The CollisionEdge representing the edge of the boundary.
     */
    public CollisionEdge getEdge() {
        return edge;
    }

    /**
     * Gets the collision position in 2D coordinates.
     *
     * @return The P2d representing the collision position.
     */
    public P2d getWhere() {
        return where;
    }
}
