package it.unibo.physics.impl;

import java.util.Optional;

import it.unibo.events.impl.HitBallEvent;
import it.unibo.model.GameObject;
import it.unibo.utils.P2d;
import it.unibo.model.World;
import it.unibo.model.impl.CircleBoundingBox;

public class CannonBallPhysicsComponent extends BallPhysicsComponent {

    @Override
    public void update(long dt, GameObject obj, World w) {

        super.update(dt, obj, w);
        CircleBoundingBox bbox = (CircleBoundingBox) obj.getBBox();

        Optional<BoundaryCollision> binfo = w.checkCollisionWithBoundaries(obj.getCurrentPos(), bbox);
        if (binfo.isPresent()) {
            BoundaryCollision info = binfo.get();
            P2d pos = obj.getCurrentPos();

        }

        CircleBoundingBox cbbox = (CircleBoundingBox) obj.getBBox();
        Optional<GameObject> ball = w.checkCollisionWithBalls(obj.getCurrentPos(), cbbox);
        if (ball.isPresent()) {
            w.notifyWorldEvent(new HitBallEvent(ball.get()));
        }
    }
}