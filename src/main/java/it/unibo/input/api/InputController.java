package it.unibo.input.api;

public interface InputController {
    boolean isMoveLeft();

    boolean isMoveRight();

    boolean isShoot();

    void stopShooting();
}