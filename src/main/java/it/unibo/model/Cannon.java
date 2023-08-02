package it.unibo.model;

import it.unibo.input.impl.PlayerInputComponent;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.graphics.impl.CannonGraphicsComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Cannon object in the game.
 */
public class Cannon extends GameObject {

    private List<Ball> cannonBalls;
    private Ball stationaryBall;
    private static final int ADJUST_FIRED_BALL_POS = 37;
    private static final int ADJUST_STATIONARY_XPOS = 37;
    private static final int BALL_SPEED_Y = -10;
    private static final int ADJUST_STATIONARY_YPOS = 50;

    /**
     * Constructs a Cannon object with the specified parameters.
     *
     * @param pos     The initial position of the cannon.
     * @param vel     The initial velocity of the cannon.
     * @param input   The input component responsible for controlling the cannon.
     * @param bbox    The bounding box defining the collision area of the cannon.
     * @param physics The physics component responsible for handling physics
     *                interactions.
     * @param graph   The graphics component responsible for rendering the cannon.
     */
    public Cannon(final P2d pos, final V2d vel, final PlayerInputComponent input, final BoundingBox bbox,
            final PhysicsComponent physics, final CannonGraphicsComponent graph) {
        super(Type.CANNON, pos, vel, input, null, graph, physics);
        this.cannonBalls = new ArrayList<>();
        this.cannonBalls = Collections.synchronizedList(this.cannonBalls);
        this.stationaryBall = createStationaryBall();
    }

    /**
     * Creates a stationary ball with a random color.
     *
     * @return The stationary ball.
     */
    private Ball createStationaryBall() {
        BallColor randomColor = BallColor.getRandomColor();
        Ball stationaryBall = GameObjectsFactory.getInstance().createStationaryBall(getStationaryBallPos(),
                new V2d(0, 0),
                randomColor);
        return stationaryBall;
    }

    /**
     * Retrieves the stationary ball.
     *
     * @return The stationary ball.
     */
    public Ball getStationaryBall() {
        return GameObjectsFactory.getInstance().createStationaryBall(getStationaryBallPos(), getCurrentVel(),
                this.stationaryBall.getColor());
    }

    /**
     * Retrieves a copy of the list of fired balls.
     *
     * @return A copy of the list of fired balls.
     */
    public List<Ball> getFiredBalls() {
        return new ArrayList<>(this.cannonBalls);
    }

    /**
     * Removes a fired ball from the list of cannon balls.
     *
     * @param b The ball to be removed.
     */
    public void removeFiredBall(final Ball b) {
        this.cannonBalls.remove(b);
    }

    /**
     * Fires a projectile from the cannon.
     * The projectile is created and added to the list of fired balls.
     * The color of the stationary ball is then updated to a random color.
     */
    public void fireProjectile() {
        P2d ballPos = new P2d(getCurrentPos().x + ADJUST_FIRED_BALL_POS, getCurrentPos().y);

        BallColor projectileColor = stationaryBall.getColor();
        Ball ball = GameObjectsFactory.getInstance().createCannonBall(ballPos, new V2d(0, BALL_SPEED_Y),
                projectileColor);

        this.cannonBalls.add(ball);

        stationaryBall.setColor(BallColor.getRandomColor());
    }

    /**
     * Calculates the position of the stationary ball relative to the cannon's
     * position.
     *
     * @return The position of the stationary ball.
     */
    public final P2d getStationaryBallPos() {
        return new P2d(getCurrentPos().x + ADJUST_STATIONARY_XPOS, getCurrentPos().y + ADJUST_STATIONARY_YPOS);
    }
}