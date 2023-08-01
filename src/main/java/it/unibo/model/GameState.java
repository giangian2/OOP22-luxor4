package it.unibo.model;

import it.unibo.utils.Path;
import it.unibo.utils.QueueManager;
import it.unibo.core.api.Level;
import it.unibo.enums.Direction;
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

    public boolean isGameOver() {
        var res = this.getWorld().moveSingleBall(this.getWorld().getQueue().get(this.getWorld().getQueue().size() - 1));
        if (res == Direction.NONE)
            return true;
        else
            return false;
    }

    public void changePauseState() {
        if (pause) {
            getWorld().unpauseBackgroundSound();
            pause = false;
        } else {
            getWorld().pauseBackgroundSound();
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