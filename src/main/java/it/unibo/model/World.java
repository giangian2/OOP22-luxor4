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
import it.unibo.model.GameObject.Type;
import it.unibo.model.collisions.impl.CircleBoundingBox;
import it.unibo.model.collisions.impl.RectBoundingBox;
import it.unibo.utils.QueueManager;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;
import it.unibo.utils.SoundPlayer;

/**
 * The World class represents the game space in which all the GameObjects
 * will be present and through which you can interact with them.
 */
@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP",
        "EI_EXPOSE_REP2" }, justification = "This warning does not represent a security threat"
                + "beacuse the Input package will update the World")

public class World {
    /**
     * RectBoundingBox
     * this property was set to PRIVATE and STATIC in order to simplify
     * the access of the bounding box for the Graphics and Physics packages with no
     * need of passing any World object to them, but only getting it with the static
     * method
     * "getBBox".
     */
    private Cannon cannon;
    private final QueueManager qm;
    private static RectBoundingBox mainBBox;
    private WorldEventListener evListener;
    private final SoundPlayer soundPlayer;

    /**
     * 
     * @param bbox          Represents the bounding box of the game area
     * @param nBalls        Number of balls that needs to be instatiated
     * @param steps         Steps that are performed by each ball in the queue at
     *                      each
     *                      frame
     * @param xmlSrc        Path of the XML file that contains the path of the queue
     * @param eventListener Event listener for events
     * @param cannon        Cannon object
     */

    public World(final RectBoundingBox bbox, final int nBalls, final int steps, final String xmlSrc,
            final WorldEventListener eventListener, final Cannon cannon) {
        /**
         * Instatiate the queue manager with the specifics given in the constructor
         * method.
         */
        this.cannon = cannon;
        this.evListener = eventListener;
        qm = new QueueManager(nBalls, steps, xmlSrc);
        final var strings = new ArrayList<String>();
        strings.add("/sounds/Background.wav");
        strings.add("/sounds/BallCollision.wav");
        soundPlayer = new SoundPlayer(new ArrayList<>(strings));
        World.setBBox(bbox);
    }

    /**
     * Sets the bounding box.
     * 
     * @param bbox Bounding box to set.
     */
    private static void setBBox(final RectBoundingBox bbox) {
        World.mainBBox = bbox;
    }

    /**
     * Gets the bounding box.
     * 
     * @return RectBoundingBox
     */
    public static RectBoundingBox getBBox() {
        return mainBBox;
    }

    /**
     * Sets the world event listener.
     * 
     * @param l Event listener
     */
    public void setEventListener(final WorldEventListener l) {
        evListener = l;
    }

    /**
     * Creates a new istance of a cannon.
     * 
     * @param pos
     * @return Cannon
     */
    public Cannon createCannon(final P2d pos) {
        return GameObjectsFactory.getInstance().createCannon(pos);
    }

    /**
     * Creates a new istance of a cannon containinig the current cannon position.
     * 
     * @param cannon Sets the cannon object
     */
    public void setCannon(final Cannon cannon) {
        this.cannon = GameObjectsFactory.getInstance().createCannon(cannon.getCurrentPos());
    }

    /**
     * Gets the current cannon object.
     * 
     * @return The cannon object
     */
    public Cannon getCannon() {
        return this.cannon;
    }

    /**
     * Notify an events to the WorldEventsListener.
     * 
     * @param ev
     */
    public void notifyWorldEvent(final WorldEvent ev) {
        this.evListener.notifyEvent(ev);
    }

    /**
     * Passing a ball, it allows you to obtain its position at the next frame via
     * the queue manager, if you reach the end of the path the direction returned
     * will be "NONE" and consequently the Game Over will have to be rendered.
     * 
     * @param queueBall Ball
     * @return Direction
     */
    public Direction moveSingleBall(final Ball queueBall) {
        return qm.getMove(queueBall);
    }

