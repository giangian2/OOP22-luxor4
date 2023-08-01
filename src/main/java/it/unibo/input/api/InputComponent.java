package it.unibo.input.api;

import it.unibo.model.*;

/**
 * An interface for defining an input component that updates the state of a GameObject based on user input.
 */
public interface InputComponent {

    /**
     * Updates the state of a GameObject based on user input.
     *
     * @param gameObject The GameObject to be updated based on input.
     * @param controller The InputController providing input data for updating the GameObject.
     */
    public void update(GameObject gameObject, InputController c);

}