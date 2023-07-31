package it.unibo.physics.impl;

import it.unibo.physics.api.PhysicsComponent;
import it.unibo.model.GameObject;
import it.unibo.model.World;

/**
     * This method is called to update the physics state of the given GameObject in the World.
     * It calculates the changes in the GameObject's properties over time (dt) and updates its position,
     * velocity, and other relevant physical attributes according to the laws of physics.
     *
     * @param dt The time elapsed since the last update, in milliseconds.
     * @param obj The GameObject whose physics state needs to be updated.
     * @param w The World in which the GameObject resides.
     */
public class CannonPhysicsComponent extends PhysicsComponent {

    @Override
    public void update(long dt, GameObject obj, World w) {

    }
}
