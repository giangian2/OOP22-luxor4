package it.unibo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.events.api.WorldEventListener;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.physics.impl.BoundaryCollision;
import it.unibo.enums.Direction;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.*;

public class World {
    private Cannon cannon;
    private QueueManager qm;
    public static RectBoundingBox mainBBox;
    private WorldEventListener evListener;
    private SoundPlayer soundPlayer = new SoundPlayer();

    public World(RectBoundingBox bbox, int nBalls, int steps, String xmlSrc) {
        qm = new QueueManager(nBalls, steps, xmlSrc);
        mainBBox = bbox;

    }

    /**
     * @param l
     */
    public void setEventListener(WorldEventListener l) {
        evListener = l;
    }

    public Cannon createCannon(P2d pos) {
        return GameObjectsFactory.getInstance().createCannon(pos, null);
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

    public Direction moveSingleBall(Ball queueBall) {
        return qm.getMove(queueBall);
    }

    public void shiftBalls() {
        qm.shiftBalls(0);
    }

    public void insertCollisionBall(Ball cannonBall, Ball queueBall) {
        int index = this.qm.balls.indexOf(queueBall);
        if (index == -1)
            throw new IllegalStateException("Queue Ball Not Found!", null);

        var cannonBallPos = cannonBall.getCurrentPos();
        var queueBallPos = queueBall.getCurrentPos();

        if (cannonBallPos.x <= queueBallPos.x && cannonBallPos.y >= queueBallPos.y) {
            if (qm.getMove(queueBall) == Direction.LEFT
                    || qm.getMove(queueBall) == Direction.DOWN) {

                this.qm.balls.add(index + 1,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                    qm.shiftBalls(index + 1);

            } else {

                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                    qm.shiftBalls(index);
                this.qm.balls.add(index,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
            }
        } else if (cannonBallPos.x >= queueBallPos.x && cannonBallPos.y >= queueBallPos.y) {

            if (qm.getMove(queueBall) == Direction.LEFT
                    || qm.getMove(queueBall) == Direction.UP) {
                if (index - 1 > 0) {

                    for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                        qm.shiftBalls(index);
                    this.qm.balls.add(index,
                            GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                    cannonBall.getColor()));

                } else {
                    this.qm.balls.add(0,
                            GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                    cannonBall.getColor()));
                    for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                        qm.shiftBalls(1);

                }
            } else {

                this.qm.balls.add(index + 1,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                    qm.shiftBalls(index + 1);

            }
        }

    }

    public List<Ball> getCLoseByThree() {
        return this.qm.getCloseByThree();
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

        qm.shiftBallsStepsTime();
        this.cannon.getFiredBalls().forEach((b) -> b.updatePhysics(dt, this));
        this.cannon.getStationaryBall().updatePhysics(dt, this);
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
                playCollisionSound();
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
        soundPlayer.play(SoundPlayer.BACKGROUND_MUSIC);
        soundPlayer.loop(SoundPlayer.BACKGROUND_MUSIC);
    }

    public void playCollisionSound() {
        soundPlayer.play(SoundPlayer.BALL_COLLISION);
    }

    public void stopMusic() {
        soundPlayer.stopAll();
    }

}
