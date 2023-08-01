package it.unibo.events.impl;

import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

/**
 * 
 * Class that extends the World Event, representing the collision between a
 * fired ball and an edge of the RectBoundingBox of the World object
 */
public class HitBorderEvent implements WorldEvent {
    private GameObject obj; // Game object that collided whit the wolrd's bounding box

    public HitBorderEvent(GameObject obj) {
        this.obj = obj;
    }

    public GameObject getCollisionObj() {
        return obj;
    }
}