package it.unibo.model;

import it.unibo.enums.BallColor;
import it.unibo.input.InputComponent;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

public class CannonBall extends Ball{

    public CannonBall(P2d pos, BallColor color, V2d vel, InputComponent input, BoundingBox bbox,
            PhysicsComponent physics){
                super(Type.BALL, pos, color, vel, input, bbox, physics);
    }

    public void fireProjectile(){
         vel = new V2d(0, -1);
    }
}
