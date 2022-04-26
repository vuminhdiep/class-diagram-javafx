package csc260.Document;

import csc260.View.NodeViewer;

import java.awt.*;
import javafx.geometry.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

public class Interface implements Node, Serializable {
    transient NodeViewer view;
    private String header = null;
    private ArrayList<Method> methods = null;
    private double width;
    private double height;
    private Point2D upperLeftCoord;
    public static int type = NodesConfig.INTERFACE;
    ArrayList<Object> content = new ArrayList<Object>();

    public void initializeNode(){
        this.view = new NodeViewer(this);

    }

    public void setEdges(Point2D point) {
        for (Edge e: start){
            e.setCoord(0, point);
        }
        for (Edge e: end){
            e.setCoord(3, point);
        }
    }

    public Interface(String header, Point2D coord){
        this.header = header;
        this.view = new NodeViewer(this);
        this.upperLeftCoord = coord;
        this.height = NodesConfig.STANDARDHEIGHT;
        this.width = NodesConfig.STANDARDWIDTH;
        content.add(header);

    }

    public Interface(String header){
        this.header = header;
        this.view = new NodeViewer(this);
        this.upperLeftCoord = NodesConfig.STANDARDCOORD;
        this.height = NodesConfig.STANDARDHEIGHT;
        this.width = NodesConfig.STANDARDWIDTH;
        content.add(header);
    }

    private ArrayList<Edge> end = new ArrayList<>();
    private ArrayList<Edge> start = new ArrayList<>();


    public void addEnd(Edge e) {
        this.end.add(e);
    }

    public void addStart(Edge e) {
        this.start.add(e);
    }

    //setters
    public void setUpperLeftCoord(double x, double y){
        upperLeftCoord = new Point2D(x, y);
    }

    public void setUpperLeftCoord(Point2D newCord){
        upperLeftCoord = newCord;
    }
    public void setWidth(double w){
         width = Math.abs(w);
    }
    public void setHeight(double h){
        height = Math.abs(h);
    }
    public void setHeader(String head){
        header = head;
        content.remove(0);
        content.add(0, header);
    }
    //getters
    public int getType(){ return type;}
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
    public Point2D getUpperLeftCoord(){
        return upperLeftCoord;
    }
    public void addMethod(Method m){
        if (methods==null){
            methods = new ArrayList<Method>();
        }
        methods.add(m);
    }
    public void addMethod(Method m, int pos){
        methods.add(pos, m);
    }
    public void clearHead(){
        header = null;
    }

    public void clearBody(){
        methods = null;
    }

    public boolean isEmpty(){
        if(header == null && methods == null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public double getX() {
        return upperLeftCoord.getX();
    }

    @Override
    public double getY() {
        return upperLeftCoord.getY();
    }

    @Override
    public Point2D getOutgoingEdgeCoord() {
        double newX = getX() + width;
        double newY = getY() + height/2;
        Point2D toReturn = new Point2D(newX, newY);
        return toReturn;
    }

    @Override
    public Point2D getIncomingEdgeCoord(){
        double newX = getX();
        double newY = getY() + height/2;
        Point2D toReturn = new Point2D(newX, newY);
        return toReturn;
    }

    public String getHeader(){
        return header;
    }
    public int getNumMethods(){
        if(methods == null){
            return 0;}
        return methods.size();}

    protected void addViewer(NodeViewer v)
    {
        view = v;
    }
    protected void removeViewer(NodeViewer v)
    {
        view = null;
    }

    public void update()
    {
        view.update();
    }

    public ArrayList<Object> getContent(){
        return content;
    }

}
