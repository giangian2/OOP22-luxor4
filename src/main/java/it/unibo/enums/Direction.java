package it.unibo.enums;

import it.unibo.utils.V2d;

/**
 * Enum that represents all possible direction of movement,
 * all elements are associated with a vector.
 * 
 * @see V2d
 */
public enum Direction {
    /**
     * Up direction.
     */
    UP(new V2d(0, -1)),

    /**
     * Down direction.
     */
    DOWN(new V2d(0, 1)),

    /**
     * Left direction.
     */
    LEFT(new V2d(-1, 0)),

    /**
     * Right direction.
     */
    RIGHT(new V2d(1, 0)),

    /**
     * Without no direction(motionless).
     */
    NONE(new V2d(0, 0));

    private V2d velocity;

    /**
     * Constructs a new Direction enum element with the specified V2d value.
     * 
     * @param velocity The V2d value representing the ball's velocity vector.
     */
    Direction(final V2d velocity) {
        this.velocity = velocity;
    }

    /**
     * Return the vector of the enum element.
     * @return Vector represented by enum element.
     */
    public V2d getVelocity() {
        return new V2d(this.velocity.getX(), this.velocity.getY());
    }
}
