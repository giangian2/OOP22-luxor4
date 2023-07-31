package it.unibo.graphics.impl;

import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.Cannon;
import it.unibo.model.GameObject;
import it.unibo.utils.P2d;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CannonGraphicsComponent implements MyGraphicsComponent {

    
    private static final int CANNON_WIDTH = 40;
    private static final int CANNON_HEIGHT = 20;
    private String cannonPath;
    private Image img;

    public CannonGraphicsComponent(String backgorundPath) {
        super();
        this.cannonPath = backgorundPath;
        this.loadImage();
    }

    private void loadImage() {
        Image image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource(this.cannonPath));
            this.img = image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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



 