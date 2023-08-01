package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

/**
 * 
 * Class that extends the World Event, representing the collision between a ball
 * from the queue and a shot from the cannon
 */
public class HitBallEvent implements WorldEvent {

    private GameObject queueBall; // ball of the tail with which the collision occurred
    private GameObject cannonBall; // fired baòò

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
