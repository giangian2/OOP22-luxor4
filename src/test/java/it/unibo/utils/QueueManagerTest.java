import it.unibo.enums.BallColor;
import it.unibo.model.Ball;
import it.unibo.utils.QueueManager;

public class QueueManagerTest {
    @Test

    void testCloseByThree(){
        List<Ball> inputList = new ArrayList<>();
        List<Ball> outputList = new ArrayList<>();
        inputList.add(new Ball(null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(null, BallColor.RED, null, null, null, null));
        inputList.add(new Ball(null, BallColor.BLUE, null, null, null, null));
        inputList.add(new Ball(null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(null, BallColor.GREEN, null, null, null, null));
        inputList.add(new Ball(null, BallColor.YELLOW, null, null, null, null));

        outputList=QueueManager.getCloseByThree(inputList);
        System.out.println(outputList);
    }
}
