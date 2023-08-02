package it.unibo.enums;

import java.awt.Color;
import java.util.Random;
/**
 * Enum that represents all possible colors of a ball,
 * it uses Color class.
 * 
 * @see java.awt.Color
 */
public enum BallColor {

    /**
     * Color red.
     */
    RED(Color.RED),

    /**
     * Color blue.
     */
    BLUE(Color.BLUE),

    /**
     * Color green.
     */
    GREEN(Color.GREEN),

    /**
     * Color yellow.
     */
    YELLOW(Color.YELLOW);

    private Color color;

    /**
     * Private constructor of BallColor.
     * @param color Color representation
     */
    BallColor(final Color color) {
        this.color = color;
    }

    /**
     * Return the color of the enum element.
     * @return Color represented by enum element.
     */
    public Color getBallColor() {
        return this.color;
    }

    /**
     * Get a random element of the enum.
     * @return Random ballColor.
     */
    public static BallColor getRandomColor() {
        BallColor[] colors = BallColor.values();
        Random rand = new Random();
        return colors[rand.nextInt(colors.length)];
    }
}
