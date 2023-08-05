package it.unibo.core.api;

import it.unibo.model.impl.World;

/**
 * The Level interface represents a functional contract for loading a game
 * level.
 * It provides a single method to load a World object that represents the game
 * level.
 */
@FunctionalInterface
public interface Level {

    /**
     * Loads the game level and returns the corresponding World object.
     *
     * @return The World object representing the loaded game level.
     */
    World loadLevel();
}
