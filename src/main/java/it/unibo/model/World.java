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
    private SoundPlayer soundPlayer = new SoundPlayer();

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

    public void insertCollisionBall(Ball cannonBall, Ball queueBall) {
        int index = this.qm.balls.indexOf(queueBall);
        if (index == -1)
            throw new IllegalStateException("Error", null);

        var ballSx = this.qm.balls.get(index - 1);
        var ballDx = this.qm.balls.get(index + 1);

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

    public void updateState(long dt) {
        this.shiftBalls();
        this.cannon.getFiredBalls().forEach((b) -> b.updatePhysics(dt, this));
    }

    /**
     * 
     * @TODO
     * 
     * 
     */

    public Optional<GameObject> checkCollisionWithBalls(P2d pos, CircleBoundingBox box) {
        double radius = box.getRadius();
        for (Ball obj : this.getQueue()) {
            if (new V2d(obj.getCurrentPos(), pos).module() <= 2 * radius) {
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }

    /**
     * 
     * @TODO
     * 
     * 
     */
    public Optional<BoundaryCollision> checkCollisionWithBoundaries(P2d pos, CircleBoundingBox box) {
        P2d ul = mainBBox.getULCorner();
        P2d br = mainBBox.getBRCorner();
        double r = box.getRadius();
        if (pos.y + r > ul.y) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.TOP, new P2d(pos.x, ul.y)));
        } else if (pos.y - r < br.y) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.BOTTOM, new P2d(pos.x, br.y)));
        } else if (pos.x + r > br.x) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.RIGHT, new P2d(br.x, pos.y)));
        } else if (pos.x - r < ul.x) {
            return Optional.of(new BoundaryCollision(BoundaryCollision.CollisionEdge.LEFT, new P2d(ul.x, pos.y)));
        } else {
            return Optional.empty();
        }
    }

    public void playBackgroundMusic() {
        soundPlayer.setFile(SoundPlayer.BACKGROUND_MUSIC);
        soundPlayer.play();
        soundPlayer.loop();
    }

    public void playShootingSound() {
        soundPlayer.setFile(SoundPlayer.CANNON_SHOOT);
        soundPlayer.play();
    }

    public void stopMusic() {
        soundPlayer.stopAll();
    }

}
