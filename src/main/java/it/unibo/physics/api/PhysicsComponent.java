package it.unibo.physics.api;

import it.unibo.model.GameObject;
import it.unibo.model.World;

/**
 * The PhysicsComponent class represents an abstract component responsible for updating the physics of a GameObject in the World.
 * It provides a method to update the position of the GameObject based on its current velocity.
 */
public abstract class PhysicsComponent {

    /**
     * Updates the physics of the GameObject.
     *
     * @param dt  The time step for the update.
     * @param obj The GameObject to update.
     * @param w   The World in which the GameObject resides.
     */
    public void update(final long dt, final GameObject obj, final World w) {
        // Get the current position and velocity of the GameObject
        var pos = obj.getCurrentPos();
        var vel = obj.getCurrentVel();

        // Update the position of the GameObject based on its current velocity
        obj.setPos(pos.sum(vel));
    }
}
