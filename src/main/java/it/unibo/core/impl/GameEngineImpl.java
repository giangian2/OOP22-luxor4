package it.unibo.core.impl;

import java.util.LinkedList;

import it.unibo.core.api.GameEngine;
import it.unibo.enums.Levels;
import it.unibo.events.api.*;
import it.unibo.events.impl.HitBallEvent;
import it.unibo.events.impl.HitBorderEvent;
import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.graphics.impl.GameOverPanel;
import it.unibo.graphics.impl.MenuGame;
import it.unibo.graphics.impl.SceneImpl;
import it.unibo.input.KeyboardInputController;
import it.unibo.model.Ball;
import it.unibo.model.GameState;
import it.unibo.model.World;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.P2d;

public class GameEngineImpl implements GameEngine, WorldEventListener {

    private static int period = 30;
    private GameState gameState;
    private LinkedList<WorldEvent> eventQueue;
    private Scene view;
    private KeyboardInputController controller;
    private Levels currentLevel;

    public GameEngineImpl(Levels currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (!gameState.isGameOver()) {
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsed = currentCycleStartTime - previousCycleStartTime;
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
        renderGameOver();

    }

    private void renderGameOver() {

        view.renderGameOver();
    }

    @Override
    public void initGame() {

        this.eventQueue = new LinkedList<WorldEvent>();
        controller = new KeyboardInputController();

        switch (this.currentLevel) {
            case L1:
                this.gameState = new GameState(this, () -> {
                    var w = new World(new RectBoundingBox(new P2d(0, 600), new P2d(800, 0)), 10, 2,
                            "levels/1/Path.xml");
                    w.setCannon(GameObjectsFactory.getInstance().createCannon(new P2d(470, 470), "images/cannone.png"));
                    return w;
                });
                this.view = new SceneImpl(this.gameState, this.controller, "images/background.jpg",
                        "images/cannone.png");
                break;

            case L2:
                this.gameState = new GameState(this, () -> {
                    var w = new World(new RectBoundingBox(new P2d(0, 600), new P2d(800, 0)), 5, 8,
                            "levels/2/Path.xml");
                    w.setCannon(GameObjectsFactory.getInstance().createCannon(new P2d(470, 470), "images/cannone.png"));
                    return w;
                });
                this.view = new SceneImpl(this.gameState, this.controller, "images/background2.jpg",
                        "images/cannone.png");
                break;
        }

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
                gameState.getWorld().getCannon().removeFiredBall((Ball) ev.getCannnonBall());
                gameState.getWorld().insertCollisionBall((Ball) ev.getCannnonBall(), (Ball) ev.getQueueBall());

            } else if (event instanceof HitBorderEvent) {
                var ev = (HitBorderEvent) event;
                gameState.getWorld().getCannon().removeFiredBall((Ball) ev.getCollisionObj());
            }
        });
        eventQueue.clear();
    }

}
