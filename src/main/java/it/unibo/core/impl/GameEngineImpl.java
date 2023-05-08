package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;

public class GameEngineImpl implements GameEngine {

    @Override
    public void mainLoop() {
        System.out.println("Game Loop");
    }

    @Override
    public void initGame() {
        System.out.println("Game Init");
    }

}
