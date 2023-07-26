package it.unibo.graphics.impl;

import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.Cannon;
import it.unibo.model.GameObject;
import it.unibo.utils.P2d;


import java.awt.Color;
import java.awt.Graphics;

public class CannonGraphicsComponent implements MyGraphicsComponent {

    
    private static final int CANNON_WIDTH = 40;
    private static final int CANNON_HEIGHT = 20;

    @Override
    public void update(GameObject obj, java.awt.Graphics2D g){
        if (!(obj instanceof Cannon)) {
            throw new IllegalArgumentException("GameObject is not a Cannon");
        }
        Cannon cannon = (Cannon) obj;

        // Disegna il cannone
        P2d pos = cannon.getCurrentPos();
        
        // Disegna la pallina stazionaria del cannone
        Ball stationaryBall = cannon.getStationaryBall();
        BallColor ballColor = stationaryBall.getColor();
        g.setColor(ballColor.getBallColor());
        

        // Disegna le palline sparate dal cannone
        for (Ball ball : cannon.getFiredBalls()) {
            P2d ballPos = ball.getCurrentPos();
            g.setColor(ballColor.getBallColor());
           
        }
    }


   
}



 