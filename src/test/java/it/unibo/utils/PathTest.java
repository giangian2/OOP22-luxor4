package it.unibo.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class PathTest {

    @Test
    void testPathBuilder() {

        assertDoesNotThrow(() -> {
            var path = new Path.PathBuilder("levels/1/Path.xml").build();
            var ietrable = path.getPositions();
            System.out.println(ietrable);

            var first = path.getFirst();
            var end = path.getLast();

            while (!first.equals(end)) {

                var dir = path.getMove(first);

                switch (dir) {
                    case UP:
                        first = new P2d(first.getX(), first.getY() - 1);
                        break;

                    case DOWN:
                        first = new P2d(first.getX(), first.getY() + 1);
                        break;

                    case LEFT:
                        first = new P2d(first.getX() - 1, first.getY());
                        break;

                    case RIGHT:
                        first = new P2d(first.getX() + 1, first.getY());
                        break;
                    default:
                        break;
                }

            }
        });

    }

}
