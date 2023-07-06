package it.unibo.input;

import it.unibo.model.CannonBall;

import java.awt.event.*;

public class CannonInput extends KeyboardInputController{

    private CannonBall cannonBall;

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isMoveLeft = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            isMoveRight = true;
        } else if (key == KeyEvent.VK_SPACE) {
            cannonBall.fireProjectile();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            isMoveLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            isMoveRight = false;
        }
    }
}
