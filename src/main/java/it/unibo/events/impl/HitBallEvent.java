package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

public class HitBallEvent implements WorldEvent {

    private GameObject queueBall;
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
