package it.unibo.graphics.impl;

import it.unibo.enums.BallColor;
import it.unibo.model.impl.Ball;
import it.unibo.model.impl.GameObject;
import it.unibo.utils.P2d;

/**
 * A graphics component responsible for rendering a Ball GameObject on the
 * screen.
 */
public class BallGraphicsComponent implements MyGraphicsComponent {

    /**
     * Updates the graphical representation of a Ball GameObject on the screen.
     *
     * @param obj The GameObject to be updated. Must be an instance of Ball.
     * @param g   The Graphics2D object used for drawing the Ball.
     * @throws IllegalArgumentException If the given GameObject is not a Ball.
     */
    @Override
    public void update(final GameObject obj, final java.awt.Graphics2D g) {
        // Check if the GameObject is a Ball instance.
        if (!(obj instanceof Ball)) {
            throw new IllegalArgumentException("GameObject is not a Ball");
        }
        // Cast the GameObject to a Ball object.
        final Ball ball = (Ball) obj;

        // Get the current position of the ball.
        final P2d pos = ball.getCurrentPos();

        // Get the color of the ball.
        final BallColor ballColor = ball.getColor();

        // Set the color of the Graphics2D object for drawing the ball.
        g.setColor(ballColor.getBallColor());

        // Draw a filled oval (representing the ball) using the ball's color and
        // dimensions.
        // The x and y position of the oval are based on the x and y coordinates of the
        // ball from pos.
        // Ball.IMAGE_DIAMETER is likely a constant representing the diameter of the
        // ball.
        g.fillOval((int) pos.getX(), (int) pos.getY(), Ball.IMAGE_DIAMETER, Ball.IMAGE_DIAMETER);
    }
}
