package it.unibo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.events.api.WorldEventListener;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.physics.impl.BoundaryCollision;
import it.unibo.enums.Direction;
import it.unibo.events.api.WorldEvent;
import it.unibo.model.impl.CircleBoundingBox;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.*;

/**
 * The World class represents the game space in which all the GameObjects
 * will be present and through which you can interact with them
 */
@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP",
        "EI_EXPOSE_REP2" }, justification = "This warning does not represent a security threat beacuse the Input package will update the World")

public class World {
    /**
     * World instance
     * this property was set to PRIVATE and STATIC in order to simplify
     * the access of the bounding box for the Graphics and Physics packages with no
     * need of passing any World object to them, but only getting it with the method
     * "getInstance"
     */
    private Cannon cannon;
    private QueueManager qm;
    private static RectBoundingBox mainBBox;
    private WorldEventListener evListener;
    private SoundPlayer soundPlayer;

    /**
     * 
     * @param bbox   represents the bounding box of the game area
     * @param nBalls number of balls that needs to be instatiated
     * @param steps  steps that are performed by each ball in the queue at each
     *               frame
     * @param xmlSrc path of the XML file that contains the path of the queue
     */

    public World(RectBoundingBox bbox, int nBalls, int steps, String xmlSrc, WorldEventListener eventListener,
            Cannon cannon) {
        /**
         * Instatiate the queue manager with the specifics given in the constructor
         * method
         */
        this.cannon = cannon;
        this.evListener = eventListener;
        qm = new QueueManager(nBalls, steps, xmlSrc);
        List<String> strings = new ArrayList<String>();
        strings.add("/sounds/Background.wav");
        strings.add("/sounds/BallCollision.wav");
        soundPlayer = new SoundPlayer(new ArrayList<>(strings));
        World.setBBox(bbox);
    }

    private static void setBBox(RectBoundingBox bbox) {
        World.mainBBox = bbox;
    }

    /**
     * Gets the bounding box
     * 
     * @return RectBoundingBox
     */
    public static RectBoundingBox getBBox() {
        return mainBBox;
    }

    /**
     * @param l event listener
     */
    public void setEventListener(WorldEventListener l) {
        evListener = l;
    }

    /**
     * 
     * @param pos
     * @return Cannon
     */
    public Cannon createCannon(P2d pos) {
        return GameObjectsFactory.getInstance().createCannon(pos);
    }

    /**
     * 
     * @param cannon Sets the cannon object
     */
    public void setCannon(Cannon cannon) {
        this.cannon = GameObjectsFactory.getInstance().createCannon(cannon.getCurrentPos());
    }

    /**
     * 
     * @return Cannon returns the cannon object
     */
    public Cannon getCannon() {
        return this.cannon;
    }

    /**
     * Notify an events to the WorldEventsListener
     * 
     * @param ev
     */
    public void notifyWorldEvent(WorldEvent ev) {
        this.evListener.notifyEvent(ev);
    }

    /**
     * Passing a ball, it allows you to obtain its position at the next frame via
     * the queue manager, if you reach the end of the path the direction returned
     * will be "NONE" and consequently the Game Over will have to be rendered
     * 
     * @param queueBall Ball
     * @return Direction
     */
    public Direction moveSingleBall(Ball queueBall) {
        return qm.getMove(queueBall);
    }

    /**
     * Shifts all the balls by as many steps as specified in the constructor
     */
    public void shiftBalls() {
        qm.shiftBalls(0);
    }

