package it.unibo.graphics.api;

/**
 * An interface representing a scene in a game, responsible for rendering the game graphics and handling transitions between game states.
 */
public interface Scene {

    /**
     * Renders the game graphics on the screen.
     * This method should be called periodically to update the visual representation of the game elements.
     */
    void render();

    /**
     * Renders the game over screen and transitions to the game over menu.
     */
    void renderGameOver();

    /**
     * Renders the game menu.
     * This method is called to display the game menu, providing options for starting a new game, adjusting settings, and exiting the game.
     * Note: The implementation of this method depends on the specific game's menu design and options.
     */
    void renderMenu();

    /**
     * Renders the win screen and transitions to the next game level or the game's victory menu.
     * This method is responsible for displaying the win screen, which congratulates the player for completing the current game level,
     * and handling any necessary transitions to the next level or the game's victory menu.
     */
    void renderWin();
}
