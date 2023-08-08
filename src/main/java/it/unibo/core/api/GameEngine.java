package it.unibo.core.api;

import it.unibo.events.api.WorldEventListener;
import it.unibo.graphics.api.Scene;
import it.unibo.model.api.GameState;

/**
 * This interface shapes the game engine.
 */
public interface GameEngine extends WorldEventListener {
    /**
     * 
     * Method used to start the main game loop.
     */
    void mainLoop();

    /**
     * Method used to initialize all aspects of the game (World + Graphics).
     */
    void initGame();

    /**
     * Method used to set the Game STate that the Engine will use to perform the
     * update operation at every cycle.
     * 
     * @param gameState Model
     */
    void setGameState(GameState gameState);

    /**
     * Method used to set the View that the Engine will use to perform the
     * rendering operation at every cycle.
     * 
     * @param view View
     */
    void setView(Scene view);
}
