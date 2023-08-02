package it.unibo.core.api;

/**
 * This interface shapes the game engine.
 */
public interface GameEngine {
    /**
     * 
     * Method used to start the main game loop.
     */
    void mainLoop();

    /**
     * Method used to initialize all aspects of the game (World + Graphics).
     */
    void initGame();
}
