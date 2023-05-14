package it.unibo.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class PathTest {

    @Test
    void testPathBuilder() {
        try {
            boolean loop = true;
            var path = new Path.PathBuilder().build();
            var first = path.getFirst();
            //var first = new P2d(300.0, 190.0);
            while (loop==true) {
                var dir = path.getMove(first);

                switch (dir) {
                    case UP:
                        first = new P2d(first.x, first.y - 1);
                        break;

                    case DOWN:
                        first = new P2d(first.x, first.y + 1);
                        break;

                    case LEFT:
                        first = new P2d(first.x - 1, first.y);
                        break;

                    case RIGHT:
                        first = new P2d(first.x + 1, first.y);
                        break;
                }
                System.out.println(first.toString());
                if(first==new P2d(300.0, 200.0)){
                    loop=false;
                }
            }
        } catch (ParserConfigurationException e) {
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

}