    /**
     * 
     * Allows you to insert a ball fired from the cannon that has had a collision
     * with the tail, inside the thing itself.
     * Passing as input the ball of the cannon that generated the collision and the
     * ball of the queue with which it occurred, update the queue via the queue
     * manager
     * 
     * @param cannonBall Ball
     * @param queueBall  Ball
     */
    public void insertCollisionBall(Ball cannonBall, Ball queueBall) {
        // Get the index of the queue ball
        int index = this.qm.getBalls().indexOf(queueBall);
        if (index == -1)
            throw new IllegalStateException("Queue Ball Not Found!", null);

        var cannonBallPos = cannonBall.getCurrentPos();
        var queueBallPos = queueBall.getCurrentPos();

        /**
         * if the collision with the tail ball occurred in the third quadrant or along
         * the axes, the cannon ball is placed ahead of the tail ball
         * otherwise it is put behind
         */
        if (cannonBallPos.x <= queueBallPos.x && cannonBallPos.y >= queueBallPos.y) {
            /**
             * if the tail ball was moving in the directions : DOWN and LEFT, then the shot
             * ball is inserted between the hit tail ball and the respective next ball
             */
            if (qm.getMove(queueBall) == Direction.LEFT
                    || qm.getMove(queueBall) == Direction.DOWN) {
                // Insert the fired ball between the hit tail ball and the respective next ball
                this.qm.getBalls().add(index + 1,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                    qm.shiftBalls(index + 1);

            } else {
                /**
                 * if the ball from the tail was moving in the directions : UP and RIGHT, then
                 * the shot ball is inserted in place of the hit bal
                 */
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                    qm.shiftBalls(index);
                this.qm.getBalls().add(index,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
            }
        } else if (cannonBallPos.x >= queueBallPos.x && cannonBallPos.y >= queueBallPos.y) {
            /**
             * if the collision with the tail ball occurred in the second quadrant, the
             * cannon ball is placed ahead of the tail ball
             * otherwise it is put behind
             */
            if (qm.getMove(queueBall) == Direction.LEFT
                    || qm.getMove(queueBall) == Direction.UP) {
                /**
                 * 
                 * if the collision occurred in the second quadrant and the ball of the queue
                 * with which the collision occurred is moving in the directions: LEFT, UP; then
                 * the cannonball is inserted in place of the queue ball
                 */
                if (index - 1 > 0) {

                    for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                        qm.shiftBalls(index);
                    this.qm.getBalls().add(index,
                            GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                    cannonBall.getColor()));

                } else {
                    /**
                     * If the fired ball was fired behind the last queue ball, it's inserted in the
                     * position 0 of the queue
                     */
                    this.qm.getBalls().add(0,
                            GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                    cannonBall.getColor()));
                    for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                        qm.shiftBalls(1);

                }
            } else {
                /**
                 * if the tail ball was moving in the directions : DOWN and RIGHT, then the shot
                 * ball is inserted between the hit tail ball and the respective next ball
                 */
                this.qm.getBalls().add(index + 1,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++)
                    qm.shiftBalls(index + 1);

            }
        }

    }

    /**
     * Returns the list of all triplets (or more) of neighboring balls having the
     * same color
     * 
     * @return List<Ball>
     */
    public List<Ball> getCLoseByThree() {
        return this.qm.getCloseByThree();
    }

    /**
     * Returns all the game objects present in the World so that they can be used
     * for graphic rendering within the Scene
     * 
     * @return List<GameObject>
     */
    public List<GameObject> getSceneEntities() {
        List<GameObject> entities = new ArrayList<GameObject>();
        entities.addAll(this.qm.getBalls());
        entities.addAll(this.cannon.getFiredBalls());
        entities.add(this.cannon);
        return entities;
    }

    /**
     * Return the balls in the queue via the QueueManager
     * 
     * @return List<Ball>
     */
    public List<Ball> getQueue() {
        return this.qm.getBalls();
    }

    /**
     * Update the state of the World every frame:
     * - the balls of the tail are scrolled
     * - the physics of each ball fired from the cannon is updated
     * - stationary cannon ball is updated
     * 
     * @param dt
     */
    public void updateState(long dt) {

        qm.shiftBallsStepsTime();
        this.cannon.getFiredBalls().forEach((b) -> b.updatePhysics(dt, this));
        this.cannon.getStationaryBall().updatePhysics(dt, this);
    }

    /**
     * 
     * checks if there is a collision between a ball passed in input and the
     * balls
     * in the queue and and returns the ball from the tail with which the
     * collision
     * occurred
     * 
     * @param pos
     * @param box
     * @return Optional<GameObject>
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
     * checks if there are collisions between an element passed as input and the
     * bounding box of the World
     * 
     * @param pos
     * @param box
     * @return Optional<BoundaryCollision>
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
        soundPlayer.playFromStart(SoundPlayer.BACKGROUND_MUSIC);
        soundPlayer.loop(SoundPlayer.BACKGROUND_MUSIC);
    }

    public void playCollisionSound() {
        soundPlayer.play(SoundPlayer.BALL_COLLISION);
    }

    public void pauseBackgroundSound() {
        soundPlayer.pause(SoundPlayer.BACKGROUND_MUSIC);
    }

    public void unpauseBackgroundSound() {
        soundPlayer.play(SoundPlayer.BACKGROUND_MUSIC);
    }

    public void stopMusic() {
        soundPlayer.stopAll();
    }

}