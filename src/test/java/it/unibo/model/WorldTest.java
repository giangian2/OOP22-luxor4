package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.P2d;

public class WorldTest {
    static final int steps = 1;

    private World initialize() {
        final int height = 600;
        final int width = 800;
        final int nballs = 10;

        final String xmlpath = "levels" + System.getProperty("file.separator") + "1"
                + System.getProperty("file.separator") + "Path.xml";
        final int cannonStartXPos = 470;
        final int cannonStartYPos = 470;
        return new World(new RectBoundingBox(new P2d(0, height), new P2d(width, 0)), nballs, steps,
                xmlpath, (ev) -> {
                    System.out.println(ev.toString());
                }, GameObjectsFactory.getInstance()
                        .createCannon(new P2d(cannonStartXPos, cannonStartYPos)));
    }

    @Test
    public void testWolrdInitialization() {

        assertDoesNotThrow(() -> {
            this.initialize();
        });

    }

    @Test
    public void testWorldUpdateState() {
        var w = this.initialize();
        final var initialQueue = w.getQueue();
        final var finalQueue = new ArrayList<Ball>();
        initialQueue.forEach(ball -> finalQueue.add(
                GameObjectsFactory.getInstance().createBall(ball.getCurrentPos(), ball.getVel(), ball.getColor())));

        w.updateState(0);

        for (int i = 0; i < initialQueue.size(); i++) {
            assertTrue(
                    Math.abs(finalQueue.get(i).getCurrentPos().getX()
                            - initialQueue.get(i).getCurrentPos().getX()) == steps
                            || Math.abs(
                                    finalQueue.get(i).getCurrentPos().getY()
                                            - initialQueue.get(i).getCurrentPos().getY()) == steps);
        }
    }

    @Test
    public void testInsertCollisionBall() {
        final var w = this.initialize();

        var firstBall = w.getQueue().get(0);
        int startSize = w.getQueue().size();

        var cannonBall = GameObjectsFactory.getInstance()
                .createCannonBall(new P2d(firstBall.getCurrentPos().getX() - 5, firstBall.getCurrentPos().getY() + 5),
                        null,
                        null);

        w.insertCollisionBall(cannonBall, firstBall);

        assertTrue(w.getQueue().size() == startSize + 1);
    }

    @Test
    public void testInsertCollisionBallWithError() {
        final var w = this.initialize();

        var firstBall = w.getQueue().get(0);

        var cannonBall = GameObjectsFactory.getInstance()
                .createBall(new P2d(firstBall.getCurrentPos().getX() - 5, firstBall.getCurrentPos().getY() + 5), null,
                        null);

        assertThrows(IllegalStateException.class, () -> {
            w.insertCollisionBall(cannonBall, firstBall);
        });

    }
}
