package it.unibo.model;

import it.unibo.enums.BallColor;
import it.unibo.graphics.impl.BallGraphicsComponent;
import it.unibo.input.api.InputComponent;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

/**
 * Represents a ball object in the game.
 */
public class Ball extends GameObject {

    private BallColor color;

    /**
     * Diameter of the ball.
     */
    public static final int IMAGE_DIAMETER = 25;

    /**
     * Constructor for an istance of Ball.
     * @param type Specified type of game object.
     * @param pos Position (coordinates)
     * @param color Color
     * @param vel Velocity vector
     * @param input Input component 
     * @param bbox Bounding box
     * @param physics Physics component
     * @param graph Graphic component
     */
    public Ball(final Type type, final P2d pos, final BallColor color,
                final V2d vel, final InputComponent input, final BoundingBox bbox,
                final PhysicsComponent physics, final BallGraphicsComponent graph) {

        super(Type.BALL, pos, vel, input, bbox, graph, physics);
        this.color = color;
    }

    /**
     * Set the color of the ball.
     * @param color Color to set
     * @see BallColor
     */
    public void setColor(final BallColor color) {
        this.color = color;
    }
    /**
     * Get the color of the ball.
     * @see BallColor
     * @return The color of the ball
     */
    public BallColor getColor() {
        return this.color;
    }

    /**
     * Method that return if a ball(a) if the distance beetween a and this ball
     * is less than its diameter + a little approximation).
     * 
     * @param a Ball to check if is near 
     * @return True if the ball is near else false 
     */
    public boolean isNear(final Ball a) {
        return Math.abs(this.getCurrentPos().sumOfAxis() - a.getCurrentPos().sumOfAxis()) < (IMAGE_DIAMETER + 2);
    }

}
