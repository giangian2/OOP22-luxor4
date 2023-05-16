package it.unibo.model;

import it.unibo.input.*;
import it.unibo.model.api.BoundingBox;
import it.unibo.utils.*;

public class GameObject {

    public static enum Type {
        BALL, CANNON_BALL
    }

    private Type type;
    private P2d pos;
    private V2d vel;
    private InputComponent input;
    private BoundingBox bbox;

    protected GameObject(Type type, P2d pos, V2d vel, InputComponent input, BoundingBox bbox) {
        this.pos = pos;
        this.vel = vel;
        this.input = input;
        this.bbox = bbox;
        this.type = type;
    }

    public Type getType() {
        return type;
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

    public void flipVelOnY() {
        this.vel = new V2d(vel.x, -vel.y);
    }

    public void flipVelOnX() {
        this.vel = new V2d(-vel.x, vel.y);
    }

    public BoundingBox getBBox() {
        return bbox;
    }
    // public void updateInput(InputController c){
    // input.update(this, c);
    // }

}
