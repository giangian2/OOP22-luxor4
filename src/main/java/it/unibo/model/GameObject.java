package it.unibo.model;

import it.unibo.input.*;
import it.unibo.utils.*;

public class GameObject {

    private P2d pos;
    private V2d vel;
    private InputComponent input;

    protected GameObject(P2d pos, V2d vel, InputComponent input) {
        this.pos = pos;
        this.vel = vel;
        this.input = input;
    }

    public void setPos(P2d pos) {
        this.pos = pos;
    }

    public void setVel(V2d vel) {
        this.vel = vel;
    }

    public V2d getVel(V2d vel) {
        return this.vel;
    }

    public P2d getCurrentPos() {
        return pos;
    }

    // public void updateInput(InputController c){
    // input.update(this, c);
    // }

}
