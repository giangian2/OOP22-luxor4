package it.unibo.input;

import it.unibo.utils.V2d;
import it.unibo.utils.P2d;
import it.unibo.model.*;

public class PlayerInputComponent implements InputComponent {

	public void update(GameObject ball, InputController ctrl) {

		if (ctrl.isMoveLeft()) {
			P2d pos = ball.getCurrentPos().setCorP2dSx();
			
			//double pos = ball.getCurrentPos().module();
			ball.setPos(pos);

			//double speed = ball.getCurrentVel().module();
			//ball.setVel(new V2d(-1, 0).mul(speed));
		} else if (ctrl.isMoveRight()) {
			//double pos = ball.getCurrentPos().module();
			//ball.setPos(new P2d(1, 0).mul(pos));

			P2d pos = ball.getCurrentPos().setCorP2dDx();
			ball.setPos(pos);

			//double speed = ball.getCurrentVel().module();
			//ball.setVel(new V2d(1, 0).mul(speed));
		}

		

	}
}