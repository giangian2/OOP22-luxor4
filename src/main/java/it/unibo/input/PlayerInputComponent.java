package it.unibo.input;

import it.unibo.utils.V2d;
import it.unibo.utils.P2d;
import it.unibo.model.*;

public class PlayerInputComponent implements InputComponent {

	public final static int SPEED = 10;

	public void update(GameObject gameObject, InputController ctrl) {
		Cannon cannon = (Cannon) gameObject;
		P2d pos = cannon.getCurrentPos();
	
		if (ctrl.isMoveLeft()) {
			pos = pos.sum(new V2d(-PlayerInputComponent.SPEED, 0));
			if(pos.x < 0) pos = new P2d(0, pos.y);
		} else if (ctrl.isMoveRight()) {
			pos = pos.sum(new V2d(PlayerInputComponent.SPEED, 0));
			if(pos.x > 700) pos = new P2d(700, pos.y);
		}
	
		
	
		cannon.setPos(pos);
	
		if (ctrl.isShoot()) {
			ctrl.stopShooting();
			cannon.fireProjectile();
		}
	}
	
}