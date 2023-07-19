package it.unibo.input;

public class KeyboardInputController implements InputController {

    boolean isMoveLeft;
    boolean isMoveRight;
    boolean isShoot;

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

    // i metodi no more
    public void notifyNoMoreMoveLeft() {
        isMoveLeft = false;
    }

    public void notifyMoveRight() {
        isMoveRight = true;
    }

    public void notifyNoMoreMoveRight() {
        isMoveRight = false;
    }

    @Override
    public boolean isShoot() {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'isShoot'");
       return isShoot;
    }

}