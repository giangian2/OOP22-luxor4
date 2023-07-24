package it.unibo.graphics.impl;

import java.awt.Color;
import java.awt.Image;

import it.unibo.model.*;

public interface Graphics {
    
    public void drawImage(Image image, int x, int y, int width, int height);
    public void update(GameObject obj, Graphics c);

    public void setColor(Color colorForBall);

    public void fillOval(int i, int j, int k, int l);



}