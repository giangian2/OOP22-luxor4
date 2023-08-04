package it.unibo.graphics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.graphics.impl.MenuGame;

/**
 * The GameRenderingTest class contains test methods to verify the rendering of
 * the MenuGame.
 * It uses JUnit 5 for testing.
 */
class GameRenderingTest {

    private MenuGame menuGame;

    @BeforeEach
    public void setup() {
        menuGame = new MenuGame();
    }

    @Test
    void testMainMenuRendering() {
        assertDoesNotThrow(() -> menuGame.showMainMenu());
        assertNotNull(menuGame.getContentPane());
    }

    @Test
    void testHelpMenuRendering() {
        assertDoesNotThrow(() -> menuGame.showHelpMenu());
        menuGame.showHelpMenu();
        assertNotNull(menuGame.getContentPane());
        final String helpText = menuGame.getHelpText();
        assertNotNull(helpText);
        assertTrue(helpText.trim().length() > 0);
    }
}
