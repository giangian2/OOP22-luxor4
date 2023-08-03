package it.unibo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.unibo.enums.Direction;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * This class is the representation of a path with straight roads, 
 * consequently only having the vertexes that connect the pieces of the path .
 */
public final class Path {
    private List<P2d> points;
    /*
     * This EPSILON represents the maximum approximation 
     * for a double precision operation.
     */
    private static final double EPSILON = 1e-6;

    /**
     * Private constructor for a path.
     * @param builder Path builder.
     */
    private Path(final PathBuilder builder) {
        this.points = builder.points;
    }

    /**
     * Get all the vertex of the path.
     * @return A list of vertexes
     */
    public List<P2d> getPositions() {
        return new ArrayList<>(this.points);
    }

    /**
     * Get the first vertex of the path.
     * @return The first vertex.
     */
    public P2d getFirst() {
        return this.points.get(0);
    }
    /**
     * Get the last vertex of the path.
     * @return The last vertex.
     */
    public P2d getLast() {
        return this.points.get(points.size() - 1);
    }

    /**
     * Given a position, it returns the direction where you have
     * to move in order to going forward in the parh.
     * @param position The position to check.
     * @return The direction to take.
     */
    public Direction getMove(final P2d position) {
        P2d nextCorner = null;
        if (points.contains(position) && (points.size() > (points.indexOf(position) + 1))) {
            nextCorner = points.get(points.indexOf(position) + 1);
        } else {
            for (int i = 0; (i < points.size() - 1); i++) {
                if (position.isBetween(points.get(i), points.get(i + 1))) {
                    nextCorner = points.get(i + 1);
                }
            }
        }
        if (nextCorner != null) {

            if (Math.abs(position.getX() - nextCorner.getX()) < EPSILON && position.getY() > nextCorner.getY()) {
                return Direction.UP;
            }
            if (Math.abs(position.getX() - nextCorner.getX()) < EPSILON && position.getY() < nextCorner.getY()) {
                return Direction.DOWN;
            }
            if (Math.abs(position.getY() - nextCorner.getY()) < EPSILON && position.getX() > nextCorner.getX()) {

                return Direction.LEFT;
            }
            if (Math.abs(position.getY() - nextCorner.getY()) < EPSILON && position.getX() < nextCorner.getX()) {
                return Direction.RIGHT;
            }
        }

        return Direction.NONE;
    }

    /**
     * Class that represents a builder for build a Path object.
     */
    public static class PathBuilder {
        private List<P2d> points;

        /**
         * Constructor for a PathBuilder.
         * @param xmlPathSrc source path of the xml file containing vertex.
         */
        public PathBuilder(final String xmlPathSrc) {
            try {
                final InputStream in = Objects.requireNonNull(
                        ClassLoader.getSystemResourceAsStream(xmlPathSrc));

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                Document doc = dBuilder.parse(in);
                NodeList nList = doc.getElementsByTagName("P2d");

                this.points = new ArrayList<P2d>();

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        int x = Integer.parseInt(eElement
                                .getElementsByTagName("x")
                                .item(0)
                                .getTextContent());
                        int y = Integer.parseInt(eElement
                                .getElementsByTagName("y")
                                .item(0)
                                .getTextContent());
                        this.points.add(new P2d(x, y));
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

        /**
         * Builds a path.
         * @return Path builded.
         */
        public Path build() {
            return new Path(this);
        }
    }
}
