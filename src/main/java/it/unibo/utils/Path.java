package it.unibo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.unibo.enums.Direction;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Path {
    private List<P2d> points;

    /**
     * Proprs + methods
     */
    private Path(PathBuilder builder) {
        this.points = builder.points;
    }

    public Iterable<P2d> getPositions() {
        return this.points;
    }

    public P2d getFirst() {
        return this.points.get(0);
    }

    public P2d getLast() {
        return this.points.get(points.size()-1);
    }

    public Direction getMove(P2d position) {
        P2d nextCorner = null;
        if (points.contains(position)) {
            nextCorner = points.get(points.indexOf(position) + 1);
        } else {
            for (int i = 0; i < points.size() - 1; i++) {
                if (position.isBetween(points.get(i), points.get(i + 1))) {
                    nextCorner = points.get(i + 1);
                }
            }
        }

        if(nextCorner == null){
            System.out.println("!!!NULL!!!");
        }

        if (position.x == nextCorner.x && position.y > nextCorner.y) {
            return Direction.UP;
        }
        if (position.x == nextCorner.x && position.y < nextCorner.y) {
            return Direction.DOWN;
        }
        if (position.y == nextCorner.y && position.x > nextCorner.x) {
            
            return Direction.LEFT;
        }
        if (position.y == nextCorner.y && position.x < nextCorner.x) {
            return Direction.RIGHT;
        }

        return Direction.DOWN;
    }

    public static class PathBuilder {
        private static final String ROOT = "levels/1/";
        private List<P2d> points;

        public PathBuilder() throws ParserConfigurationException, SAXException, IOException {

            final InputStream in = Objects.requireNonNull(
                    ClassLoader.getSystemResourceAsStream(ROOT + "Path.xml"));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(in);
            NodeList nList = doc.getElementsByTagName("P2d");

            this.points = new ArrayList<P2d>();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

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
        }

        public Path build() {
            return new Path(this);
        }
    }
}
