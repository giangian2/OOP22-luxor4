package it.unibo.input.impl;

import it.unibo.input.api.InputComponent;
import it.unibo.input.api.InputController;
import it.unibo.model.*;

/**
 * A concrete implementation of the InputComponent interface that does nothing.
 */
public class NullInputComponent implements InputComponent {

	/**
	 * Updates the state of a GameObject based on user input.
	 *
	 * @param obj The GameObject to be updated based on input.
	 * @param ctrl The InputController providing input data for updating the GameObject.
	 */
	public void update(GameObject obj, InputController ctrl) {

	}

}