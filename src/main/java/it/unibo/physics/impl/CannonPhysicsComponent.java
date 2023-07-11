package it.unibo.physics.impl;

import it.unibo.physics.api.PhysicsComponent;
import it.unibo.model.Cannon;
import it.unibo.model.GameObject;
import it.unibo.model.World;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

<<<<<<< HEAD
public class CannonPhysicsComponent extends PhysicsComponent {

    private double cannonPosX;
    private double cannonPosY;
    private double cannonVelX;
=======
/*public class CannonPhysicsComponent extends PhysicsComponent{
>>>>>>> 19d01f0726f2e72c4be1c725070f53c32a28c110

    @Override
    public void update(long dt, GameObject obj, World w) {

        super.update(dt, obj, w);

        if (obj instanceof Cannon) {
            Cannon cannon = (Cannon) obj;
            P2d cannonPos = cannon.getCurrentPos();  // Ottieni la posizione corrente del cannone
            cannonPosX = cannonPos.x;  // Aggiungi la velocità x corrente del cannone
            V2d cannonVel = cannon.getCurrentVel();  // Ottieni la velocità corrente del cannone
            cannonVelX = cannonVel.x; 
        }
    }
}*/

public class CannonPhysicsComponent extends PhysicsComponent {

    @Override
    public void update(long dt, GameObject obj, World w) {
       

    }

}
