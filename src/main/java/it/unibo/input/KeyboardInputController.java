package it.unibo.input;

public class KeyboardInputController implements InputController {
private boolean isMoveLeft;
private boolean isMoveRight;


    @Override
    public boolean isMoveLeft() {
        return isMoveLeft;
    }

    @Override
    public boolean isMoveRight() {
        return isMoveRight;
    }

    public void notifyMoveLeft() {
        isMoveLeft = true;
    }

    //i metodi no more
    public void notifyNoMoreMoveLeft() {
        isMoveLeft = false;
    }

    public void notifyMoveRight() {
        isMoveRight = true;
    }

    public void notifyNoMoreMoveRight() {
        isMoveRight = false;
    }

}