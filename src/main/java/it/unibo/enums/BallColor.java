package it.unibo.enums;

import java.awt.Color;

public enum BallColor {
    RED(Color.RED),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW);

    private Color color;

    private BallColor(Color color){
        this.color=color;
    }

    public Color getBallColor(){
        return this.color;
    }

}
