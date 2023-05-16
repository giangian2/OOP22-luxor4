package it.unibo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import it.unibo.events.api.WorldEventListener;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.api.BoundingBox;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.*;

public class World {
    private List<GameObject> balls;
    private GameObject cannonBall;
    private GameObject cannon;
    private RectBoundingBox mainBBox;
    private WorldEventListener evListener;

    public World(RectBoundingBox bbox) {
        balls = new ArrayList<GameObject>();
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

    public void addBall(GameObject obj) {
        this.balls.add(obj);
    }

    public void removeBall(GameObject obj) {
        this.balls.remove(obj);
    }

    public void notifyWorldEvent(WorldEvent ev) {
        this.evListener.notifyEvent(ev);
    }

    public RectBoundingBox getBBox() {
        return this.mainBBox;
    }

    public List<GameObject> getSceneEntities() {
        List<GameObject> entities = new ArrayList<GameObject>();
        entities.addAll(this.balls);
        entities.add(this.cannonBall);
        entities.add(this.cannon);
        return entities;
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
