package it.unibo.model;

import it.unibo.graphics.impl.MyGraphicsComponent;
import it.unibo.input.api.InputComponent;
import it.unibo.input.api.InputController;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.*;

/**
 * A class representing a generic GameObject in the game.
 *  This class provides methods to manage the position, velocity, input, physics, graphics, and bounding box of the game object.
 */
public class GameObject {

    /**
     * Enumeration representing the type of the game object.
     * The possible types are BALL, CANNON, CANNON_BALL, and STATIONARY_BALL.
     */
    public static enum Type {
        BALL, CANNON, CANNON_BALL, STATIONARY_BALL
    }

    private Type type;
    private P2d pos;
    private V2d vel;
    protected InputComponent input;
    protected PhysicsComponent physics;
    protected MyGraphicsComponent graph;
    protected BoundingBox bbox;

    /**
     * Creates a new GameObject with the specified type, position, velocity, input component, bounding box, graphics component, and physics component.
     *
     * @param type The type of the GameObject.
     * @param pos The initial position of the GameObject.
     * @param vel The initial velocity of the GameObject.
     * @param input The input component of the GameObject.
     * @param bbox The bounding box of the GameObject.
     * @param graph The graphics component of the GameObject.
     * @param physics The physics component of the GameObject.
     */
    public GameObject(Type type, P2d pos, V2d vel, InputComponent input, BoundingBox bbox, MyGraphicsComponent graph,
            PhysicsComponent physics) {
        this.pos = pos;
        this.vel = vel;
        this.input = input;
        this.bbox = bbox;
        this.type = type;
        this.physics = physics;
        this.graph = graph;
    }

    /**
     * Gets the type of the game object.
     *
     * @return The type of the game object (e.g., BALL, CANNON, CANNON_BALL, or STATIONARY_BALL).
     */
    public Type getType() {
        return type;
    }

    /**
    * Sets the position of the game object in 2D space.
    *
    * @param pos The new position to set for the game object.
    */
    public void setPos(P2d pos) {
        this.pos = pos;
    }

    /**
     * Sets the velocity of the game object in 2D space.
     *
     * @param vel The new velocity to set for the game object.
     */
    public void setVel(V2d vel) {
        this.vel = vel;
    }

    /**
     * Gets the position of the game object in 2D space.
     *
     * @return The position of the game object.
     */
    public V2d getVel(V2d vel) {
        return this.vel;
    }

    /**
     * Gets the current position of the game object in 2D space.
     *
     * @return The current position of the game object.
     */
    public final P2d getCurrentPos() {
        return this.pos;
    }

    /**
     * Gets the current velocity of the game object in 2D space.
     *
     * @return The current velocity of the game object.
     */
    public V2d getCurrentVel() {
        return this.vel;
    }

    /**
     * Gets the input component of the game object.
     *
     * @return The input component of the game object.
     */
    public void flipVelOnY() { 
        this.vel = new V2d(vel.x, -vel.y);
    }

    /**
     * Flips the horizontal velocity of the game object.
     * This method is used to change the direction of the game object in the horizontal axis.
     */
    public void flipVelOnX() {
        this.vel = new V2d(-vel.x, vel.y);
    }

    /**
     * Gets the input component of the game object.
     *
     * @return The input component of the game object.
     */
    public BoundingBox getBBox() { 
        return bbox;
    }

    /**
     * Updates the physics of the game object.
     *
     * @param dt The time step used to update the physics.
     * @param w  The game world where the game object is located.
     */
    public void updatePhysics(long dt, World w) {
        physics.update(dt, this, w);
    }

    /**
     * Updates the input of the game object.
     *
     * @param c The InputController providing input data for updating the game object.
     */
    public void updateInput(InputController c) {
        input.update(this, c);
    }

    /**
     * Updates the graphics of the game object.
     *
     * @param c The Graphics2D object used to update the graphics of the game object.
     */
    public void updateGraphics(java.awt.Graphics2D c) {
        graph.update(this, c);
    }

}
