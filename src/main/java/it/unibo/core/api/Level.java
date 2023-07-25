package it.unibo.core.api;

import it.unibo.enums.Levels;
import it.unibo.model.World;
import it.unibo.utils.V2d;

@FunctionalInterface
public interface Level {
    public World loadLevel();
}