package csc260.Document;


import csc260.View.EdgeViewer;
import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.ArrayList;


/**
 A directed edge with annotation between two nodes. An
 undirected edge between node u and node v can be simulated by two edges: (u,
 v) and (v, u).
 +type : string
 +annotation (string)
 +from: node
 +to: node
 +coordinates (list of coords)
 **/
public class AnnotatedEdge implements Edge, Serializable {

    public int type;
    private ArrayList<Point2D> coordinates;

    private String annotation;
    private Point2D p0, p1, p2, p3;

    transient EdgeViewer view;
    private Node from;
    private Node to;




    /**
     * Create an edge between a node from and a node to. The order of the coordinates matter as (x0, y0) the first and (x3, y3) the last
     **/
    public AnnotatedEdge(Node from, Node to, int newType) {

        annotation = "";
        coordinates = new ArrayList<Point2D>();
        type = newType;
        this.from = from;
        this.to = to;

        p0 = new Point2D(from.getOutgoingEdgeCoord().getX(), to.getOutgoingEdgeCoord().getY());
        p3 = new Point2D(to.getIncomingEdgeCoord().getX(), to.getIncomingEdgeCoord().getY());
        double dist = p0.distance(p3);
        p1 = new Point2D(from.getOutgoingEdgeCoord().getX() + dist/3, from.getOutgoingEdgeCoord().getY() + dist/3);
        p2 = new Point2D(to.getIncomingEdgeCoord().getX() - dist/3, to.getIncomingEdgeCoord().getY() - dist/3);


        coordinates.add(p0);
        coordinates.add(p1);
        coordinates.add(p2);
        coordinates.add(p3);


        view = new EdgeViewer(this);


    }


    public AnnotatedEdge(){
        type = MappingEdges.CONTAINMENT;
        annotation = "";
        coordinates = new ArrayList<Point2D>();

        this.from = null;
        this.to = null;

        p0 = new Point2D(0, 0);
        p1 = new Point2D(0, 0);
        p2 = new Point2D(0, 0);
        p3 = new Point2D(0, 0);

        coordinates.add(p0);
        coordinates.add(p1);
        coordinates.add(p2);
        coordinates.add(p3);


        view = new EdgeViewer(this);


    }


    /**
     * Get the length of the first and last coordinates
     **/
    public double getLength() {
        return coordinates.get(0).distance(coordinates.get(coordinates.size()-1));
    }

    public void setCoord(int coordNumber, Point2D newCoord) {
        if(coordinates.size() > Math.abs(coordNumber)){
            coordinates.set(coordNumber, newCoord);
        }

    }

    public int getType() {
        return type;

    }


    public void setType(int newType){
        if(newType != MappingEdges.CONTAINMENT && newType != MappingEdges.INHERITANCE && newType != MappingEdges.DELEGATION){
            type = MappingEdges.CONTAINMENT;
        } else {
            type = newType;
        }
    }

    public void setAnnotation(String newAnnotation){
        annotation = newAnnotation;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void updateFrom(Node newFrom) {
        if(from!= null){
            from = newFrom;
            setCoord(0,newFrom.getOutgoingEdgeCoord());
        }


    }

    public void updateTo(Node newTo) {
        if(to != null) {
            to = newTo;
            setCoord(coordinates.size() - 1,newTo.getIncomingEdgeCoord());
        }

    }

    public ArrayList<Point2D> getCoords() {
        return coordinates;
    }

    public Point2D getFrom() {
        return coordinates.get(0);
    }

    public Point2D getTo() {
        return coordinates.get(coordinates.size() - 1);

    }

    public Node getSource(){
        return from;
    }

    public Node getTarget(){
        return to;
    }


    public void addViewer(EdgeViewer v)
    {
        view = v;
    }
    public void removeViewer(EdgeViewer v)
    {
        view = null;
    }

    public void update()
    {
        view.update();
    }



}
