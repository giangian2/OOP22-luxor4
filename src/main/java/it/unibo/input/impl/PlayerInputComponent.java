package it.unibo.input.impl;

import it.unibo.utils.V2d;
import it.unibo.utils.P2d;
import it.unibo.input.api.InputComponent;
import it.unibo.input.api.InputController;
import it.unibo.model.impl.Cannon;
import it.unibo.model.impl.GameObject;
import it.unibo.model.impl.WorldImpl;

/**
 * A concrete implementation of the InputComponent interface that handles input
 * for the player.
 */
public class PlayerInputComponent implements InputComponent {
    /**
     * The speed at which the player moves.
     */
    public static final int SPEED = 5;

    /**
     * The limit of the right border of the screen.
     */
    public static final int ADJUST_RIGHT_BORDER_LIMIT = 90;

    /**
     * Updates the state of a GameObject based on user input.
     *
     * @param gameObject The GameObject to be updated based on input.
     * @param ctrl       The InputController providing input data for updating the
     *                   GameObject.
     */
    @Override
    public void update(final GameObject gameObject, final InputController ctrl) {
        // Get the current position of the GameObject
        P2d pos = gameObject.getCurrentPos();

        // Update the position of the GameObject based on the input
        int moveSpeedX = 0;
        if (ctrl.isMoveLeft()) {
            moveSpeedX = -PlayerInputComponent.SPEED;
        } else if (ctrl.isMoveRight() && pos.getX() < WorldImpl.getBBox().getBRCorner().getX()
                - PlayerInputComponent.ADJUST_RIGHT_BORDER_LIMIT) {
            moveSpeedX = PlayerInputComponent.SPEED;
        }

        pos = pos.sum(new V2d(moveSpeedX, 0));

        // Set the new position of the GameObject
        gameObject.setPos(pos);

        // If the GameObject is a Cannon and the "Shoot" input action is active, fire a
        // projectile
        if (gameObject instanceof Cannon && ctrl.isShoot()) {
            final Cannon cannon = (Cannon) gameObject;
            ctrl.stopShooting();
            cannon.fireProjectile();
        }
    }

}
