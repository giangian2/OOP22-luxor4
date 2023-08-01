package it.unibo.events.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP" }, justification = "This warning does not represent a security threat beacuse the GameEnigneImpl will need to manipulate the object")
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