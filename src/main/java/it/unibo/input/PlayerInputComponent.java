package it.unibo.input;

import it.unibo.utils.V2d;
import it.unibo.utils.P2d;
import it.unibo.model.*;

public class PlayerInputComponent implements InputComponent {

	public void update(GameObject ball, InputController ctrl) {

		if (ctrl.isMoveLeft()) {

			double pos = ball.getCurrentPos().module();
		//	ball.setPos(new P2d(y, 0).mul(p));

			double speed = ball.getCurrentVel().module();
			ball.setVel(new V2d(-1, 0).mul(speed));
		} else if (ctrl.isMoveRight()) {
			double pos = ball.getCurrentPos().module();
			//ball.setPos(new P2d(1, 0).mul(pos));



			double speed = ball.getCurrentVel().module();
			ball.setVel(new V2d(1, 0).mul(speed));
		}

		

	}
}