    /**
     * Shifts all the balls by as many steps as specified in the constructor.
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
     * manager.
     * 
     * @param cannonBall Ball
     * @param queueBall  Ball
     */
    public void insertCollisionBall(final Ball cannonBall, final Ball queueBall) {
        // Get the index of the queue ball
        final int index = this.qm.getBalls().indexOf(queueBall);
        if (index == -1) {
            throw new IllegalStateException("Queue Ball Not Found!", null);
        }

        if (cannonBall.getType() != Type.CANNON_BALL) {
            throw new IllegalStateException("The first argument wasn't a cannon ball!", null);
        }

        final var cannonBallPos = cannonBall.getCurrentPos();
        final var queueBallPos = queueBall.getCurrentPos();

        /**
         * if the collision with the tail ball occurred in the third quadrant or along
         * the axes, the cannon ball is placed ahead of the tail ball
         * otherwise it is put behind.
         */
        if (cannonBallPos.getX() <= queueBallPos.getX() && cannonBallPos.getY() >= queueBallPos.getY()) {
            /**
             * if the tail ball was moving in the directions : DOWN and LEFT, then the shot
             * ball is inserted between the hit tail ball and the respective next ball.
             */
            if (qm.getMove(queueBall) == Direction.LEFT
                    || qm.getMove(queueBall) == Direction.DOWN) {
                // Insert the fired ball between the hit tail ball and the respective next ball
                this.qm.getBalls().add(index + 1,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++) {
                    qm.shiftBalls(index + 1);
                }
            } else {
                /**
                 * if the ball from the tail was moving in the directions : UP and RIGHT, then
                 * the shot ball is inserted in place of the hit ball.
                 */
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++) {
                    qm.shiftBalls(index);
                }
                this.qm.getBalls().add(index,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
            }
        } else if (cannonBallPos.getX() >= queueBallPos.getX() && cannonBallPos.getY() >= queueBallPos.getY()) {
            /**
             * if the collision with the tail ball occurred in the second quadrant, the
             * cannon ball is placed ahead of the tail ball
             * otherwise it is put behind.
             */
            if (qm.getMove(queueBall) == Direction.LEFT
                    || qm.getMove(queueBall) == Direction.UP) {
                /**
                 * 
                 * if the collision occurred in the second quadrant and the ball of the queue
                 * with which the collision occurred is moving in the directions: LEFT, UP; then
                 * the cannonball is inserted in place of the queue ball.
                 */
                if (index - 1 > 0) {

                    for (int i = 0; i < Ball.IMAGE_DIAMETER; i++) {
                        qm.shiftBalls(index);
                    }
                    this.qm.getBalls().add(index,
                            GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                    cannonBall.getColor()));

                } else {
                    /**
                     * If the fired ball was fired behind the last queue ball, it's inserted in the
                     * position 0 of the queue.
                     */
                    this.qm.getBalls().add(0,
                            GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                    cannonBall.getColor()));
                    for (int i = 0; i < Ball.IMAGE_DIAMETER; i++) {
                        qm.shiftBalls(1);
                    }

                }
            } else {
                /**
                 * if the tail ball was moving in the directions : DOWN and RIGHT, then the shot
                 * ball is inserted between the hit tail ball and the respective next ball.
                 */
                this.qm.getBalls().add(index + 1,
                        GameObjectsFactory.getInstance().createBall(queueBallPos, null,
                                cannonBall.getColor()));
                for (int i = 0; i < Ball.IMAGE_DIAMETER; i++) {
                    qm.shiftBalls(index + 1);
                }
            }
        }

    }

    /**
     * Returns the list of all triplets (or more) of neighboring balls having the
     * same color.
     * 
     * @return List<Ball>
     */
    public List<Ball> getCloseByThree() {
        return this.qm.checkCloseByThree();
    }

    /**
     * Returns all the game objects present in the World so that they can be used
     * for graphic rendering within the Scene.
     * 
     * @return List<GameObject>
     */
    public List<GameObject> getSceneEntities() {
        final var entities = new ArrayList<GameObject>();
        entities.addAll(this.qm.getBalls());
        entities.addAll(this.cannon.getFiredBalls());
        entities.add(this.cannon);
        return entities;
    }

    /**
     * Return the balls in the queue via the QueueManager.
     * 
     * @return List<Ball>
     */
    public List<Ball> getQueue() {
        return this.qm.getBalls();
    }

    /**
     * Update the state of the World every frame:
     * <ul>
     * <li>The balls of the tail are scrolled.</li>
     * <li>The physics of each ball fired from the cannon is updated.</li>
     * <li>Stationary cannon ball is updated.</li>
     * </ul>
     * 
     * @param dt The elapsed time since the last update.
     */
    public void updateState(final long dt) {

        qm.shiftBallsStepsTime();
        this.cannon.getFiredBalls().forEach((b) -> b.updatePhysics(dt, this));
        this.cannon.getStationaryBall().updatePhysics(dt, this);
    }

    /**
     * Checks if there is a collision between a ball passed in input and the
     * balls in the queue and and returns the ball from
     * the tail with which the collision occurred.
     * 
     * @param pos Ball's position
     * @param box Ball's bounding box
     * @return Optional<GameObject>
     */
    public Optional<GameObject> checkCollisionWithBalls(final P2d pos, final CircleBoundingBox box) {
        final double radius = box.getRadius();
        for (final Ball obj : this.getQueue()) {
            if (new V2d(obj.getCurrentPos(), pos).module() <= 2 * radius) {
                playCollisionSound();
                return Optional.of(obj);
            }
        }
        return Optional.empty();
    }

    /**
     * Checks if there are collisions between an element passed as input and the
     * bounding box of the World.
     * 
     * @param pos Ball's position
     * @param box Ball's bounding box
     * @return Optional<BoundaryCollision>
     */
    public Optional<BoundaryCollision> checkCollisionWithBoundaries(final P2d pos, final CircleBoundingBox box) {
        final P2d ul = mainBBox.getULCorner();
        final P2d br = mainBBox.getBRCorner();
        final double r = box.getRadius();
        if (pos.getY() + r > ul.getY()) {
            return Optional
                    .of(new BoundaryCollision(BoundaryCollision.CollisionEdge.TOP, new P2d(pos.getX(), ul.getY())));
        } else if (pos.getY() - r < br.getY()) {
            return Optional
                    .of(new BoundaryCollision(BoundaryCollision.CollisionEdge.BOTTOM, new P2d(pos.getX(), br.getY())));
        } else if (pos.getX() + r > br.getX()) {
            return Optional
                    .of(new BoundaryCollision(BoundaryCollision.CollisionEdge.RIGHT, new P2d(br.getX(), pos.getY())));
        } else if (pos.getX() - r < ul.getX()) {
            return Optional
                    .of(new BoundaryCollision(BoundaryCollision.CollisionEdge.LEFT, new P2d(ul.getX(), pos.getY())));
        } else {
            return Optional.empty();
        }
    }

    /**
     * This method starts playing the background music from the beginning
     * and continuously loops it.
     */
    public void playBackgroundMusic() {
        soundPlayer.playFromStart(SoundPlayer.BACKGROUND_MUSIC);
        soundPlayer.loop(SoundPlayer.BACKGROUND_MUSIC);
    }

    /**
     * Plays the collision sound effect.
     */
    public void playCollisionSound() {
        soundPlayer.play(SoundPlayer.BALL_COLLISION);
    }

    /**
     * This method pauses the currently playing background music,
     * allowing to pause the game's audio.
     */
    public void pauseBackgroundSound() {
        soundPlayer.pause(SoundPlayer.BACKGROUND_MUSIC);
    }

    /**
     * Unpauses the background music.
     * This method resumes playing the background music after it has been paused.
     */
    public void unpauseBackgroundSound() {
        soundPlayer.play(SoundPlayer.BACKGROUND_MUSIC);
    }

    /**
     * This method stops all sounds and music currently playing in the game,
     * bringing the audio playback to a complete stop.
     */
    public void stopMusic() {
        soundPlayer.stopAll();
    }

}
