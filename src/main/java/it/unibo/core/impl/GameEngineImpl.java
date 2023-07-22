package it.unibo.core.impl;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import it.unibo.core.api.GameEngine;
import it.unibo.enums.Levels;
import it.unibo.events.api.*;
import it.unibo.events.impl.HitBallEvent;
import it.unibo.events.impl.HitBorderEvent;
import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.graphics.impl.MenuGame;
import it.unibo.graphics.impl.SceneImpl;
import it.unibo.input.KeyboardInputController;
import it.unibo.model.Ball;
import it.unibo.model.GameState;
import it.unibo.utils.Path;
import it.unibo.utils.Path.PathBuilder;

public class GameEngineImpl implements GameEngine, WorldEventListener {

    private static int period = 20;
    private GameState gameState;
    private LinkedList<WorldEvent> eventQueue;
    private Scene view;
    private KeyboardInputController controller;
    private Levels currentLevel;
    JFrame mainFrame;

    public GameEngineImpl(Levels currentLevel, JFrame mainFrame) {
        this.gameState = new GameState(this);
        this.eventQueue = new LinkedList<WorldEvent>();
        controller = new KeyboardInputController();
        this.mainFrame = mainFrame;
        this.view = new SceneImpl(this.gameState, this.controller, this.mainFrame);
        this.currentLevel = currentLevel;

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
        // System.out.println("Game Init");
        gameState.getWorld().playBackgroundMusic();
        mainLoop();
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
            } else if (event instanceof HitBallEvent) {

                var ev = (HitBallEvent) event;
                System.out.println("collision detected : " + ev.getQueueBall().getCurrentPos().toString());
                gameState.getWorld().getCannon().removeFiredBall((Ball) ev.getCannnonBall());
                gameState.getWorld().insertCollisionBall((Ball) ev.getCannnonBall(), (Ball) ev.getQueueBall());

            } else if (event instanceof HitBorderEvent) {
                System.out.println("Border collision");
                var ev = (HitBorderEvent) event;
                gameState.getWorld().getCannon().removeFiredBall((Ball) ev.getCollisionObj());
            }
        });
        eventQueue.clear();
    }

}
