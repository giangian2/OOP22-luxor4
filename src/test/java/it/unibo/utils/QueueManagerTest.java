package it.unibo.utils;


import java.util.ArrayList;
import java.util.List;
import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.model.GameObject;
import it.unibo.utils.QueueManager;

import org.junit.jupiter.api.Test;

public class QueueManagerTest {
    @Test

    void testCloseByThree(){
        List<Ball> inputList = new ArrayList<>();
        List<Ball> outputList = new ArrayList<>();
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.BLUE, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.YELLOW, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.YELLOW, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(GameObject.Type.BALL,null, BallColor.RED, null, null, null, null));

        outputList=QueueManager.getCloseByThree(inputList);
        //outputList.forEach(i->System.out.println(i.getColor()));
    }
}
