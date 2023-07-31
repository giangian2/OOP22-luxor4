package it.unibo.physics.api;

import it.unibo.model.GameObject;
import it.unibo.model.World;

public abstract class PhysicsComponent {

    public void update(long dt, GameObject obj, World w) {
        var pos = obj.getCurrentPos();
        var vel = obj.getCurrentVel();
        obj.setPos(pos.sum(vel));
    }
}
