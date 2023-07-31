package it.unibo.core.api;

import it.unibo.model.World;

@FunctionalInterface
public interface Level {
    public World loadLevel();
}