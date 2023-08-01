package it.unibo.core.impl;

import java.util.LinkedList;

import it.unibo.core.api.GameEngine;
import it.unibo.enums.Levels;
import it.unibo.events.api.*;
import it.unibo.events.impl.HitBallEvent;
import it.unibo.events.impl.HitBorderEvent;
import it.unibo.events.impl.PauseGameEvent;
import it.unibo.graphics.api.Scene;
import it.unibo.graphics.impl.SceneImpl;
import it.unibo.input.impl.KeyboardInputController;
import it.unibo.model.Ball;
import it.unibo.model.GameState;
import it.unibo.model.World;
import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.P2d;

/**
 * 
 * The gameEngineImpl class will implement the GameEngine and WorldEventListener
 * interface in such a way as to be able to directly manage the events related
 * to the World that are triggered during the mainLoop
 */
public class GameEngineImpl implements GameEngine, WorldEventListener {

    private static int period = 30; // Period of rendering
    private GameState gameState;
    private LinkedList<WorldEvent> eventQueue; // EVent queue used to process any event
    private Scene view; // View
    private KeyboardInputController controller; // Controller
    private Levels currentLevel; // Selected Level

    /**
     * Initialize the GameEngineImpl with the given level in order to instatiate the
     * World properly and render the correct view
     * 
     * @param currentLevel
     */
    public GameEngineImpl(Levels currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public void mainLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        // Main loop, process input -> updateGame -> render utile game over or win state
        // is reached
        while (!gameState.isGameOver() && !gameState.isWin()) {
            long currentCycleStartTime = System.currentTimeMillis();
            long elapsed = currentCycleStartTime - previousCycleStartTime;
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }

        // If win state is reached, render the respective view
        if (gameState.isGameOver())
            renderGameOver();
        // If game over state is reached, render the respective view
        if (gameState.isWin())
            renderWin();

    }

    /**
     * Implementing the worldEventListener, this method will add the notified event
     * to the event queue in orther to be processed in the further step
     */
    @Override
    public void notifyEvent(WorldEvent e) {
        eventQueue.add(e);
    }

    /**
     * Initialize the View and the World based on the selected level throught the
     * functional interface "Level"
     */
    @Override
    public void initGame() {

        // Initialize event queue and controller
        this.eventQueue = new LinkedList<WorldEvent>();
        controller = new KeyboardInputController();

        switch (this.currentLevel) {
            case L1:
                /**
                 * In case the selected level is l1,
                 * the game state is instantiated having the GameEngine itself as an event
                 * listener and a lambda function is passed that implements the loadLevel method
                 * of the level interface, in this way there will be a fluid and scalable
                 * development process for the creation of new levels
                 */
                this.gameState = new GameState(this, () -> {
                    var w = new World(new RectBoundingBox(new P2d(0, 600), new P2d(800, 0)), 10, 2,
                            "levels/1/Path.xml");
                    w.setCannon(GameObjectsFactory.getInstance().createCannon(new P2d(470, 470)));
                    return w;
                });
                // Render the view passing the correct background related to the selected level
                this.view = new SceneImpl(this.gameState, this.controller, "images/background.jpg");
                break;

            case L2:
                this.gameState = new GameState(this, () -> {
                    var w = new World(new RectBoundingBox(new P2d(0, 600), new P2d(800, 0)), 5, 8,
                            "levels/2/Path.xml");
                    w.setCannon(GameObjectsFactory.getInstance().createCannon(new P2d(470, 470)));
                    return w;
                });
                this.view = new SceneImpl(this.gameState, this.controller, "images/background2.jpg");
                break;
        }

        // Start background music and main loop
        gameState.getWorld().playBackgroundMusic();
        mainLoop();
    }

    /**
     * 
     */
    private void renderGameOver() {
        view.renderGameOver();
    }

    /**
     * 
     */
    private void renderWin() {
        view.renderWin();
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
