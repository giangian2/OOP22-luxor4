package it.unibo.model;

import it.unibo.enums.BallColor;
import it.unibo.input.*;
import it.unibo.utils.*;

public class Ball extends GameObject{

    private P2d pos;
    private BallColor color;
    private V2d vel;
    private InputComponent input;

    public Ball(P2d pos, BallColor color, V2d vel, InputComponent input){
        super(pos, vel, input);
        this.color = color;
    }

    public void setColor(BallColor color) {
        this.color = color;
    }

    public BallColor getColor(BallColor color) {
        return this.color;
    }
}
