package it.unibo.enums;

import java.awt.Color;
import java.util.Random;
/**
 * Enum that represents all possible colors of a ball,
 * it uses Color class.
 * 
 * @see Color
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
    private static Random rand = new Random();

    /**
     * Constructs a new BallColor enum element with the specified Color value.
     * 
     * @param color The Color value representing the ball's color.
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
     * @return Random BallColor.
     */
    public static BallColor getRandomColor() {
        BallColor[] colors = BallColor.values();
        return colors[rand.nextInt(colors.length)];
    }
}
