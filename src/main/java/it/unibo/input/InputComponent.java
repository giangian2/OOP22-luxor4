package it.unibo.input;

import it.unibo.model.*;

//nota per la FEDE dalla FEDE:  guarda PlayerInputComponent() in GameObjectsFactory
public interface InputComponent {
    public void update(GameObject ball, InputController c);

    
}