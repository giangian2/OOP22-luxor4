package it.unibo.model.api;

import java.util.List;
import java.util.Optional;

import it.unibo.enums.Direction;
import it.unibo.events.api.WorldEvent;
import it.unibo.events.api.WorldEventListener;
import it.unibo.model.collisions.impl.CircleBoundingBox;
import it.unibo.model.impl.Ball;
import it.unibo.model.impl.Cannon;
import it.unibo.model.impl.GameObject;
import it.unibo.physics.impl.BoundaryCollision;
import it.unibo.utils.P2d;

/**
 * The World interface models the game space in which all the GameObjects
 * will be present and through which you can interact with them.
 */
public interface World {

    /**
     * Notify an events to the WorldEventsListener.
     * 
     * @param ev world event to be notified
     */
    void notifyWorldEvent(WorldEvent ev);

    /**
     * Sets the world event listener.
     * 
     * @param l Event listener
     */
    void setEventListener(WorldEventListener l);

    /**
     * Gets the current cannon object.
     * 
     * @return The cannon object
     */
    Cannon getCannon();

    /**
     * Passing a ball, it allows you to obtain its position at the next frame via
     * the queue manager, if you reach the end of the path the direction returned
     * will be "NONE" and consequently the Game Over will have to be rendered.
     * 
     * @param queueBall Ball
     * @return Direction
     */
    Direction moveSingleBall(Ball queueBall);

    /**
     * Shifts all the balls by as many steps as specified in the constructor.
     */
    void shiftBalls();

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
    void insertCollisionBall(Ball cannonBall, Ball queueBall);

    /**
     * Returns the list of all triplets (or more) of neighboring balls having the
     * same color.
     * 
     * @return Lis of balls to remove from queue
     */
    Optional<List<Ball>> getCloseByThree();

    /**
     * Returns all the game objects present in the World so that they can be used
     * for graphic rendering within the Scene.
     * 
     * @return List of all wolrd's game objects
     */
    List<GameObject> getSceneEntities();

    /**
     * Return the balls in the queue via the QueueManager.
     * 
     * @return List of all queue balls
     */
    List<Ball> getQueue();

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
    void updateState(long dt);

    /**
     * Checks if there are collisions between an element passed as input and the
     * bounding box of the World.
     * 
     * @param pos Ball's position
     * @param box Ball's bounding box
     * @return BoundaryCollision
     */
    Optional<BoundaryCollision> checkCollisionWithBoundaries(P2d pos, CircleBoundingBox box);

    /**
     * Checks if there is a collision between a ball passed in input and the
     * balls in the queue and and returns the ball from
     * the tail with which the collision occurred.
     * 
     * @param pos Ball's position
     * @param box Ball's bounding box
     * @return GameObject -> the queue ball with the given bbox collided with
     */
    Optional<GameObject> checkCollisionWithBalls(P2d pos, CircleBoundingBox box);

    /**
     * This method starts playing the background music from the beginning
     * and continuously loops it.
     */
    void playBackgroundMusic();

    /**
     * Plays the collision sound effect.
     */
    void playCollisionSound();

    /**
     * This method pauses the currently playing background music,
     * allowing to pause the game's audio.
     */
    void pauseBackgroundSound();

    /**
     * Unpauses the background music.
     * This method resumes playing the background music after it has been paused.
     */
    void unpauseBackgroundSound();

    /**
     * This method stops all sounds and music currently playing in the game,
     * bringing the audio playback to a complete stop.
     */
    void stopMusic();
}
