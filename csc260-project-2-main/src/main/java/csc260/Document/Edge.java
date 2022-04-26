package csc260.Document;

import javafx.geometry.Point2D;

/**
 * An Edge interface to represent an edge in a class diagram
 +getListener(): int
 +setState(): void
 +update()
 +addViewer(Viewer)
 +removeViewer(Viewer)
 */


public interface Edge{
    public double getLength();

    public void setCoord(int coordNumber, Point2D newCoord);

    public int getType();

    public String getAnnotation();

    public void updateFrom(Node newFrom);

    public void updateTo(Node newTo);

    public Iterable<Point2D> getCoords();

    public Point2D getFrom();

    public Point2D getTo();

    public void update();


    public Node getSource();

    public Node getTarget();

}

