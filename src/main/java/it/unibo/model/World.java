package it.unibo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import it.unibo.events.api.WorldEventListener;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.api.BoundingBox;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.*;

public class World {
    private GameObject cannonBall;
    private GameObject cannon;
    private QueueManager qm;
    private RectBoundingBox mainBBox;
    private WorldEventListener evListener;

    public World(RectBoundingBox bbox, int nBalls) {
        qm = new QueueManager(10);
        mainBBox = bbox;

    }

    public void setEventListener(WorldEventListener l) {
        evListener = l;
    }

    public void setCannonBall(GameObject ball) {
        this.cannonBall = ball;
    }

    public void setCannon(GameObject cannon) {
        this.cannon = cannon;
    }

    public void notifyWorldEvent(WorldEvent ev) {
        this.evListener.notifyEvent(ev);
    }

    public RectBoundingBox getBBox() {
        return this.mainBBox;
    }

    public void shiftBalls() {
        qm.shiftBalls();
    }

    public List<GameObject> getSceneEntities() {
        List<GameObject> entities = new ArrayList<GameObject>();
        entities.addAll(this.qm.balls);
        entities.add(this.cannonBall);
        entities.add(this.cannon);
        return entities;
    }

    public List<Ball> getQueue() {
        return this.qm.balls;
    }

    /**
     * 
     * @TODO
     * 
     * 
     */

    public Optional<GameObject> checkCollisionWithBalls(P2d pos, CircleBoundingBox box) {
        return Optional.empty();
    }

}
