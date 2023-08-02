package it.unibo.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.graphics.impl.MenuGame;

public class GameRenderingTest {

    private MenuGame menuGame;

    @BeforeEach
    public void setup() {
        menuGame = new MenuGame();
    }

    @Test
    public void testMainMenuRendering() {
        // Verifica che la chiamata a showMainMenu() non lanci eccezioni
        assertDoesNotThrow(() -> menuGame.showMainMenu());

        // Verifica che sia presente un pannello principale nel menu
        assertNotNull(menuGame.getContentPane());
    }

    @Test
    public void testHelpMenuRendering() {
        // Verifica che la chiamata a showHelpMenu() non lanci eccezioni
        assertDoesNotThrow(() -> menuGame.showHelpMenu());

        // Simula l'azione di clic sul pulsante di aiuto per visualizzare il menu di aiuto
        menuGame.showHelpMenu();

        // Verifica che sia presente un pannello di aiuto nel menu
        assertNotNull(menuGame.getContentPane());

        // Verifica che ci sia un'area di testo con il testo di aiuto
        String helpText = menuGame.getHelpText();
        assertNotNull(helpText);
        assertTrue(helpText.trim().length() > 0);
    }
}
