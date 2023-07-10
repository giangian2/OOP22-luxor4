package it.unibo.core.impl;

import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import it.unibo.core.api.GameEngine;
import it.unibo.events.api.*;
import it.unibo.graphics.api.Scene;
import it.unibo.graphics.impl.SceneImpl;
import it.unibo.model.GameState;
import it.unibo.utils.Path;
import it.unibo.utils.Path.PathBuilder;

public class GameEngineImpl implements GameEngine, WorldEventListener {

    private static int period = 40;
    private GameState gameState;
    private LinkedList<WorldEvent> eventQueue;
    private Scene view;

    public GameEngineImpl() {
        this.gameState = new GameState(this);
        this.eventQueue = new LinkedList<WorldEvent>();
        this.view = new SceneImpl(this.gameState.getWorld());
    }

    @Override
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (true) {
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsed = currentCycleStartTime - previousCycleStartTime;
            // processInput();
            // updateGame(elapsed);
            this.gameState.getWorld().shiftBalls();
            render();
            waitForNextFrame(currentCycleStartTime);
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

    protected void waitForNextFrame(long cycleStartTime) {
        long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < period) {
            try {
                Thread.sleep(period - dt);
            } catch (Exception ex) {
            }
        }
    }

}
