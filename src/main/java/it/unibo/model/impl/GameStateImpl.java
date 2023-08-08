package it.unibo.model.impl;

import java.util.List;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.core.api.Level;
import it.unibo.enums.Direction;
import it.unibo.events.api.WorldEventListener;
import it.unibo.input.api.InputController;
import it.unibo.model.api.GameState;
import it.unibo.model.api.World;

/**
 * 
 * This class manages the game state and provides methods for updating the state
 * based on game events.
 */
@SuppressFBWarnings(value = { "EI_EXPOSE_REP" }, justification = "I prefer to suppress these FindBugs warnings")
public class GameStateImpl implements GameState {

    private int score;
    private World world;
    private boolean pause;
    private final Level level;

    /**
     * Constructs a new GameState object with the specified event listener and
     * level.
     *
     * @param l     The WorldEventListener to handle game events.
     * @param level The Level object representing the current game level.
     */
    public GameStateImpl(final WorldEventListener l, final Level level) {
        score = 0;
        pause = false;
        this.level = level;
        this.loadLevel();
        world.setEventListener(l);
    }

    /**
     * Retrieves the World object representing the game world.
     *
     * @return The World.
     */
    @Override
    public World getWorld() {
        return world;
    }

    /**
     * Increases the current score by 1.
     */
    @Override
    public void incScore() {
        score++;
    }

    /**
     * Decreases the current score by 1.
     */
    @Override
    public void decScore() {
        score--;
    }

    /**
     * Retrieves the current score of the game.
     *
     * @return The current score.
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Loads the level into the game world.
     * This method initializes the game world based on the current level.
     */
    private void loadLevel() {
        this.world = this.level.loadLevel();
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        if (this.getWorld().getQueue().size() > 0) {
            final var res = this.getWorld()
                    .moveSingleBall(this.getWorld().getQueue().get(this.getWorld().getQueue().size() - 1));
            return res == Direction.NONE;
        }
        return false;
    }

    /**
     * Checks if the player has won the game.
     *
     * @return true if the player has won, false otherwise.
     */
    @Override
    public boolean isWin() {
        return this.getWorld().getQueue().size() == 0;
    }

    /**
     * Toggles the pause state of the game.
     * If the game is paused, it will be resumed, and vice versa.
     */
    @Override
    public void changePauseState() {
        if (pause) {
            getWorld().unpauseBackgroundSound();
            pause = false;
        } else {
            getWorld().pauseBackgroundSound();
            pause = true;
        }
    }

    /**
     * Updates the game state based on the elapsed time.
     *
     * @param dt The elapsed time since the last update.
     */
    @Override
    public void update(final long dt) {
        if (!pause) {
            world.updateState(dt);

            final Optional<List<Ball>> toEliminateList = this.getWorld().getCloseByThree();
            if (toEliminateList.isPresent()) {
                toEliminateList.get().forEach((el) -> {
                    this.incScore();
                    this.getWorld().getQueue().remove(el);
                });
            }
        }
    }

    /**
     * Processes the user input using the provided KeyboardInputController.
     * This method updates the state of the game world based on the user's input.
     *
     * @param controller The KeyboardInputController to handle user input.
     */
    @Override
    public void processInput(final InputController controller) {
        if (!pause) {
            world.getCannon().updateInput(controller);
        }
    }
}
