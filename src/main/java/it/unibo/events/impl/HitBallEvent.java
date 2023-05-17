package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

public class HitBallEvent implements WorldEvent {
    private GameObject obj;

    public HitBallEvent(GameObject obj) {
        this.obj = obj;
    }

    public GameObject getCollisionObj() {
        return obj;
    }
}
