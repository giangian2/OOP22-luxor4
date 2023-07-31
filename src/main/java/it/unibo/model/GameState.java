package it.unibo.model;

import it.unibo.utils.Path;
import it.unibo.utils.QueueManager;
import it.unibo.core.api.Level;
import it.unibo.events.api.WorldEventListener;
import it.unibo.input.KeyboardInputController;

public class GameState {

    private int score;
    private World world;
    private boolean pause;
    private Level level;

    public GameState(WorldEventListener l, Level level) {
        score = 0;
        pause = false;
        this.level = level;
        this.loadLevel();
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

    private void loadLevel() {
        this.world = this.level.loadLevel();
    }

    public boolean isGameOver(QueueManager queueManager) {
        if (queueManager.balls.isEmpty()) {
            return true; // empty queue
        }

        // if one or more balls in the queue have no path to follow
        Path path = queueManager.path;
        for (Ball ball : queueManager.balls) {
            if (path.getMove(ball.getCurrentPos()) == null) {
                return true;
            }
        }

        return false; // game not over

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
            
            this.getWorld().getCLoseByThree().forEach((el) -> {
                System.out.println(this.getScore());
                this.incScore();
                this.getWorld().getQueue().remove(el);
            });
        }
    }

    public void processInput(KeyboardInputController controller) {
        if (!pause) {
            world.getCannon().updateInput(controller);
        }
    }
}