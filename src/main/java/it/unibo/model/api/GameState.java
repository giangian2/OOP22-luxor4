package it.unibo.model.api;

import it.unibo.input.api.InputController;
import it.unibo.model.impl.World;

/**
 * Represents the state of the game, including the score, world, pause state,
 * and level.
 */
public interface GameState {
    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    boolean isGameOver();

    /**
     * Checks if the player has won the game.
     *
     * @return true if the player has won, false otherwise.
     */
    boolean isWin();

    /**
     * Updates the game state based on the elapsed time.
     *
     * @param dt The elapsed time since the last update.
     */
    void update(long dt);

    /**
     * Increases the current score by 1.
     */
    void incScore();

    /**
     * Decreases the current score by 1.
     */
    void decScore();

    /**
     * Retrieves the current score of the game.
     *
     * @return The current score.
     */
    int getScore();

    /**
     * Processes the user input using the provided KeyboardInputController.
     * This method updates the state of the game world based on the user's input.
     *
     * @param controller The KeyboardInputController to handle user input.
     */
    void processInput(InputController controller);

    /**
     * Retrieves the World object representing the game world.
     *
     * @return The World.
     */
    World getWorld();

    /**
     * Toggles the pause state of the game.
     * If the game is paused, it will be resumed, and vice versa.
     */
    void changePauseState();
}
