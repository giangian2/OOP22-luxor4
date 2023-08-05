package it.unibo.utils.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.unibo.enums.Direction;
import it.unibo.utils.P2d;
import it.unibo.utils.api.Path;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * This class is the representation of a path with straight roads,
 * consequently only having the vertexes that connect the pieces of the path .
 */
public final class XmlPath implements Path{
    private final List<P2d> points;
    /*
     * This EPSILON represents the maximum approximation
     * for a double precision operation.
     */
    private static final double EPSILON = 1e-6;

    /**
     * Private constructor for a path.
     * 
     * @param builder Path builder.
     */
    private XmlPath(final XmlPathBuilder builder) {
        this.points = builder.points;
    }

    /**
     * Get all the vertex of the path.
     * 
     * @return A list of vertexes
     */
    public List<P2d> getPositions() {
        return new ArrayList<>(this.points);
    }

    /**
     * Get the first vertex of the path.
     * 
     * @return The first vertex.
     */
    public P2d getFirst() {
        return this.points.get(0);
    }

    /**
     * Get the last vertex of the path.
     * 
     * @return The last vertex.
     */
    public P2d getLast() {
        return this.points.get(points.size() - 1);
    }

    /**
     * Given a position, it returns the direction where you have
     * to move in order to going forward in the parh.
     * 
     * @param position The position to check.
     * @return The direction to take.
     */
    public Direction getMove(final P2d position) {
        P2d nextCorner = null;
        if (points.contains(position) && points.size() > (points.indexOf(position) + 1)) {
            nextCorner = points.get(points.indexOf(position) + 1);
        } else {
            for (int i = 0; i < points.size() - 1; i++) {
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
    public static class XmlPathBuilder {
        private List<P2d> points;

        /**
         * Constructor for a PathBuilder.
         * 
         * @param xmlPathSrc source path of the xml file containing vertex.
         */
        public XmlPathBuilder(final String xmlPathSrc) {
            try {
                final InputStream in = Objects.requireNonNull(
                        ClassLoader.getSystemResourceAsStream(xmlPathSrc));

                final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                final Document doc = dBuilder.parse(in);
                final NodeList nList = doc.getElementsByTagName("P2d");

                this.points = new ArrayList<>();

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    final Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        final Element eElement = (Element) nNode;
                        final int x = Integer.parseInt(eElement
                                .getElementsByTagName("x")
                                .item(0)
                                .getTextContent());
                        final int y = Integer.parseInt(eElement
                                .getElementsByTagName("y")
                                .item(0)
                                .getTextContent());
                        this.points.add(new P2d(x, y));
                    }
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                Logger.getGlobal().log(Level.WARNING, null, e);
            }
        }

        /**
         * Builds a path.
         * 
         * @return Path builded.
         */
        public XmlPath build() {
            return new XmlPath(this);
        }
    }
}
