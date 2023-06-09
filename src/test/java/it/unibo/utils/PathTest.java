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

        var path = new Path.PathBuilder().build();
        var ietrable = path.getPositions();
        System.out.println(ietrable);

        var first = path.getFirst();
        var end = path.getLast();

        while (!first.equals(end)) {

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
        }

    }

}
