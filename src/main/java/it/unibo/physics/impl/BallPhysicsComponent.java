package it.unibo.physics.impl;

import it.unibo.model.GameObject;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.model.World;

/**
 * The BallPhysicsComponent class represents a specific implementation of the PhysicsComponent
 * for updating the physics of a ball GameObject in the World.
 */
public class BallPhysicsComponent extends PhysicsComponent {

    /**
     * Updates the physics of the ball GameObject.
     *
     * @param dt  The time step for the update.
     * @param obj The ball GameObject to update.
     * @param w   The World in which the ball GameObject resides.
     */
    @Override
    public void update(long dt, GameObject obj, World w) {
        // Call the update method of the parent class to update the position of the ball
        super.update(dt, obj, w);

        /*CircleBoundingBox cbbox = (CircleBoundingBox) obj.getBBox();
        Optional<GameObject> ball = w.checkCollisionWithBalls(obj.getCurrentPos(), cbbox);
        if (ball.isPresent()) {
            w.notifyWorldEvent(new HitBallEvent(ball.get()));
        }*/
    }
}
