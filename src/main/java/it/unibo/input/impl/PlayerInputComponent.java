package it.unibo.input.impl;

import it.unibo.utils.V2d;
import it.unibo.utils.P2d;
import it.unibo.input.api.InputComponent;
import it.unibo.input.api.InputController;
import it.unibo.model.*;

public class PlayerInputComponent implements InputComponent {

	public final static int SPEED = 10;
	public final static int ADJUST_RIGHT_BORDER_LIMIT = 90;

	public void update(GameObject gameObject, InputController ctrl) {

		P2d pos = gameObject.getCurrentPos();

		if (ctrl.isMoveLeft()) {
			if (pos.x > 0)
				pos = pos.sum(new V2d(-PlayerInputComponent.SPEED, 0));
		} else if (ctrl.isMoveRight()) {
			if (pos.x < World.getInstance().getBBox().getBRCorner().x - PlayerInputComponent.ADJUST_RIGHT_BORDER_LIMIT)
				pos = pos.sum(new V2d(PlayerInputComponent.SPEED, 0));
		}

		gameObject.setPos(pos);

		if (gameObject instanceof Cannon) {
			Cannon cannon = (Cannon) gameObject;
			if (ctrl.isShoot()) {
				ctrl.stopShooting();
				cannon.fireProjectile();
			}
		}

	}

}