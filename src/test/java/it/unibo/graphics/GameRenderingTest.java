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
public class GameRenderingTest {

    private MenuGame menuGame;

    /**
     * Set up the test environment before each test method.
     * Create a new instance of MenuGame for each test.
     */
    @BeforeEach
    public void setUp() {
        menuGame = new MenuGame();
    }

    /**
     * Test the rendering of the main menu in MenuGame.
     * Verifies that calling showMainMenu() does not throw any exceptions and that
     * the main menu panel is present.
     */
    @Test
    public void testMainMenuRendering() {
        // Verifica che la chiamata a showMainMenu() non lanci eccezioni
        assertDoesNotThrow(() -> menuGame.showMainMenu());

        // Verifica che sia presente un pannello principale nel menu
        assertNotNull(menuGame.getContentPane());
    }

    /**
     * Test the rendering of the help menu in MenuGame.
     * Verifies that calling showHelpMenu() does not throw any exceptions and that
     * the help menu panel is present.
     * Also simulates clicking on the help button to show the help menu and checks
     * that the help text is present.
     */
    @Test
    public void testHelpMenuRendering() {
        // Verifica che la chiamata a showHelpMenu() non lanci eccezioni
        assertDoesNotThrow(() -> menuGame.showHelpMenu());

        // Simula l'azione di clic sul pulsante di aiuto per visualizzare il menu di
        // aiuto
        menuGame.showHelpMenu();

        // Verifica che sia presente un pannello di aiuto nel menu
        assertNotNull(menuGame.getContentPane());

        // Verifica che ci sia un'area di testo con il testo di aiuto
        final String helpText = menuGame.getHelpText();
        assertNotNull(helpText);
        assertTrue(helpText.trim().length() > 0);
    }
}
