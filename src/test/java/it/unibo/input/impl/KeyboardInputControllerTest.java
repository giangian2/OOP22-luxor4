package it.unibo.input.impl; 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * JUnit test for {@link KeyboardInputController}.
 */
public class KeyboardInputControllerTest {


    private KeyboardInputController inputController;

    /**
     * Sets up the test fixture.
     * (Called before every test case method.)
     */
    @BeforeEach
    void setUp() {
        inputController = new KeyboardInputController();
    }

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

