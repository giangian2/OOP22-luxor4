package it.unibo.core.api;

import it.unibo.model.GameState;
import it.unibo.model.World;
import it.unibo.utils.V2d;

@FunctionalInterface
public interface Level {
    public void loadLevel(GameState gameState, int nBalls, V2d queuSpeed, String pathXmlSrc, String backroundSrc);
}