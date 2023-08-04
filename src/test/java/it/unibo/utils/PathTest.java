package it.unibo.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/**
 * 
 * class to test the correct functioning of the path builder.
 */
class PathTest {

    /**
     * Verify the correct functioning of the builder and ensure that there are no
     * errors during the entire movement path.
     */
    @Test
    void testPathBuilder() {

        assertDoesNotThrow(() -> {
            final var path = new Path.PathBuilder("levels/1/Path.xml").build();
            var first = path.getFirst();
            final var end = path.getLast();

            while (!first.equals(end)) {

                final var dir = path.getMove(first);

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
