package it.unibo.model;

import it.unibo.enums.BallColor;
import it.unibo.input.*;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.physics.impl.BallPhysicsComponent;
import it.unibo.utils.*;

public class Ball extends GameObject {

    private BallColor color;
    public static final int IMAGE_DIAMETER = 25;

    public Ball(P2d pos, BallColor color, V2d vel, InputComponent input, BoundingBox bbox,
            PhysicsComponent physics) {

        super(Type.BALL, pos, vel, input, bbox, physics);
        this.color = color;
    }

    public void setColor(BallColor color) {
        this.color = color;
    }

    public BallColor getColor() {
        return this.color;
    }
}
