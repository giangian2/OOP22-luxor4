package it.unibo.core.impl;

import it.unibo.enums.BallColor;
import it.unibo.graphics.impl.BallGraphicsComponent;
import it.unibo.graphics.impl.CannonGraphicsComponent;
import it.unibo.input.impl.NullInputComponent;
import it.unibo.input.impl.PlayerInputComponent;
import it.unibo.model.Ball;
import it.unibo.model.Cannon;
import it.unibo.model.GameObject.Type;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.physics.impl.BallPhysicsComponent;
import it.unibo.physics.impl.CannonBallPhysicsComponent;
import it.unibo.physics.impl.StationaryBallPhysicsComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;

public class GameObjectsFactory {

    static private GameObjectsFactory instance;

    static public GameObjectsFactory getInstance() {
        if (instance == null) {
            instance = new GameObjectsFactory();
        }
        return instance;
    }

    public Ball createBall(P2d pos, V2d vel, BallColor color) {
        return new Ball(Type.BALL, pos, color, new V2d(10, 10),
                new NullInputComponent(),
                new CircleBoundingBox(new P2d(pos.x, pos.y), 10), // in input
                new BallPhysicsComponent(),
                new BallGraphicsComponent()); // in physics

    }

    public Cannon createCannon(P2d pos, String path) {
        return new Cannon(pos, new V2d(pos, pos),
                new PlayerInputComponent(), // in input
                new RectBoundingBox(pos, pos), // in input
                null,
                new CannonGraphicsComponent()); // in physics
    }

    public Ball createCannonBall(P2d pos, V2d vel, BallColor color) {
        return new Ball(Type.CANNON_BALL, pos, color, new V2d(0, -10),
                new NullInputComponent(),
                new CircleBoundingBox(new P2d(pos.x, pos.y), 10), // in input
                new CannonBallPhysicsComponent(),
                new BallGraphicsComponent()); // in physics

    }

    public Ball createStationaryBall(P2d cannonPos, V2d vel, BallColor color) {
        return new Ball(Type.STATIONARY_BALL, cannonPos, color, new V2d(0, 0),
                new NullInputComponent(),
                new CircleBoundingBox(new P2d(cannonPos.x, cannonPos.y), 10), // in input
                new StationaryBallPhysicsComponent(),
                new BallGraphicsComponent()); // in physics
    }
}