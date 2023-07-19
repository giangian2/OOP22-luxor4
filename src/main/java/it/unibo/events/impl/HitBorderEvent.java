package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;
import it.unibo.utils.P2d;

public class HitBorderEvent implements WorldEvent {
    private GameObject obj;

    public HitBorderEvent(GameObject obj) {
        this.obj = obj;
    }

    public GameObject getCollisionObj() {
        return obj;
    }
}