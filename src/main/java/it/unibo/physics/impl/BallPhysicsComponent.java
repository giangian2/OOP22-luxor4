package it.unibo.physics.impl;

import it.unibo.model.impl.GameObject;
import it.unibo.model.api.World;
import it.unibo.physics.api.PhysicsComponent;

/**
 * The BallPhysicsComponent class represents a specific implementation of the
 * PhysicsComponent
 * for updating the physics of a ball GameObject in the World.
 */
public class BallPhysicsComponent implements PhysicsComponent {

    /**
     * Updates the physics of the ball GameObject.
     *
     * @param dt  The time step for the update.
     * @param obj The ball GameObject to update.
     * @param w   The World in which the ball GameObject resides.
     */
    @Override
    public void update(final long dt, final GameObject obj, final World w) {
        // Get the current position and velocity of the ball GameObject
        final var pos = obj.getCurrentPos();
        final var vel = obj.getCurrentVel();

        // Update the position of the ball GameObject based on its current velocity
        obj.setPos(pos.sum(vel));
    }
}
