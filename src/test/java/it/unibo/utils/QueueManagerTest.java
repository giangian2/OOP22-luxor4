package it.unibo.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.GameObject;

import org.junit.jupiter.api.Test;

public class QueueManagerTest {
    @Test
    void testInitializationWithLevel1() {
        assertDoesNotThrow(() -> {
            new QueueManager(10, 1, "levels/1/Path.xml");
        });
    }

    @Test
    void testInitializationWithLevel2() {
        assertDoesNotThrow(() -> {
            new QueueManager(20, 1, "levels/2/Path.xml");
        });
    }

    @Test
    void testCloseByThree() {
        var qm = new QueueManager(0, 1, "levels/1/Path.xml");
/*
        List<Ball> outputList = new ArrayList<>();
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(100, 10), BallColor.GREEN, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(125, 10), BallColor.RED, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(150, 10), BallColor.RED, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(190, 10), BallColor.RED, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(100, 10), BallColor.BLUE, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(100, 10), BallColor.GREEN, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(125, 10), BallColor.GREEN, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(150, 10), BallColor.GREEN, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(100, 10), BallColor.GREEN, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(100, 10), BallColor.YELLOW, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(100, 10), BallColor.YELLOW, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(100, 10), BallColor.RED, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(110, 10), BallColor.RED, null, null, null, null, null));
        qm.balls.add(new Ball(GameObject.Type.BALL, new P2d(120, 10), BallColor.RED, null, null, null, null, null));

        outputList = qm.getCloseByThree();
        assertTrue(outputList.size() == 6);*/
    }
}
