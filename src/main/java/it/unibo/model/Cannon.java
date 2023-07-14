package it.unibo.model;

import it.unibo.input.*;
import it.unibo.model.api.BoundingBox;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.physics.impl.BallPhysicsComponent;
import it.unibo.physics.impl.CannonPhysicsComponent;
import it.unibo.model.Ball;
import it.unibo.enums.BallColor;
import it.unibo.utils.*;

import java.util.Random;

public class Cannon extends GameObject{

     public Cannon(P2d pos, V2d vel, InputComponent input, BoundingBox bbox,
            PhysicsComponent physics) {

        super(Type.CANNON, pos, vel, input, null, physics);
    }

    public void fireProjectile(double cannonPosx, double cannonPosY){
        P2d ballPos = new P2d(cannonPosx, cannonPosY);

        Random random = new Random();
        BallColor randomColor = BallColor.getRandomColor();

        BoundingBox bbox = new CircleBoundingBox();
        PhysicsComponent physics = new BallPhysicsComponent();

        Ball ball = new Ball(Type.BALL, ballPos, randomColor, new V2d(0, -10), null, bbox, physics);
    }
}