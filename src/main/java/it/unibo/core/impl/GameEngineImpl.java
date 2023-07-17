package it.unibo.core.impl;

import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import it.unibo.core.api.GameEngine;
import it.unibo.events.api.*;
import it.unibo.events.impl.HitBallEvent;

import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.graphics.impl.SceneImpl;
import it.unibo.input.KeyboardInputController;
import it.unibo.model.GameState;
import it.unibo.utils.Path;
import it.unibo.utils.Path.PathBuilder;

public class GameEngineImpl implements GameEngine, WorldEventListener {

    private static int period = 40;
    private GameState gameState;
    private LinkedList<WorldEvent> eventQueue;
    private Scene view;
    private KeyboardInputController controller;

    public GameEngineImpl() {
        this.gameState = new GameState(this);
        this.eventQueue = new LinkedList<WorldEvent>();
        controller = new KeyboardInputController();
        this.view = new SceneImpl(this.gameState, this.controller);
    }

    @Override
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (true) {
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsed = currentCycleStartTime - previousCycleStartTime;
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
    }

    @Override
    public void initGame() {
        System.out.println("Game Init");
    }

    public void updateGame(long elapsed) {
        gameState.update(elapsed);
        checkEvents();
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

    protected void processInput() {
        gameState.processInput(controller);
    }

    @Override
    public void notifyEvent(WorldEvent e) {
        eventQueue.add(e);
    }

    private void checkEvents() {
        eventQueue.stream().forEach(event -> {
            if (event instanceof PauseGameEvent) {
                gameState.changePauseState();
            }
        });
        eventQueue.clear();
    }

}
