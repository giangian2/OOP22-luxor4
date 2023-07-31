package it.unibo.physics.impl;

import it.unibo.model.GameObject;
import it.unibo.model.World;
import it.unibo.physics.api.PhysicsComponent;

public class StationaryBallPhysicsComponent extends PhysicsComponent {

    /**
     * This method updates the position of the given GameObject based on the position of the stationary ball in the World.
     * The update is performed considering the time elapsed since the last update (dt).
     *
     * @param dt  The time elapsed since the last update, in milliseconds.
     * @param obj The GameObject to be updated.
     * @param w   The World containing the GameObjects and other entities.
     */
    @Override
    public void update(long dt, GameObject obj, World w) {
        /**
         * Get the position of the stationary ball from the World.
         */
        var pos = w.getCannon().getStationaryBallPos();
        
        /** 
         * Update the position of the given GameObject to the position of the stationary ball.
        */
        obj.setPos(pos);
    }
}
