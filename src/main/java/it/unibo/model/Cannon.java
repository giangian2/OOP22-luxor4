package it.unibo.model;

import it.unibo.input.*;
import it.unibo.model.api.BoundingBox;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.physics.impl.BallPhysicsComponent;
import it.unibo.physics.impl.CannonPhysicsComponent;
import it.unibo.model.Ball;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.utils.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Cannon extends GameObject{

    private List<GameObject> cannonBalls;

     public Cannon(P2d pos, V2d vel, InputComponent input, BoundingBox bbox,
            PhysicsComponent physics) {

        super(Type.CANNON, pos, vel, input, null, physics);
        this.cannonBalls = new ArrayList<>();
    }

    public List<GameObject> getFiredBalls() {
        return this.cannonBalls;
    }

    public void fireProjectile(double cannonPosx, double cannonPosY){
        P2d ballPos = new P2d(cannonPosx, cannonPosY);

        BallColor randomColor = BallColor.getRandomColor();

        Ball ball = GameObjectsFactory.getInstance().createBall(ballPos, new V2d(0, -10), randomColor);

        cannonBalls.add(ball);
    }
}