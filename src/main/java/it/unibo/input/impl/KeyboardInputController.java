package it.unibo.input.impl;

import it.unibo.input.api.InputController;

/**
 * A concrete implementation of the InputController interface that handles keyboard input for the game.
 */
public class KeyboardInputController implements InputController {

    private boolean isMoveLeft;
    private boolean isMoveRight;
    private boolean isShoot;

    /**
     * Checks if the "Move Left" input action is currently active.
     *
     * @return true if the "Move Left" input action is active, false otherwise.
     */
    @Override
    public boolean isMoveLeft() {
        return isMoveLeft;
    }

    /**
     * Checks if the "Move Right" input action is currently active.
     *
     * @return true if the "Move Right" input action is active, false otherwise.
     */
    @Override
    public boolean isMoveRight() {
        return isMoveRight;
    }

    /**
     * Notifies the input controller that the "Move Left" input action is active.
     */
    public void notifyMoveLeft() {
        isMoveLeft = true;
    }

     /**
     * Notifies the input controller that the "Move Left" input action is no longer active.
     */
    public void notifyNoMoreMoveLeft() {
        isMoveLeft = false;
    }

    /**
     * Notifies the input controller that the "Move Right" input action is active.
     */
    public void notifyMoveRight() {
        isMoveRight = true;
    }

    /**
     * Notifies the input controller that the "Move Right" input action is active.
     */
    public void notifyNoMoreMoveRight() {
        isMoveRight = false;
    }

    /**
     * Checks if the "Shoot" input action is currently active.
     *
     * @return true if the "Shoot" input action is active, false otherwise.
     */
    @Override
    public boolean isShoot() {
        // TODO Auto-generated method stub
        return isShoot;
    }

    /**
     * Notifies the input controller that the "Shoot" input action is active.
     */
    public void notifyShoot() {
        isShoot = true;
    }

    /**
     * Stops the "Shoot" input action.
     * This method is called to indicate that the "Shoot" action should stop, e.g., when the shoot key is released.
     */
    @Override
    public void stopShooting() {
        isShoot = false;
    }

    /**
     * Sets the input component for the input controller.
     * This method is called by the input component to set itself as the input component for the input controller.
     *
     * @param playerInputComponent The input component to be set for the input controller.
     */
    public void setInputComponent(final PlayerInputComponent playerInputComponent) {
    }

    /**
     * Creates a copy of the input controller.
     *
     * @return A copy of the input controller.
     */
    public KeyboardInputController copy() {
        return new KeyboardInputController();
    }

}
