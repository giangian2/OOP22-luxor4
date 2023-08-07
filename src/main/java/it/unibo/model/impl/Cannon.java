package it.unibo.model.impl;

import it.unibo.input.impl.PlayerInputComponent;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.graphics.impl.CannonGraphicsComponent;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Represents a Cannon object in the game.
 */
public class Cannon extends GameObject {

    private final List<Ball> cannonBalls;
    private final Ball stationaryBall;
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
     * @param physics The physics component responsible for handling physics
     *                interactions.
     * @param graph   The graphics component responsible for rendering the cannon.
     */
    public Cannon(final P2d pos, final V2d vel, final PlayerInputComponent input,
            final PhysicsComponent physics, final CannonGraphicsComponent graph) {
        super(Type.CANNON, pos, vel, input, null, graph, physics);
        this.cannonBalls = new ArrayList<>();
        this.stationaryBall = createStationaryBall();
    }

    /**
     * Creates a stationary ball that rappresents the ball which will be shot with a random color.
     *
     * @return The stationary ball.
     */
    private Ball createStationaryBall() {
        final BallColor randomColor = BallColor.getRandomColor();
        return GameObjectsFactory.getInstance().createStationaryBall(getStationaryBallPos(),
                new V2d(0, 0),
                randomColor);
    }

    /**
     * Retrieves the stationary ball.
     *
     * @return The stationary ball.
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP",
            "EI_EXPOSE_REP2" }, justification = "This warning does not represent a security threat beacuse the Stationary Ball "
                    +
                    "needs to be accessed and updated by the Physic Component")
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
     * Fires a ball from the cannon.
     * The ball is created and added to the list of fired balls.
     * The color of the stationary ball is then updated to a random color.
     */
    public void fireBall() {
        final P2d ballPos = new P2d(getCurrentPos().getX() + ADJUST_FIRED_BALL_POS, getCurrentPos().getY());

        final BallColor bColor = stationaryBall.getColor();
        final Ball ball = GameObjectsFactory.getInstance().createCannonBall(ballPos, new V2d(0, BALL_SPEED_Y),
                bColor);

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
        return new P2d(getCurrentPos().getX() + ADJUST_STATIONARY_XPOS,
                getCurrentPos().getY() + ADJUST_STATIONARY_YPOS);
    }
}
