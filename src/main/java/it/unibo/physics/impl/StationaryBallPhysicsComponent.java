package it.unibo.physics.impl;

import it.unibo.model.GameObject;
import it.unibo.model.World;
import it.unibo.physics.api.PhysicsComponent;

public class StationaryBallPhysicsComponent extends PhysicsComponent{
    @Override
    public void update(long dt, GameObject obj, World w) {
        var pos = w.getCannon().getStationaryBallPos();
        obj.setPos(pos);
        
    }
}
