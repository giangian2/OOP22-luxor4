package it.unibo.utils.api;

import java.util.List;

import it.unibo.enums.Direction;
import it.unibo.utils.P2d;

/**
 * This interface is the representation of a path with straight roads,
 * consequently only having the vertexes that connect the pieces of the path.
 */
public interface Path {
    /**
     * Get all the vertex of the path.
     * 
     * @return A list of vertexes
     */
    List<P2d> getPositions();

    /**
     * Get the first vertex of the path.
     * 
     * @return The first vertex.
     */
    P2d getFirst();

    /**
     * Get the last vertex of the path.
     * 
     * @return The last vertex.
     */
    P2d getLast();

    /**
     * Given a position, it returns the direction where you have
     * to move in order to going forward in the parh.
     * 
     * @param position The position to check.
     * @return The direction to take.
     */
    Direction getMove(P2d position);

}
