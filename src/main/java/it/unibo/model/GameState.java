package it.unibo.model;

import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.P2d;
import it.unibo.utils.Path;
import it.unibo.utils.QueueManager;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.events.api.WorldEventListener;
import it.unibo.input.KeyboardInputController;

public class GameState {

    private int score;
    private World world;
    private boolean pause;

    public GameState(WorldEventListener l) {
        score = 0;
        pause = false;
        world = new World(new RectBoundingBox(new P2d(0, 600), new P2d(800, 0)), 10);
        world.setCannon(GameObjectsFactory.getInstance().createCannon(new P2d(470, 470)));
        world.setEventListener(l);
    }
    
    public World getWorld() {
        return world;
    }

    public void incScore() {
        score++;
    }

    public void decScore() {
        score--;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver(QueueManager queueManager) {
        if(queueManager.balls.isEmpty()) {
            return true; //empty queue
        }

        //if one or more balls in the queue have no path to follow
        Path path = queueManager.path;
        for (Ball ball : queueManager.balls) {
            if (path.getMove(ball.getCurrentPos()) == null) {
                return true; 
            }
        }

        return false; //game not over

    }

    public void changePauseState() {
        if (pause) {
            pause = false;
        } else {
            pause = true;
        }
    }

    public void update(long dt) {
        if (!pause) {
            world.updateState(dt);
        }
    }

    public void processInput(KeyboardInputController controller) {
        if (!pause) {
            world.getCannon().updateInput(controller);
        }
    }
}