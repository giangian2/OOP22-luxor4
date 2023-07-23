package it.unibo.utils;

import java.io.IOException;
import java.util.ArrayList;
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

    public QueueManager(int nBalls) {
        path = new Path.PathBuilder().build();
        this.balls = new ArrayList<>();
        this.instatiate(nBalls);
    }

    private void instatiate(int n) {
        var factory = GameObjectsFactory.getInstance();
        var pos = path.getFirst();

        for (int i = 0; i < n; i++) {
            if (this.path.getMove(pos) == Direction.LEFT) {
                this.balls.add(factory.createBall(pos, null, BallColor.GREEN));
                pos = new P2d(pos.x - Ball.IMAGE_DIAMETER - 10, pos.y);
            }
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

    public List<Ball> getCloseByThree() {
        var ballList = this.balls;
        List<Ball> returnList = new ArrayList<Ball>();

        for (int i = 0; i < ballList.size() - 2;) {
            var currentColor = ballList.get(i).getColor();
            int count = 1;
            for (int j = 1; (i + j < ballList.size()) && (ballList.get(i + j).getColor() == currentColor); j++) {
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
