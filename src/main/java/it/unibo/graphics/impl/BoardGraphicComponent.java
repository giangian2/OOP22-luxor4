package it.unibo.graphics.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.GameObject;

public class BoardGraphicComponent implements MyGraphicsComponent {

    private String backgroundPath;
    private Image img;

    public BoardGraphicComponent(String backgorundPath) {
        super();
        this.backgroundPath = backgorundPath;
        this.loadImage();
    }

    private void loadImage() {
        Image image = null;
        try {
            image = ImageIO.read(ClassLoader.getSystemResource(this.backgroundPath));
            this.img = image;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void update(GameObject obj, java.awt.Graphics2D g) {
        // frame.setUndecorated(true); // Remove title bar

        g.drawImage(img, 0, 0, null);
    }

    public Image getBackgorundImg() {
        return this.img;
    }

}
