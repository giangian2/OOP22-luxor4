package it.unibo.utils;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<P2d> getPositions() {
        return this.points;
    }

    public P2d getFirst() {
        return this.points.get(0);
    }

    public P2d getLast() {
        return this.points.get(points.size() - 1);
    }

    public Direction getMove(P2d position) {
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

            double epsilon = 1e-6;

            if (Math.abs(position.x - nextCorner.x) < epsilon && position.y > nextCorner.y) {
                return Direction.UP;
            }
            if (Math.abs(position.x - nextCorner.x) < epsilon && position.y < nextCorner.y) {
                return Direction.DOWN;
            }
            if (Math.abs(position.y - nextCorner.y) < epsilon && position.x > nextCorner.x) {

                return Direction.LEFT;
            }
            if (Math.abs(position.y - nextCorner.y) < epsilon && position.x < nextCorner.x) {
                return Direction.RIGHT;
            }
        }

        return Direction.NONE;
    }

    public static class PathBuilder {
        private List<P2d> points;

        public PathBuilder(String xmlPathSrc) {
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

        public Path build() {
            return new Path(this);
        }
    }
}
