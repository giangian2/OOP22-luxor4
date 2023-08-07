package it.unibo.physics.impl;

import it.unibo.model.impl.GameObject;
import it.unibo.model.impl.World;
import it.unibo.physics.api.PhysicsComponent;

/**
 * A PhysicsComponent implementation that updates the position of a given
 * GameObject to match the position of a stationary ball in the World. 
 * The update is performed based
 * on the time elapsed since the last update (dt).
 *
 * This component ensures that the given GameObject represents the color of the
 * stationary ball,
 * which will be shot by the cannon.
 */
public class StationaryBallPhysicsComponent implements PhysicsComponent {

    /**
     * This method updates the position of the given GameObject based on the
     * position of the stationary ball in the World.
     * The update is performed considering the time elapsed since the last update
     * (dt).
     *
     * @param dt  The time elapsed since the last update, in milliseconds.
     * @param obj The GameObject to be updated.
     * @param w   The World containing the GameObjects and other entities.
     */
    @Override
    public void update(final long dt, final GameObject obj, final World w) {
        /**
         * Get the position of the stationary ball from the World.
         */
        final var pos = w.getCannon().getStationaryBallPos();
        /**
         * Update the position of the given GameObject to the position of the stationary
         * ball.
         */
        obj.setPos(pos);
    }
}
