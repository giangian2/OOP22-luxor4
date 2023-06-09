package it.unibo.model;

import it.unibo.input.*;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.*;

public class GameObject {

    public static enum Type {
        BALL, CANNON
    }

    private Type type;
    private P2d pos;
    private V2d vel;
    private InputComponent input;
    private PhysicsComponent physics;
    private BoundingBox bbox;

    public GameObject(Type type, P2d pos, V2d vel, InputComponent input, BoundingBox bbox,
            PhysicsComponent physics) {
        this.pos = pos;
        this.vel = vel;
        this.input = input;
        this.bbox = bbox;
        this.type = type;
        this.physics = physics;
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
        return this.pos;
    }

    public V2d getCurrentVel() {
        return this.vel;
    }

    public void flipVelOnY() { // set vel in Y
        this.vel = new V2d(vel.x, -vel.y);
    }

    public void flipVelOnX() {// set vel in X
        this.vel = new V2d(-vel.x, vel.y);
    }

    public BoundingBox getBBox() { // bounding
        return bbox;
    }

    public void updatePhysics(long dt, World w) {
        physics.update(dt, this, w);
    }

    public void updateInput(InputController c){
        input.update(this, c);
    }

}
