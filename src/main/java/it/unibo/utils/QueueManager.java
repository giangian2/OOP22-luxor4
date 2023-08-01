package it.unibo.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.enums.Direction;
import it.unibo.model.Ball;

/**
 * The QueueManager manages a list of Ball objects associated with a Path.
 */
public class QueueManager {
    private Path path;
    private List<Ball> balls;
    private int steps;

    /**
     * Constructs a QueueManager with the specified parameters.
     *
     * @param nBalls The number of balls in the list
     * @param steps The number of times the balls will be moved forward.
     * @param xmlPathSrc The path of the xml file containing the Path.
     */
    public QueueManager(final int nBalls, final int steps, final String xmlPathSrc) {
        path = new Path.PathBuilder(xmlPathSrc).build();
        this.balls = new ArrayList<>();
        this.balls = Collections.synchronizedList(this.balls);
        this.instatiate(nBalls);
        this.steps = steps;
    }

    /**
     * Returns the list of balls present in the current instance of this class.
     *
     * @return A list containing the balls present in the current instance.
     */
    public List<Ball> getBalls() {
        return this.balls;
    }

    /**
     * Instatiate a list of n Ball with the following properties.
     * <ul>
     *     <li>The list does not contain more than two adjacent balls of the same color</li>
     *     <li>The positions of the balls will follow the Path vertex</li>
     * </ul>
     * @param n The number of balls in the list.
    */
    private void instatiate(final int n) {
        var factory = GameObjectsFactory.getInstance();
        var pos = path.getFirst();
        Direction direction;

        /**
         * Create the list of balls following the color property
         */
        for (int i = 0; i < n; i++) {
            this.balls.add(factory.createBall(new P2d(0, 0), new V2d(0, 0), BallColor.getRandomColor()));
            if (i > 2) {
                if (balls.get(balls.size() - 1).getColor() == balls.get(balls.size() - 2).getColor()
                        && balls.get(balls.size() - 2).getColor() == balls.get(balls.size() - 3).getColor()) {
                    balls.remove(balls.size() - 1);
                    i--;
                }
            }
        }

        /**
         * Set the positions of the balls following the Path
         */
        for (int i = 0; i < balls.size(); i++) {
            balls.get(i).setPos(pos);
            direction = path.getMove(pos);
            pos = new P2d(pos.x + direction.getVelocity().getX() * Ball.IMAGE_DIAMETER,
                    pos.y + direction.getVelocity().getY() * Ball.IMAGE_DIAMETER);
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
     * Move balls contained in the list by one pixel starting from a specified index.
     * @param startIndex The index from which the shift must start.
     */
    public void shiftBalls(final int startIndex) {
        boolean firstShifting = true;

        if (startIndex < this.balls.size()) {

            for (int i = startIndex; i < this.balls.size(); i++) {

                var nextMove = this.getMove(this.balls.get(i));
                var currentPos = this.balls.get(i).getCurrentPos();

                if (i > 0 && !firstShifting) {
                    if (Math.abs(this.balls.get(i - 1).getCurrentPos().x - currentPos.x) > Ball.IMAGE_DIAMETER
                            || Math.abs(this.balls.get(i - 1).getCurrentPos().y - currentPos.y) > Ball.IMAGE_DIAMETER) {

                        break;
                    }

                }

                switch (nextMove) {
                    case UP:
                        this.balls.get(i).setPos(new P2d(currentPos.x, currentPos.y - 1));
                        break;

                    case DOWN:
                        this.balls.get(i).setPos(new P2d(currentPos.x, currentPos.y + 1));
                        break;

                    case LEFT:
                        this.balls.get(i).setPos(new P2d(currentPos.x - 1, currentPos.y));
                        break;

                    case RIGHT:
                        this.balls.get(i).setPos(new P2d(currentPos.x + 1, currentPos.y));
                        break;
                    default:
                        break;
                }
                firstShifting = false;
            }
        }

    }

    /**
     * Call steps-time the method {@link #shiftBalls()}.
     */
    public void shiftBallsStepsTime() {
        for (int i = 0; i < steps; i++) {
            shiftBalls(0);
        }
    }

    /**
     * Search if there are sub-lists of at least three adjacent balls with the same color in the ball list.
     * @return The sum of the sub-lists founded.
     */
    public List<Ball> getCloseByThree() {
        var ballList = this.balls;
        List<Ball> returnList = new ArrayList<Ball>();

        for (int i = 0; i < ballList.size() - 2;) {
            var currentColor = ballList.get(i).getColor();
            int count = 1;
            for (int j = 1; (i + j < ballList.size())
                    && (ballList.get(i + j).getColor() == currentColor)
                    && (ballList.get(i + j).isNear(ballList.get(i + j - 1))); j++) {
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
