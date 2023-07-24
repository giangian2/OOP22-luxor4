package it.unibo.graphics.impl;

import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.GameObject;
import it.unibo.utils.P2d;

import java.awt.Color;
import java.awt.Graphics;

public class BallGraphicsComponent implements GraphicsComponent {

    @Override
    public void update(GameObject obj, it.unibo.graphics.impl.Graphics g) {
        if (!(obj instanceof Ball)) {
            throw new IllegalArgumentException("GameObject is not a Ball");
        }
        Ball ball = (Ball) obj;

        // Disegna la pallina
        P2d pos = ball.getCurrentPos();
        BallColor ballColor = ball.getColor();
        g.setColor(getColorForBall(ballColor));
        g.fillOval((int) pos.x, (int) pos.y, Ball.IMAGE_DIAMETER, Ball.IMAGE_DIAMETER);
    }

    //ottine il colore corrispondente a un colore della pallina
    private Color getColorForBall(BallColor ballColor) {
        switch (ballColor) {
            case RED:
                return Color.RED;
            case GREEN:
                return Color.GREEN;
            case BLUE:
                return Color.BLUE;
            case YELLOW:
                return Color.YELLOW;
            default:
                throw new IllegalArgumentException("Invalid ball color");
        }
    }

   
}
