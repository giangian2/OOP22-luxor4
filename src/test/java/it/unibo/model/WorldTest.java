package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.model.collisions.impl.RectBoundingBox;
import it.unibo.model.impl.Ball;
import it.unibo.model.impl.WorldImpl;
import it.unibo.model.api.World;
import it.unibo.utils.P2d;

/**
 * 
 * Class to test the correct functioning of the Wolrd class and all its main
 * features.
 */
class WorldTest {
    static final int STEPS = 1;

    /**
     * Initializes an instance of world based on level 1 parameters.
     * 
     * @return World
     */
    World initialize() {
        // CHECKSTYLE: MagicNumber OFF
        /*
         * it would be redundant and useless use constants
         * to indicates those arbitraty "magic numbers".
         */
        final int height = 600;
        final int width = 800;
        final int nballs = 10;

        final String xmlpath = "levels/1/Path.xml";
        final int cannonStartXPos = 470;
        final int cannonStartYPos = 470;
        return new WorldImpl(new RectBoundingBox(new P2d(0, height), new P2d(width, 0)), nballs, STEPS,
                xmlpath, (ev) -> {
                }, GameObjectsFactory.getInstance()
                        .createCannon(new P2d(cannonStartXPos, cannonStartYPos)));
    }

    /**
     * 
     * Verify that there are no errors in instantiating the world object.
     */
    @Test
    void testWolrdInitialization() {

        assertDoesNotThrow(() -> {
            this.initialize();
        });

    }

    /**
     * 
     * Verify that the wolrd state is updated correctly at each main loop cycle, in
     * particular it verifies that all the balls are shifted at each update.
     */
    @Test
    void testWorldUpdateState() {
        final var w = this.initialize(); // wolrd object instantiation
        final var initialQueue = w.getQueue();
        final var finalQueue = new ArrayList<Ball>();
        initialQueue.forEach(ball -> finalQueue.add(
                GameObjectsFactory.getInstance().createBall(ball.getCurrentPos(), ball.getCurrentVel(),
                        ball.getColor())));

        w.updateState(0); // world update

        /**
         * 
         * check if after the update the shift of the balls of the queue has happened
         * correctly.
         */
        for (int i = 0; i < initialQueue.size(); i++) {
            assertTrue(
                    Math.abs(finalQueue.get(i).getCurrentPos().getX()
                            - initialQueue.get(i).getCurrentPos().getX()) == STEPS
                            || Math.abs(
                                    finalQueue.get(i).getCurrentPos().getY()
                                            - initialQueue.get(i).getCurrentPos().getY()) == STEPS);
        }
    }

    /**
     * 
     * Tests the insertion of a ball into the queue after a collision.
     */
    @Test
    void testInsertCollisionBall() {
        final var w = this.initialize();

        final var firstBall = w.getQueue().get(0);
        final int startSize = w.getQueue().size();

        final var cannonBall = GameObjectsFactory.getInstance()
                .createCannonBall(new P2d(firstBall.getCurrentPos().getX() - 5, firstBall.getCurrentPos().getY() + 5),
                        null,
                        null);

        w.insertCollisionBall(cannonBall, firstBall);

        assertEquals(w.getQueue().size(), startSize + 1);
    }

    /**
     * 
     * Test the correct error detection for inserting a collision when the ball that
     * generated the collision is not of type CANNON_BALL.
     */
    @Test
    void testInsertCollisionBallWithError() {
        final var w = this.initialize();

        final var firstBall = w.getQueue().get(0);

        final var cannonBall = GameObjectsFactory.getInstance()
                .createBall(new P2d(firstBall.getCurrentPos().getX() - 5, firstBall.getCurrentPos().getY() + 5), null,
                        null);

        assertThrows(IllegalStateException.class, () -> {
            w.insertCollisionBall(cannonBall, firstBall);
        });

    }
}
