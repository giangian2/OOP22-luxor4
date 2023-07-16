package it.unibo.model;

import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.events.api.WorldEventListener;

public class GameState {

    private int score;
    private World world;
    private boolean pause;

    public GameState(WorldEventListener l) {
        score = 0;
        pause = false;
        world = new World(new RectBoundingBox(new P2d(-9, 8), new P2d(9, -8)), 10);
        world.setCannon(GameObjectsFactory.getInstance().createCannon(new P2d(250, 250)));
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

    public boolean isGameOver() {
        return false;
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
}