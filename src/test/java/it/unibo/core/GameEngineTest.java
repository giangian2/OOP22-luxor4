package it.unibo.core;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import it.unibo.core.impl.GameEngineImpl;
import it.unibo.enums.Levels;
import it.unibo.events.impl.PauseGameEvent;

public class GameEngineTest {

    private GameEngineImpl initialize() {
        return new GameEngineImpl(Levels.L1);
    }

    @Test
    public void testMainLoop() {
        assertDoesNotThrow(() -> {
            var engine = this.initialize();
            Thread t2 = new Thread(() -> {
                engine.initGame();

            }, "Game thread thread");

            t2.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t2.interrupt();
        });
    }

    @Test
    public void testEventListener() {
        var engine = this.initialize();
        assertDoesNotThrow(() -> {
            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                engine.notifyEvent(new PauseGameEvent());
            }, "Events thread");

            Thread t2 = new Thread(() -> {
                engine.initGame();

            }, "Game thread thread");

            t2.start();
            t.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t2.interrupt();
        });

    }
}
