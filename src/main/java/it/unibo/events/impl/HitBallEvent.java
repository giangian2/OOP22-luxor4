package it.unibo.events.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP" }, justification = "This warning does not represent a security threat beacuse the GameEnigneImpl will need to manipulate the 2 mutable objects")
/**
 * 
 * Class that extends the World Event, representing the collision between a ball
 * from the queue and a shot from the cannon
 */

public class HitBallEvent implements WorldEvent {

    private GameObject queueBall; // ball of the tail with which the collision occurred
    private GameObject cannonBall;

    public HitBallEvent(GameObject queueBall, GameObject cannonBall) {
        this.queueBall = queueBall;
        this.cannonBall = cannonBall;
    }

    public GameObject getQueueBall() {
        return queueBall;
    }

    public GameObject getCannnonBall() {
        return cannonBall;
    }
}
