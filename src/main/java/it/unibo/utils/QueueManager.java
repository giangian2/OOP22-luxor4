package it.unibo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.enums.Direction;
import it.unibo.model.Ball;

public class QueueManager {

    public Path path;
    public List<Ball> balls;
    private int steps;

    public QueueManager(int nBalls, int steps, String xmlPathSrc) {
        path = new Path.PathBuilder(xmlPathSrc).build();
        this.balls = new ArrayList<>();
        this.balls = Collections.synchronizedList(this.balls);
        this.instatiate(nBalls);
        this.steps = steps;
    }

    private void instatiate(int n) {
        var factory = GameObjectsFactory.getInstance();
        var pos = path.getFirst();
        Direction direction;

        // crea una lista di palline che vada bene

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

        // assegna le posizioni
        for (int i = 0; i < balls.size(); i++) {
            System.out.println("ball n° " + i);
            balls.get(i).setPos(pos);
            direction = path.getMove(pos);
            pos = new P2d(pos.x + direction.getVelocity().getX() * Ball.IMAGE_DIAMETER,
                    pos.y + direction.getVelocity().getY() * Ball.IMAGE_DIAMETER);
        }

    }

    public Direction getMove(Ball ball) {
        return path.getMove(ball.getCurrentPos());
    }

    public void shiftBalls(int startIndex) {
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
                }
                firstShifting = false;
            }
        }

    }

    public void shiftBallsStepsTime() {
        for (int i = 0; i < steps; i++) {
            shiftBalls(0);
        }
    }

    public List<Ball> getCloseByThree() {
        var ballList = this.balls;
        List<Ball> returnList = new ArrayList<Ball>();

        for (int i = 0; i < ballList.size() - 2;) {
            var currentColor = ballList.get(i).getColor();
            int count = 1;
            for (int j = 1; (i + j < ballList.size()) &&
                    (ballList.get(i + j).getColor() == currentColor) &&
                    (ballList.get(i + j).isNear(ballList.get(i + j - 1))); j++) {
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
