package it.unibo.physics.impl;

import it.unibo.physics.api.PhysicsComponent;
import it.unibo.model.Cannon;
import it.unibo.model.GameObject;
import it.unibo.model.World;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

public class CannonPhysicsComponent extends PhysicsComponent {

    private double cannonPosX;
    private double cannonPosY;
    private double cannonVelX;

    @Override
    public void update(long dt, GameObject obj, World w) {
        super.update(dt, obj, w);

        if (obj instanceof Cannon) {
            Cannon cannon = (Cannon) obj;
            P2d cannonPos = cannon.getCurrentPos();
            cannonPosX = cannonPos.x;
            cannonPosY = cannonPos.y;

            V2d cannonVel = cannon.getCurrentVel();
            cannonVelX = cannonVel.x;

            cannon.fireProjectile(cannonPosX, cannonPosY);
        }
    }
}
