package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import it.unibo.core.impl.GameEngineImpl;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.Levels;
import it.unibo.events.api.WorldEventListener;
import it.unibo.model.collisions.impl.RectBoundingBox;
import it.unibo.model.impl.GameStateImpl;
import it.unibo.model.impl.World;
import it.unibo.utils.P2d;

/**
 * Class to test the correct functioning of the GameState class.
 */
class GameStateTest {

    /**
     * Initialize a GameState instance based on level 1.
     * 
     * @return GameState
     */
    GameStateImpl initialize() {
        final WorldEventListener gameEngine = new GameEngineImpl(Levels.L1); // Initialize thew world event listener
        return new GameStateImpl(gameEngine, () -> {
            final int height = 600;
            final int width = 800;
            final int nballs = 10;
            final int steps = 1;
            final String xmlpath = "levels" + System.getProperty("file.separator") + "1"
                    + System.getProperty("file.separator") + "Path.xml";
            final int cannonStartXPos = 470;
            final int cannonStartYPos = 470;

            return new World(new RectBoundingBox(new P2d(0, height), new P2d(width, 0)), nballs, steps,
                    xmlpath, null,
                    GameObjectsFactory.getInstance()
                            .createCannon(new P2d(cannonStartXPos, cannonStartYPos)));
        });
    }

    /**
     * Tests the correct creation of the game state object with a given level.
     */
    @Test
    void testInitializationWithLevels() {

        assertDoesNotThrow(() -> {
            this.initialize();
        });
    }

    /**
     * 
     * Tests the correct functioning of the basic scoring operations.
     */
    @Test
    void testScoreHandling() {
        final var gameState = this.initialize();

        final int initialScore = gameState.getScore();
        gameState.incScore();

        assertEquals(gameState.getScore(), initialScore + 1);

        gameState.decScore();
        assertEquals(gameState.getScore(), initialScore);

    }

    /**
     * Tests the correct detection of the game over status.
     */
    @Test
    void testGameOver() {
        final var gameState = this.initialize();
        while (!gameState.isGameOver()) {
            gameState.getWorld().shiftBalls();
        }

        assertTrue(gameState.isGameOver());
    }

    /**
     * Tests the correct detection of the win status.
     */
    @Test
    void testWin() {
        final var gameState = this.initialize();
        gameState.getWorld().getQueue().removeAll(gameState.getWorld().getQueue());
        assertTrue(gameState.isWin());
    }
}
