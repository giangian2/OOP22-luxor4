package it.unibo.input.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * JUnit test for {@link KeyboardInputController}.
 */
class KeyboardInputControllerTest {

    private final KeyboardInputController inputController = new KeyboardInputController();

    /**
     * Tears down the test fixture.
     * (Called after every test case method.)
     */
    @Test
    void testMoveLeft() {
        inputController.notifyMoveLeft();
        assertTrue(inputController.isMoveLeft(), "Move Left should be active");
        inputController.notifyNoMoreMoveLeft();
        assertFalse(inputController.isMoveLeft(), "Move Left should not be active");
    }

    @Test
    void testMoveRight() {
        inputController.notifyMoveRight();
        assertTrue(inputController.isMoveRight(), "Move Right should be active");
        inputController.notifyNoMoreMoveRight();
        assertFalse(inputController.isMoveRight(), "Move Right should not be active");
    }

    @Test
    void testShoot() {
        inputController.notifyShoot();
        assertTrue(inputController.isShoot(), "Shoot should be active");
        inputController.stopShooting();
        assertFalse(inputController.isShoot(), "Shoot should not be active");
    }
}
