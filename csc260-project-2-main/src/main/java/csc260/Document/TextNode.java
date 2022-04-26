package csc260.Document;

import csc260.View.NodeViewer;
import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.ArrayList;

public class TextNode implements Node, Serializable {
    private ArrayList<String> text = null;
    private int type = NodesConfig.NOTE ;
    private double width;
    private double height;
    private Point2D upperLeftCoord;
    transient NodeViewer view;
    ArrayList<Object> content = new ArrayList<Object>();

    public TextNode(){
          this.view = new NodeViewer(this);
          this.width = NodesConfig.STANDARDWIDTH;
          this.height = NodesConfig.STANDARDHEIGHT;
          this.upperLeftCoord = NodesConfig.STANDARDCOORD;
          content.add(text);
    }

    public void initializeNode(){
        this.view = new NodeViewer(this);

    }

    public TextNode(String text){
        this.view = new NodeViewer(this);
        this.width = NodesConfig.STANDARDWIDTH;
        this.height = NodesConfig.STANDARDHEIGHT;
        this.text = new ArrayList<String>();
        this.text.add(text);
        this.upperLeftCoord = NodesConfig.STANDARDCOORD;
        content.add(text);
    }
    public TextNode(String text, Point2D coord){
        this.view = new NodeViewer(this);
        this.width = NodesConfig.STANDARDWIDTH;
        this.height = NodesConfig.STANDARDHEIGHT;
        this.text = new ArrayList<String>();
        this.text.add(text);
        this.upperLeftCoord = coord;
        content.add(text);
    }

    //setters
    public void setUpperLeftCoord(double x, double y){
        upperLeftCoord = new Point2D(x, y);
    }
    public void setWidth(double w){
         width = Math.abs(w);
    }
    public void setHeight(double h){
        height = Math.abs(h);
    }

    public void setUpperLeftCoord(Point2D newCord){
        upperLeftCoord = newCord;
    }

    private ArrayList<Edge> end = new ArrayList<>();
    private ArrayList<Edge> start = new ArrayList<>();

    public void setEdges(Point2D point) {
        for (Edge e: start){
            e.setCoord(0, point);
        }
        for (Edge e: end){
            e.setCoord(3, point);
        }
    }


    public void addEnd(Edge e) {
        this.end.add(e);
    }

    public void addStart(Edge e) {
        this.start.add(e);
    }


    //getters
    public double getHeight(){
        return height;
    }
    public double getWidth(){
        return width;
    }
    public int getType(){
        return type;
    }

    public Point2D getUpperLeftCoord(){
        return upperLeftCoord;
    }

    public boolean isEmpty(){
        return text==null;
    }
    public void addViewer(NodeViewer v)
    {
        view = v;
    }
    public void removeViewer(NodeViewer v)
    {
        view = null;
    }

    public void update()
    {
        view.update();
    }

    public double getX(){
        return upperLeftCoord.getX();
    }

    public double getY(){
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
    public Point2D getIncomingEdgeCoord() {
        double newX = getX();
        double newY = getY() + height/2;
        Point2D toReturn = new Point2D(newX, newY);
        return toReturn;
    }

    public ArrayList<Object> getContent(){
        return content;
    }
}
