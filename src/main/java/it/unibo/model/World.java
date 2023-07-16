package it.unibo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import it.unibo.events.api.WorldEventListener;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.physics.impl.BoundaryCollision;
import it.unibo.enums.BallColor;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.api.BoundingBox;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.*;

public class World {
    private Cannon cannon;
    private QueueManager qm;
    private RectBoundingBox mainBBox;
    private WorldEventListener evListener;

    public World(RectBoundingBox bbox, int nBalls) {
        qm = new QueueManager(10);
        mainBBox = bbox;

    }

    /**
     * @param l
     */
    public void setEventListener(WorldEventListener l) {
        evListener = l;
    }

    public Cannon createCannon(P2d pos) {
        return GameObjectsFactory.getInstance().createCannon(pos);
    }

    public void setCannon(Cannon cannon) {
        this.cannon = cannon;
    }

    public Cannon getCannon() {
        return this.cannon;
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
        entities.addAll(this.cannon.getFiredBalls());
        entities.add(this.cannon);
        return entities;
    }

    public List<Ball> getQueue() {
        return this.qm.balls;
    }

    public void updateState(long dt){
        //aggiungere gli update dei gameobject

        //checkCollisionWithBalls(null, null)
        //checkCollisionWithBoundaries(null, null)
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
    public Optional<BoundaryCollision> checkCollisionWithBoundaries(P2d pos, CircleBoundingBox box) {
        return Optional.empty();
    }

}
