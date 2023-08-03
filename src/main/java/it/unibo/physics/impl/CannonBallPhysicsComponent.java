package it.unibo.physics.impl;

import java.util.Optional;

import it.unibo.events.impl.HitBallEvent;
import it.unibo.events.impl.HitBorderEvent;
import it.unibo.model.Ball;
import it.unibo.model.GameObject;
import it.unibo.model.World;
import it.unibo.model.collisions.impl.CircleBoundingBox;

/**
 * This class represents the physics component for a cannon ball in the game.
 * It extends the BallPhysicsComponent class and provides additional
 * functionality
 * specific to the cannon ball.
 */
public class CannonBallPhysicsComponent extends BallPhysicsComponent {

    /**
     * This method updates the position and behavior of the cannon ball in the
     * world.
     *
     * @param dt  The time elapsed since the last update.
     * @param obj The GameObject representing the cannon ball.
     * @param w   The World in which the ball exists.
     */
    @Override
    public void update(final long dt, final GameObject obj, final World w) {

        /**
         * Call the update method of the parent class to handle generic ball physics.
         */
        super.update(dt, obj, w);
        /**
         * Get the bounding box of the cannon ball.
         */
        CircleBoundingBox bbox = (CircleBoundingBox) obj.getBBox();

        /**
         * Check for collisions with the boundaries of the world.
         */
        Optional<BoundaryCollision> binfo = w.checkCollisionWithBoundaries(obj.getCurrentPos(), bbox);
        if (binfo.isPresent()) {

            /**
             * If there is a collision with a boundary, and the object is a ball,
             * notify the world about the event.
             */
            if (obj instanceof Ball) {
                var ball = (Ball) obj;
                w.notifyWorldEvent(new HitBorderEvent(ball));
            }
        }

        /**
         * Check for collisions with other balls in the world.
         */
        CircleBoundingBox cbbox = (CircleBoundingBox) obj.getBBox();
        Optional<GameObject> ball = w.checkCollisionWithBalls(obj.getCurrentPos(), cbbox);
        if (ball.isPresent()) {

            /**
             * If there is a collision with another ball, notify the world about the event.
             */
            w.notifyWorldEvent(new HitBallEvent(ball.get(), (Ball) obj));
        }
    }
}
