package it.unibo.enums;

import it.unibo.utils.V2d;

public enum Direction {
    UP(new V2d(-1,0)),
    DOWN(new V2d(1,0)),
    LEFT(new V2d(0,-1)),
    RIGHT(new V2d(0,1));

    private V2d velocity;

    private Direction(V2d velocity){
        this.velocity=velocity;
    }

    public V2d getVelocity(){
        return this.velocity;
    }
}
