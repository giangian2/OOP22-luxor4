package it.unibo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.net.URL;
import java.util.LinkedList;
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

    public Direction getMove(P2d position) {
        return Direction.DOWN;
    }

    public static class PathBuilder {
        private static final String ROOT = "levels/1/";
        private List<P2d> points;

        public PathBuilder() throws ParserConfigurationException, SAXException, IOException {
            this.points = new LinkedList<P2d>();

            final InputStream in = Objects.requireNonNull(
                    ClassLoader.getSystemResourceAsStream(ROOT + "Path.xml"));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(in);
            NodeList nList = doc.getElementsByTagName("P2d");

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
