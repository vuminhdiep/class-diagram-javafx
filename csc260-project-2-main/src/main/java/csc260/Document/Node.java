package csc260.Document;

import java.awt.*;
import javafx.geometry.Point2D;
import java.util.ArrayList;

/**
 * A Node interface to represent a single box in a class diagram
 */
public interface Node {
    /**
     * adds the content to the Node
     */
    public int getType();

    public void addEnd(Edge e);

    public void addStart(Edge e);

    public double getHeight();

    public void setEdges(Point2D point);

    public double getWidth();

    public void setHeight(double h);

    public void setWidth(double w);

    public boolean isEmpty();

    public double getX();

    public double getY();

    public void initializeNode();

    public void setUpperLeftCoord(Point2D point);

    public void setUpperLeftCoord(double x, double y);


    public Point2D getOutgoingEdgeCoord();

    public Point2D getIncomingEdgeCoord();

    public Point2D getUpperLeftCoord();

    public void update();

    public ArrayList<Object> getContent();
}
