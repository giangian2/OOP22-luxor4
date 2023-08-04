package it.unibo.model;

import it.unibo.graphics.impl.MyGraphicsComponent;
import it.unibo.input.api.InputComponent;
import it.unibo.input.api.InputController;
import it.unibo.model.collisions.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

/**
 * A class representing a generic GameObject in the game.
 * This class provides methods to manage the position, velocity, input, physics,
 * graphics, and bounding box of the game object.
 */
public class GameObject {

    /**
     * Enumeration representing the type of the game object.
     * The possible types are BALL, CANNON, CANNON_BALL, and STATIONARY_BALL.
     */
    public enum Type {
        /**
         * The type of the game object.
         */
        /** standard ball. */
        BALL,
        /** cannon. */
        CANNON,
        /** ball fired by the cannon. */
        CANNON_BALL,
        /** ball displayedon thecannon object. */
        STATIONARY_BALL
    }

    /**
     * The type of the game object.
     */
    private Type type;

    /**
     * The position of the game object in 2D space.
     */
    private P2d pos;

    /**
     * The velocity of the game object in 2D space.
     */
    private V2d vel;
    private InputComponent input;

    /**
     * The physics component of the game object.
     */
    private PhysicsComponent physics;

    /**
     * The graphics component of the game object.
     */
    private MyGraphicsComponent graph;

    /**
     * The bounding box of the game object.
     */
    private BoundingBox bbox;

    /**
     * Creates a new GameObject with the specified type, position, velocity, input
     * component,
     * bounding box, graphics component, and physics component.
     *
     * @param type    The type of the GameObject.
     * @param pos     The initial position of the GameObject.
     * @param vel     The initial velocity of the GameObject.
     * @param input   The input component of the GameObject.
     * @param bbox    The bounding box of the GameObject.
     * @param graph   The graphics component of the GameObject.
     * @param physics The physics component of the GameObject.
     */
    public GameObject(final Type type, final P2d pos, final V2d vel, final InputComponent input, final BoundingBox bbox,
            final MyGraphicsComponent graph, final PhysicsComponent physics) {
        this.pos = pos;
        this.vel = new V2d(vel.getX(), vel.getY()); // defensive copy
        this.input = input;
        this.bbox = bbox;
        this.type = type;
        this.physics = physics;
        this.graph = graph;
    }

    /**
     * Gets the input component of the game object.
     *
     * @return The input component of the game object.
     */
    public InputComponent getInput() {
        return input;
    }

    /**
     * Sets the input component of the game object.
     *
     * @param input The new input component to set for the game object.
     */
    public void setInput(final InputComponent input) {
        this.input = input;
    }

    /**
     * Gets the physics component of the game object.
     *
     * @return The physics component of the game object.
     */
    public PhysicsComponent getPhysics() {
        return physics;
    }

    /**
     * Sets the physics component of the game object.
     *
     * @param physics The new physics component to set for the game object.
     */

    public void setPhysics(final PhysicsComponent physics) {
        this.physics = physics;
    }

    /**
     * Gets the graphics component of the game object.
     *
     * @return The graphics component of the game object.
     */
    public MyGraphicsComponent getGraph() {
        return graph;
    }

    /**
     * Sets the graphics component of the game object.
     *
     * @param graph The new graphics component to set for the game object.
     */

    public void setGraph(final MyGraphicsComponent graph) {
        this.graph = graph;
    }

    /**
     * Sets the bounding box of the game object.
     * 
     * @return The bounding box of the game object.
     */
    public BoundingBox getBbox() {
        return bbox;
    }

    /**
     * Sets the bounding box of the game object.
     *
     * @param bbox The new bounding box to set for the game object.
     */

    public void setBbox(final BoundingBox bbox) {
        this.bbox = bbox;
    }

    /**
     * Gets the type of the game object.
     *
     * @return The type of the game object (e.g., BALL, CANNON, CANNON_BALL, or
     *         STATIONARY_BALL).
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the position of the game object in 2D space.
     *
     * @param pos The new position to set for the game object.
     */
    public void setPos(final P2d pos) {
        this.pos = pos;
    }

    /**
     * Sets the velocity of the game object in 2D space.
     *
     * @param vel The new velocity to set for the game object.
     */
    public void setVel(final V2d vel) {
        this.vel = new V2d(vel.getX(), vel.getY());
    }

    /**
     * Gets the velocity of the game object in 2D space.
     * 
     * @return The velocity of the game object.
     */
    public V2d getVel() {
        return new V2d(vel.getX(), vel.getY());
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
        return new V2d(vel.getX(), vel.getY());
    }

    /**
     * Flips the vertical velocity of the game object.
     *
     */
    public void flipVelOnY() {
        this.vel = new V2d(vel.getX(), -vel.getY());
    }

    /**
     * Flips the horizontal velocity of the game object.
     * This method is used to change the direction of the game object in the
     * horizontal axis.
     */
    public void flipVelOnX() {
        this.vel = new V2d(-vel.getX(), vel.getY());
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
    public void updatePhysics(final long dt, final World w) {
        physics.update(dt, this, w);
    }

    /**
     * Updates the input of the game object.
     *
     * @param c The InputController providing input data for updating the game
     *          object.
     */
    public void updateInput(final InputController c) {
        input.update(this, c);
    }

    /**
     * Updates the graphics of the game object.
     *
     * @param c The Graphics2D object used to update the graphics of the game
     *          object.
     */
    public void updateGraphics(final java.awt.Graphics2D c) {
        graph.update(this, c);
    }

}
