package it.unibo.events.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.impl.GameObject;

/**
 * 
 * Class that extends the World Event, representing the collision between a ball
 * from the queue and a shot from the cannon.
 */
@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP" }, justification = "This warning does not represent a security threat"
                + "beacuse the GameEnigneImpl will need to manipulate the 2 mutable objects")

public class HitBallEvent implements WorldEvent {

    private final GameObject queueBall; // ball of the tail with which the collision occurred
    private final GameObject cannonBall; // fired ball that raised the collision

    /**
     * Constructor.
     * 
     * @param queueBall  ball of the queue
     * @param cannonBall fired ball that collided with the queueBall
     */
    public HitBallEvent(final GameObject queueBall, final GameObject cannonBall) {
        this.queueBall = queueBall;
        this.cannonBall = cannonBall;
    }

    /**
     * 
     * @return GameObject
     */
    public GameObject getQueueBall() {
        return queueBall;
    }

    /**
     * 
     * @return GameObject
     */
    public GameObject getCannnonBall() {
        return cannonBall;
    }
}
