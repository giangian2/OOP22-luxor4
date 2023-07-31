package it.unibo.graphics.impl;

import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.GameObject;
import it.unibo.utils.P2d;

public class BallGraphicsComponent implements MyGraphicsComponent {

    @Override
    public void update(GameObject obj, java.awt.Graphics2D g) {
        if (!(obj instanceof Ball)) {
            throw new IllegalArgumentException("GameObject is not a Ball");
        }
        Ball ball = (Ball) obj;

        // Disegna la pallina
        P2d pos = ball.getCurrentPos();
        BallColor ballColor = ball.getColor();
        g.setColor(ballColor.getBallColor());
        g.fillOval((int) pos.x, (int) pos.y, Ball.IMAGE_DIAMETER, Ball.IMAGE_DIAMETER);
    }
   
}
