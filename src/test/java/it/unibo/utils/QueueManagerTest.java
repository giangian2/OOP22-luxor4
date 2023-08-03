package it.unibo.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import it.unibo.core.impl.GameObjectsFactory;
import it.unibo.enums.BallColor;
import it.unibo.model.Ball;

import org.junit.jupiter.api.Test;

/**
 * This test class tests QueueManager methods
 */
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
            new QueueManager(10, 1, "levels/2/Path.xml");
        });
    }

    @Test
    @SuppressWarnings("magicnumber")
    void testCheckCloseByThree() {
        

        var qm = new QueueManager(0, 1, "levels/1/Path.xml");
        var gof = GameObjectsFactory.getInstance();
        
        // CHECKSTYLE: MagicNumber OFF
        /*
         * it would be redundant and useless use constants
         * to indicates those arbitraty "magic numbers"
         */

        qm.getBalls().add(gof.createBall(new P2d(100, 0), new V2d(0, 0), BallColor.GREEN));
        qm.getBalls().add(gof.createBall(new P2d(125, 0), new V2d(0, 0), BallColor.RED));
        qm.getBalls().add(gof.createBall(new P2d(150, 0), new V2d(0, 0), BallColor.RED));
        qm.getBalls().add(gof.createBall(new P2d(175, 0), new V2d(0, 0), BallColor.RED));
        qm.getBalls().add(gof.createBall(new P2d(175, 25), new V2d(0, 0), BallColor.BLUE));
        qm.getBalls().add(gof.createBall(new P2d(175, 50), new V2d(0, 0), BallColor.GREEN));
        qm.getBalls().add(gof.createBall(new P2d(200, 50), new V2d(0, 0), BallColor.GREEN));
        qm.getBalls().add(gof.createBall(new P2d(202, 50), new V2d(0, 0), BallColor.GREEN));
        qm.getBalls().add(gof.createBall(new P2d(225, 50), new V2d(0, 0), BallColor.GREEN));
        qm.getBalls().add(gof.createBall(new P2d(225, 60), new V2d(0, 0), BallColor.YELLOW));
        qm.getBalls().add(gof.createBall(new P2d(225, 75), new V2d(0, 0), BallColor.YELLOW));
        qm.getBalls().add(gof.createBall(new P2d(225, 100), new V2d(0, 0), BallColor.RED));
        qm.getBalls().add(gof.createBall(new P2d(225, 125), new V2d(0, 0), BallColor.RED));
        qm.getBalls().add(gof.createBall(new P2d(250, 125), new V2d(0, 0), BallColor.RED));

        List<Ball> outputList = new ArrayList<>(qm.getBalls());
        outputList.remove(10);
        outputList.remove(9);
        outputList.remove(4);
        outputList.remove(0);

        // CHECKSTYLE: MagicNumber ON
        
        var founded = qm.checkCloseByThree();
        assertTrue(outputList.equals(founded));
    }

    @Test
    void testShiftBalls() {
        var qm = new QueueManager(0, 1, "levels/1/Path.xml");
        var gof = GameObjectsFactory.getInstance();
        List<Ball> outputList = new ArrayList<>();

        // CHECKSTYLE: MagicNumber OFF
        /*
         * it would be redundant and useless use constants
         * to indicates those arbitraty "magic numbers"
         */
        qm.getBalls().add(gof.createBall(new P2d(788, 38), new V2d(0, 0), BallColor.GREEN));
        qm.getBalls().add(gof.createBall(new P2d(763, 38), new V2d(0, 0), BallColor.RED));
        qm.getBalls().add(gof.createBall(new P2d(738, 38), new V2d(0, 0), BallColor.RED));

        outputList.add(gof.createBall(new P2d(787, 38), new V2d(0, 0), BallColor.GREEN));
        outputList.add(gof.createBall(new P2d(762, 38), new V2d(0, 0), BallColor.RED));
        outputList.add(gof.createBall(new P2d(737, 38), new V2d(0, 0), BallColor.RED));

        qm.shiftBalls(0);

        // CHECKSTYLE: MagicNumber ON

        assertTrue(outputList.equals(qm.getBalls()));
    }
}
