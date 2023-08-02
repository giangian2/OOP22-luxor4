package it.unibo.input.api;

/**
 * An interface for defining an input controller that provides input data for the game.
 */
public interface InputController {
     /**
     * Checks if the "Move Left" input action is currently active.
     *
     * @return true if the "Move Left" input action is active, false otherwise.
     */
    boolean isMoveLeft();

    /**
     * Checks if the "Move Right" input action is currently active.
     *
     * @return true if the "Move Right" input action is active, false otherwise.
     */
    boolean isMoveRight();

    /**
     * Checks if the "Shoot" input action is currently active.
     *
     * @return true if the "Shoot" input action is active, false otherwise.
     */
    boolean isShoot();

    /**
     * Stops the "Shoot" input action.
     * This method is called to indicate that the "Shoot" action should stop, e.g., when the shoot key is released.
     */
    void stopShooting();
}
