package it.unibo.model;

import it.unibo.model.impl.RectBoundingBox;
import it.unibo.utils.P2d;
import it.unibo.utils.V2d;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.events.api.WorldEventListener;

public class GameState {

    private int score;
    private World world;

    public GameState(WorldEventListener l) {
        GameObjectsFactory f = GameObjectsFactory.getInstance();

        score = 0;
        world = new World(new RectBoundingBox(new P2d(-9, 8), new P2d(9, -8)));
        /**
         * @TODO
         */
        // world.setCannon
        // word.setQueue
        // world.setListener
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

    public void update(int dt) {

    }
}