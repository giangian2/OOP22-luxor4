package it.unibo.core.impl;

import java.util.LinkedList;

import it.unibo.core.api.GameEngine;
import it.unibo.events.api.*;

public class GameEngineImpl implements GameEngine, WorldEventListener {

    private static int period = 20;
    private LinkedList<WorldEvent> eventQueue;

    public GameEngineImpl(String levelName) {
        eventQueue = new LinkedList<WorldEvent>();
    }

    @Override
    public void mainLoop() {
        System.out.println("Game Loop");
    }

    @Override
    public void initGame() {
        System.out.println("Game Init");
    }

    @Override
    public void notifyEvent(WorldEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyEvent'");
    }

}
