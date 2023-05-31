package main.java.it.unibo.input;

import it.unibo.utils.V2d;
import sit.unibo.model.*;


public class PlayerInputComponent implements InputComponent {

	public void update(GameObject ball, InputController ctrl){
		
		if (ctrl.isMoveLeft()){
			double speed = ball.getCurrentVel().module();
			ball.setVel(new V2d(-1,0).mul(speed));			
		} else if (ctrl.isMoveRight()){
			double speed = ball.getCurrentVel().module();
			ball.setVel(new V2d(1,0).mul(speed));			
		}
	
	}
}