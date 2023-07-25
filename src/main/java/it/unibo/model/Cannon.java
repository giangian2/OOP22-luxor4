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

public class Cannon extends GameObject {

    private List<Ball> cannonBalls;
    private Ball stationaryBall;
    final private int ADJUST_FIRED_BALL_POS = 37;
    final private int ADJUST_STATIONARY_X_POS = 37;
    final private int ADJUST_STATIONARY_Y_POS = 50;

    public Cannon(P2d pos, V2d vel, InputComponent input, BoundingBox bbox,
            PhysicsComponent physics) {

        super(Type.CANNON, pos, vel, input, null, graph, physics);
        this.cannonBalls = new ArrayList<>();
        this.stationaryBall = createStationaryBall();
    }

    private Ball createStationaryBall() {
        BallColor randomColor = BallColor.getRandomColor();
        Ball stationaryBall = GameObjectsFactory.getInstance().createStationaryBall(getStationaryBallPos(), new V2d(0, 0),
                randomColor);
        return stationaryBall;
    }

    public Ball getStationaryBall() {
        return this.stationaryBall;
    }

    public List<Ball> getFiredBalls() {
        return this.cannonBalls;
    }

    public void removeFiredBall(Ball b) {
        this.cannonBalls.remove(b);
    }

    public void fireProjectile() {
        P2d ballPos = new P2d(getCurrentPos().x + ADJUST_FIRED_BALL_POS, getCurrentPos().y);

        BallColor projectileColor = stationaryBall.getColor();
        Ball ball = GameObjectsFactory.getInstance().createCannonBall(ballPos, new V2d(0, -10), projectileColor);

        this.cannonBalls.add(ball);

        stationaryBall.setColor(BallColor.getRandomColor());
    }

    public P2d getStationaryBallPos(){
        return new P2d(getCurrentPos().x + ADJUST_STATIONARY_X_POS, getCurrentPos().y + ADJUST_STATIONARY_Y_POS);
    }
}