package it.unibo;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.core.api.GameEngine;

public class App {
    public static void main(String[] args) {
        GameEngine engine = new GameEngineImpl();
        engine.initGame();
        engine.mainLoop();
    }
}
