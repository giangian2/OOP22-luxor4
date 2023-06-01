package it.unibo.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import it.unibo.enums.Direction;
import it.unibo.model.Ball;

public class QueueManager {

    private Path path;

    public QueueManager() {
        try{
            path = new Path.PathBuilder().build();

        }catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Direction getMove(Ball ball){
        return path.getMove(ball.getCurrentPos());
    }


    public static List<Ball> getCloseByThree(List<Ball> ballList){
        List<Ball> returnList = new ArrayList<Ball>();

        for(int i = 0; i < ballList.size() - 2;){
            var currentColor = ballList.get(i).getColor();
            int count = 1;
            for(int j = 1;(i+j < ballList.size())&&(ballList.get(i+j).getColor()==currentColor); j++){
                    count++;
            }
            if(count>2){
                for(int j=0;j<count;j++){
                    returnList.add(ballList.get(i+j));
                }
            }
            i+=count;
        }

        return returnList;
    }
}
