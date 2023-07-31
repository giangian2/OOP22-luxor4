package it.unibo.physics.impl;

import it.unibo.model.GameObject;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.model.World;

public class BallPhysicsComponent extends PhysicsComponent {

    @Override
    public void update(long dt, GameObject obj, World w) {

        super.update(dt, obj, w);

        /*CircleBoundingBox cbbox = (CircleBoundingBox) obj.getBBox();
        Optional<GameObject> ball = w.checkCollisionWithBalls(obj.getCurrentPos(), cbbox);
        if (ball.isPresent()) {
            w.notifyWorldEvent(new HitBallEvent(ball.get()));
        }*/
    }
}
