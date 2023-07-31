package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

public class HitBorderEvent implements WorldEvent {
    private GameObject obj;

    public HitBorderEvent(GameObject obj) {
        this.obj = obj;
    }

    public GameObject getCollisionObj() {
        return obj;
    }
}