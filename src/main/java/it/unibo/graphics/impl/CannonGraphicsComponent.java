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

    private Image icon;

    public CannonGraphicsComponent() {
        super();
        this.loadImage();
    }

    private void loadImage() {
        Image image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource("images/cannone.png"));
            this.icon = image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameObject obj, java.awt.Graphics2D g) {
        if (!(obj instanceof Cannon)) {
            throw new IllegalArgumentException("GameObject is not a Cannon");
        }
        Cannon cannon = (Cannon) obj;

        g.drawImage(this.icon, (int) cannon.getCurrentPos().x, (int) cannon.getCurrentPos().y, null);
    }

}
