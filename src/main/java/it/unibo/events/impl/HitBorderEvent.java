package it.unibo.events.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.GameObject;

/**
 * 
 * Class that extends the World Event, representing the collision between a
 * fired ball and an edge of the RectBoundingBox of the World object
 */
@SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "I prefer to suppress these FindBugs warnings")
public class HitBorderEvent implements WorldEvent {
    private GameObject obj; // Game object that collided whit the wolrd's bounding box

    public HitBorderEvent(GameObject obj) {
        this.obj = new GameObject(obj.getType(), obj.getCurrentPos(), obj.getCurrentVel(), null, null, null, null);
    }

    public GameObject getCollisionObj() {
        return obj;
    }
}