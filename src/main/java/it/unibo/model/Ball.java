package it.unibo.model;

import it.unibo.enums.BallColor;
import it.unibo.graphics.impl.BallGraphicsComponent;
import it.unibo.input.*;
import it.unibo.model.api.BoundingBox;
import it.unibo.physics.api.PhysicsComponent;
import it.unibo.utils.*;

public class Ball extends GameObject {

    private BallColor color;
    public static final int IMAGE_DIAMETER = 25;
    public static final int RADIUS = 0;

    public Ball(Type type, P2d pos, BallColor color, V2d vel, InputComponent input, BoundingBox bbox,
            PhysicsComponent physics, BallGraphicsComponent graph) {

        super(Type.BALL, pos, vel, input, bbox, graph, physics);
        this.color = color;
    }

    public void setColor(BallColor color) {
        this.color = color;
    }

    public BallColor getColor() {
        return this.color;
    }

    public boolean isNear(Ball ball) {
        return Math.abs(this.getCurrentPos().sumOfAxis() - ball.getCurrentPos().sumOfAxis()) < (IMAGE_DIAMETER + 2);
    }

}
