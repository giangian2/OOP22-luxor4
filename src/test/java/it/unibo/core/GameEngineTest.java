package it.unibo.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.enums.Levels;
import it.unibo.events.api.WorldEvent;

/**
 * Test class used to test fundamental aspects of the game engine.
 */
public class GameEngineTest {

    /**
     * Initialize a new instace of GameEngineImpl.
     * 
     * @return GameEngineImpl
     */
    private GameEngineImpl initialize() {
        return new GameEngineImpl(Levels.L1);
    }

    /**
     * Verify the correct functioning of the main loop, if there are no exceptions
     * the main loop thread is killed and the test is successful.
     */
    @Test
    public void testMainLoop() {
        // CHECKSTYLE: MagicNumber OFF
        /*
         * it would be redundant and useless use constants
         * to indicates those arbitraty "magic numbers".
         */
        assertDoesNotThrow(() -> {
            // initialize a new instance of GameEngineImpl
            var engine = this.initialize();
            Thread mainLoopThread = new Thread(() -> {
                engine.initGame();

            }, "Game thread thread");

            mainLoopThread.start(); // Start the mainloop thread

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainLoopThread.interrupt(); // Kill the mainloop thread
        });
    }

    /**
     * Verify the correct functioning of the method used to process the events that
     * are pushed into the appropriate queue.
     */
    @Test
    public void testEventListener() {
        // CHECKSTYLE: MagicNumber OFF
        /*
         * it would be redundant and useless use constants
         * to indicates those arbitraty "magic numbers".
         */
        // initialize a new instance of GameEngineImpl
        var engine = this.initialize();

        assertDoesNotThrow(() -> {
            Thread eventThread = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Notify a new PauseEvent to the GameEngine
                engine.notifyEvent(new WorldEvent() {
                });
            }, "Events thread");

            Thread mainLoopThread = new Thread(() -> {
                engine.initGame();
            }, "Game thread");

            mainLoopThread.start(); // start the mian loop thread
            eventThread.start(); // start the events thread

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            mainLoopThread.interrupt(); // kills the main loop thread
        });

    }
}
