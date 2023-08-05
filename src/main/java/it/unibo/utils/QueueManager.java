package it.unibo.utils;

import java.util.ArrayList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.enums.Direction;
import it.unibo.model.impl.Ball;
import it.unibo.utils.impl.XmlPath;

/**
 * The QueueManager manages a list of Ball objects associated with a Path.
 */
public class QueueManager {
    private final XmlPath path;
    private final List<Ball> balls;
    private final int steps;

    /**
     * Constructs a new QueueManager with the specified parameters.
     *
     * @param nBalls     The number of balls in the list
     * @param steps      The number of times the balls will be moved forward.
     * @param xmlPathSrc The path of the xml file containing the Path.
     */
    public QueueManager(final int nBalls, final int steps, final String xmlPathSrc) {
        path = new XmlPath.XmlPathBuilder(xmlPathSrc).build();
        this.balls = new ArrayList<>();
        this.instatiate(nBalls);
        this.steps = steps;
    }

    /**
     * Returns the list of balls present in the current instance of this class.
     *
     * @return A list containing the balls present in the current instance.
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP",
            "EI_EXPOSE_REP2" }, justification = "Exposing internal representation of balls list for specific use case.")
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * Instatiate a list of n Ball with the following properties.
     * <ul>
     * <li>The list does not contain more than two adjacent balls of the same
     * color</li>
     * <li>The positions of the balls will follow the Path vertex</li>
     * </ul>
     * 
     * @param n The number of balls in the list.
     */
    private void instatiate(final int n) {
        final var factory = GameObjectsFactory.getInstance();
        var pos = path.getFirst();
        Direction direction;

        /**
         * Create the list of balls following the color property
         */
        for (int i = 0; i < n; i++) {
            this.balls.add(factory.createBall(new P2d(0, 0), new V2d(0, 0), BallColor.getRandomColor()));
            if (i > 2
                    && balls.get(balls.size() - 1).getColor() == balls.get(balls.size() - 2).getColor()
                    && balls.get(balls.size() - 2).getColor() == balls.get(balls.size() - 3).getColor()) {

                balls.remove(balls.size() - 1);
                i--;
            }
        }

        /**
         * Set the positions of the balls following the Path
         */
        for (final var b : balls) {
            b.setPos(pos);
            direction = path.getMove(pos);
            pos = new P2d(pos.getX() + direction.getVelocity().getX() * Ball.IMAGE_DIAMETER,
                    pos.getY() + direction.getVelocity().getY() * Ball.IMAGE_DIAMETER);
        }
    }

    /**
     * 
     * @param ball The ball of which you want to know the next movement
     * @return The direction of the ball next movement
     * @see Path
     */
    public Direction getMove(final Ball ball) {
        return path.getMove(ball.getCurrentPos());
    }

    /**
     * Move balls contained in the list by one pixel starting from a specified
     * index.
     * 
     * @param startIndex The index from which the shift must start.
     */
    public void shiftBalls(final int startIndex) {
        boolean firstShifting = true;

        if (startIndex < this.balls.size()) {

            for (int i = startIndex; i < this.balls.size(); i++) {

                final var nextMove = this.getMove(this.balls.get(i));
                final var currentPos = this.balls.get(i).getCurrentPos();

                if (i > 0 && !firstShifting
                        && (Math.abs(
                                this.balls.get(i - 1).getCurrentPos().getX() - currentPos.getX()) > Ball.IMAGE_DIAMETER
                                || Math.abs(this.balls.get(i - 1).getCurrentPos().getY()
                                        - currentPos.getY()) > Ball.IMAGE_DIAMETER)) {

                    break;

                }

                switch (nextMove) {
                    case UP:
                        this.balls.get(i).setPos(new P2d(currentPos.getX(), currentPos.getY() - 1));
                        break;

                    case DOWN:
                        this.balls.get(i).setPos(new P2d(currentPos.getX(), currentPos.getY() + 1));
                        break;

                    case LEFT:
                        this.balls.get(i).setPos(new P2d(currentPos.getX() - 1, currentPos.getY()));
                        break;

                    case RIGHT:
                        this.balls.get(i).setPos(new P2d(currentPos.getX() + 1, currentPos.getY()));
                        break;
                    default:
                        break;
                }
                firstShifting = false;
            }
        }

    }

    /**
     * Call steps-time the method.
     */
    public void shiftBallsStepsTime() {
        for (int i = 0; i < steps; i++) {
            shiftBalls(0);
        }
    }

    /**
     * Search if there are sub-lists of at least three adjacent balls with the same
     * color in the ball list.
     * 
     * @return The sum of the sub-lists founded.
     */
    public List<Ball> checkCloseByThree() {
        final var ballList = this.balls;
        final List<Ball> returnList = new ArrayList<>();

        for (int i = 0; i < ballList.size() - 2;) {
            final var currentColor = ballList.get(i).getColor();
            int count = 1;
            for (int j = 1; i + j < ballList.size()
                    && ballList.get(i + j).getColor() == currentColor
                    && ballList.get(i + j).isNear(ballList.get(i + j - 1)); j++) {
                count++;
            }
            if (count > 2) {
                for (int j = 0; j < count; j++) {
                    returnList.add(ballList.get(i + j));
                }
            }
            i += count;
        }

        return returnList;
    }
}
