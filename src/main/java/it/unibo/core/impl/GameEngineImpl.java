package it.unibo.core.impl;

import java.util.LinkedList;

import it.unibo.core.api.GameEngine;
import it.unibo.events.api.*;
import it.unibo.graphics.api.Scene;
import it.unibo.graphics.impl.SceneImpl;

public class GameEngineImpl implements GameEngine, WorldEventListener {

    private static int period = 20;
    private LinkedList<WorldEvent> eventQueue;
    private Scene view;

    public GameEngineImpl() {
        this.eventQueue = new LinkedList<WorldEvent>();
        this.view = new SceneImpl();
    }

    @Override
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (true) {
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsed = currentCycleStartTime - previousCycleStartTime;
            // processInput();
            // updateGame(elapsed);
            render();
            // waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
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

    protected void render() {
        view.render();
    }

}
