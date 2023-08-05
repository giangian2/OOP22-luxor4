package it.unibo.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.enums.Levels;
import it.unibo.events.impl.PauseGameEvent;

/**
 * Test class used to test fundamental aspects of the game engine.
 */
class GameEngineTest {

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
    void testMainLoop() {
        // CHECKSTYLE: MagicNumber OFF
        /*
         * it would be redundant and useless use constants
         * to indicates those arbitraty "magic numbers".
         */
        assertDoesNotThrow(() -> {
            // initialize a new instance of GameEngineImpl
            final var engine = this.initialize();
            final Thread mainLoopThread = new Thread(() -> {
                engine.initGame();
            }, "Game thread");

            mainLoopThread.start();

            mainLoopThread.interrupt(); // kills the main loop thread
        });

    }

    /**
     * Verify the correct functioning of the method used to process the events that
     * are pushed into the appropriate queue.
     */
    @Test
    void testEventListener() {
        // CHECKSTYLE: MagicNumber OFF
        /*
         * it would be redundant and useless use constants
         * to indicates those arbitraty "magic numbers".
         */
        // initialize a new instance of GameEngineImpl
        final var engine = this.initialize();

        assertDoesNotThrow(() -> {
            final Thread eventThread = new Thread(() -> {
                engine.notifyEvent(new PauseGameEvent());
            }, "Events thread");

            final Thread mainLoopThread = new Thread(() -> {
                engine.initGame();
            }, "Game thread");

            mainLoopThread.start(); // start the mian loop thread
            eventThread.start(); // start the events thread

            mainLoopThread.interrupt(); // kills the main loop thread
        });

    }
}
