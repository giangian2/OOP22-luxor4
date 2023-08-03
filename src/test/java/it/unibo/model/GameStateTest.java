package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.Levels;
import it.unibo.events.api.WorldEventListener;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.P2d;

public class GameStateTest {

    GameState initialize() {
        WorldEventListener gameEngine = new GameEngineImpl(Levels.L1);
        return new GameState(gameEngine, () -> {
            final int height = 600;
            final int width = 800;
            final int nballs = 10;
            final int steps = 1;
            final String xmlpath = "levels" + System.getProperty("file.separator") + "1"
                    + System.getProperty("file.separator") + "Path.xml";
            final int cannonStartXPos = 470;
            final int cannonStartYPos = 470;

            var w = new World(new RectBoundingBox(new P2d(0, height), new P2d(width, 0)), nballs, steps,
                    xmlpath, null,
                    GameObjectsFactory.getInstance()
                            .createCannon(new P2d(cannonStartXPos, cannonStartYPos)));

            return w;
        });
    }

    @Test
    public void testInitializationWithLevels() {

        assertDoesNotThrow(() -> {
            this.initialize();
        });

    }

    @Test
    public void testScoreHandling() {
        var gameState = this.initialize();

        int initialScore = gameState.getScore();
        gameState.incScore();

        assertTrue(gameState.getScore() == initialScore + 1);

        gameState.decScore();
        assertTrue(gameState.getScore() == initialScore);

    }

    @Test
    public void testGameOver() {
        var gameState = this.initialize();
        while (!gameState.isGameOver()) {
            gameState.getWorld().shiftBalls();
        }

        assertTrue(gameState.isGameOver());
    }

    @Test
    public void testWin() {
        var gameState = this.initialize();
        gameState.getWorld().getQueue().removeAll(gameState.getWorld().getQueue());
        assertTrue(gameState.isWin());
    }

}
