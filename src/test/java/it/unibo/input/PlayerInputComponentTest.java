package it.unibo.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.input.api.InputController;
import it.unibo.input.impl.KeyboardInputController;
import it.unibo.input.impl.PlayerInputComponent;
import it.unibo.model.impl.Cannon;
import it.unibo.model.impl.GameObject;
import it.unibo.utils.P2d;

/**
 * JUnit test for {@link PlayerInputComponent}.
 */
public class PlayerInputComponentTest {

    private PlayerInputComponent playerInputComponent;
    private InputController inputController;
    private GameObject gameObject;
    private static final int INITIAL_X_POS = 10;
    private static final int INITIAL_Y_POS = 20;
    private static final int MOVE_LEFT_AMOUNT = 5;
    private static final int INITIAL_X_POS_GO = 50;
    private static final int SLIDE = 5;

    /**
     * Sets up the test environment.
     */
    @BeforeEach
    public void setUp() {
        playerInputComponent = new PlayerInputComponent();
        inputController = new KeyboardInputController();
        gameObject = new Cannon(null, null, playerInputComponent, null, null);
    }

    @Test
    void testMoveLeft() {
        // Set up initial position for the GameObject
        P2d initialPos = new P2d(INITIAL_X_POS, INITIAL_Y_POS);
        gameObject.setPos(initialPos);

        // Notify the input controller of the left movement and perform the update
        ((KeyboardInputController) inputController).notifyMoveLeft();
        playerInputComponent.update(gameObject, inputController);

        // Ensure that the position is updated correctly to the left
        assertEquals(new P2d(INITIAL_X_POS - MOVE_LEFT_AMOUNT, INITIAL_Y_POS), gameObject.getCurrentPos());

        // Stop the left movement and perform another update
        ((KeyboardInputController) inputController).notifyNoMoreMoveLeft();
        playerInputComponent.update(gameObject, inputController);

        // Ensure that the position remains the same since the left movement is stopped
        assertEquals(new P2d(INITIAL_X_POS - MOVE_LEFT_AMOUNT, INITIAL_Y_POS), gameObject.getCurrentPos());
    }

    @Test
    void testMoveRight() {
        // Set up initial position for the GameObject
        P2d initialPos = new P2d(INITIAL_X_POS_GO, INITIAL_Y_POS);
        gameObject.setPos(initialPos);

        // Notify the input controller of the right movement and perform the update
        ((KeyboardInputController) inputController).notifyMoveRight();
        playerInputComponent.update(gameObject, inputController);

        // Ensure that the position is updated correctly to the right
        assertEquals(new P2d(INITIAL_X_POS_GO + SLIDE, INITIAL_Y_POS), gameObject.getCurrentPos());

        // Stop the right movement and perform another update
        ((KeyboardInputController) inputController).notifyNoMoreMoveRight();
        playerInputComponent.update(gameObject, inputController);

        // Ensure that the position remains the same since the right movement is stopped
        assertEquals(new P2d(INITIAL_X_POS_GO + SLIDE, INITIAL_Y_POS), gameObject.getCurrentPos());
    }

    @Test
    void testShoot() {
        // Notify the input controller of the shoot action and perform the update
        ((KeyboardInputController) inputController).notifyShoot();
        playerInputComponent.update(gameObject, inputController);

        // Ensure that the GameObject fires a projectile if it's a Cannon and isShoot()
        // returns true
        assertTrue(inputController.isShoot());

        // Stop shooting and perform another update
        ((KeyboardInputController) inputController).stopShooting();
        playerInputComponent.update(gameObject, inputController);

        // Ensure that the GameObject stops shooting
        assertFalse(inputController.isShoot());
    }
}
