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

/**
 * A factory class for creating GameObjects.
 */
public class GameObjectsFactory {

    /**
     * The instance of the GameObjectsFactory.
     */
    private static GameObjectsFactory instance;

    /**
     * Gets the singleton instance of the GameObjectsFactory.
     *
     * @return The GameObjectsFactory instance.
     */
    public static synchronized GameObjectsFactory getInstance() {
        if (instance == null) {
            instance = new GameObjectsFactory();
        }
        return instance;
    }
    /**
     * Creates a new Ball game object.
     *
     * @param pos   The initial position of the Ball.
     * @param vel   The initial velocity of the Ball.
     * @param color The color of the Ball.
     * @return The created Ball game object.
     */
    public Ball createBall(final P2d pos, final V2d vel, final BallColor color) {
        return new Ball(Type.BALL, pos, color, new V2d(10, 10),
                new NullInputComponent(),
                new CircleBoundingBox(new P2d(pos.getX(), pos.getY()), 10), // in input
                new BallPhysicsComponent(),
                new BallGraphicsComponent()); // in physics

    }

    /**
     * Creates a new Cannon game object.
     *
     * @param pos  The initial position of the Cannon.
     * @return The created Cannon game object.
     */
    public Cannon createCannon(final P2d pos) {
        return new Cannon(pos, new V2d(pos, pos),
                new PlayerInputComponent(), // in input
                new RectBoundingBox(pos, pos), // in input
                null,
                new CannonGraphicsComponent()); // in physics
    }

    /**
     * Creates a new CannonBall game object.
     *
     * @param pos   The initial position of the CannonBall.
     * @param vel   The initial velocity of the CannonBall.
     * @param color The color of the CannonBall.
     * @return The created CannonBall game object.
     */
    public Ball createCannonBall(final P2d pos, final V2d vel, final BallColor color) {
        final int initialVelocity = -10;
        return new Ball(Type.CANNON_BALL, pos, color, new V2d(0, initialVelocity),
                new NullInputComponent(),
                new CircleBoundingBox(new P2d(pos.getX(), pos.getY()), 10), // in input
                new CannonBallPhysicsComponent(),
                new BallGraphicsComponent()); // in physics

    }

    /**
     * Creates a new StationaryBall game object.
     *
     * @param cannonPos The position of the Cannon that fired the StationaryBall.
     * @param vel       The initial velocity of the StationaryBall.
     * @param color     The color of the StationaryBall.
     * @return The created StationaryBall game object.
     */
    public Ball createStationaryBall(final P2d cannonPos, final V2d vel, final BallColor color) {
        return new Ball(Type.STATIONARY_BALL, cannonPos, color, new V2d(0, 0),
                new NullInputComponent(),
                new CircleBoundingBox(new P2d(cannonPos.getX(), cannonPos.getY()), 10), // in input
                new StationaryBallPhysicsComponent(),
                new BallGraphicsComponent()); // in physics
    }
}
