package it.unibo.graphics.impl;

import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.Cannon;
import it.unibo.model.GameObject;
import it.unibo.utils.P2d;

import java.awt.Color;
import java.awt.Graphics;

public class CannonGraphicsComponent implements GraphicsComponent {

    
    private static final int CANNON_WIDTH = 40;
    private static final int CANNON_HEIGHT = 20;

    @Override
    public void update(GameObject obj, it.unibo.graphics.impl.Graphics g){
        if (!(obj instanceof Cannon)) {
            throw new IllegalArgumentException("GameObject is not a Cannon");
        }
        Cannon cannon = (Cannon) obj;

        // Disegna il cannone
        P2d pos = cannon.getCurrentPos();
        
        // Disegna la pallina stazionaria del cannone
        Ball stationaryBall = cannon.getStationaryBall();
        BallColor ballColor = stationaryBall.getColor();
        g.setColor(getColorForBall(ballColor));
        g.drawImage(null, (int) pos.x + CANNON_WIDTH, (int) pos.y - CANNON_HEIGHT / 2, Ball.RADIUS * 2, Ball.RADIUS * 2);

        // Disegna le palline sparate dal cannone
        for (Ball ball : cannon.getFiredBalls()) {
            P2d ballPos = ball.getCurrentPos();
            g.setColor(getColorForBall(ball.getColor()));
            g.drawImage(null, (int) ballPos.x, (int) ballPos.y, Ball.RADIUS * 2, Ball.RADIUS * 2);
        }
    }

    //ottiene il colore corrispondente a un colore della pallina
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



 