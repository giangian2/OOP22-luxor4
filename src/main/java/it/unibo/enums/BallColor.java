package it.unibo.enums;

import java.awt.Color;
import java.util.Random;

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

    public BallColor getRandomColor(){
        BallColor[] colors = BallColor.values();
        Random rand= new Random();
        return colors[rand.nextInt(colors.length)];
    }
}
