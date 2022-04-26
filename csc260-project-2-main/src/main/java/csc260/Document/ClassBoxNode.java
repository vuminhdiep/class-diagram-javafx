package csc260.Document;

import csc260.View.NodeViewer;

import javafx.geometry.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.lang.Math;

public class ClassBoxNode implements Node, Serializable {

    //The NodeViewer Object attached to ClassBoxNode
    transient NodeViewer view;


    //Title for the class
    private String header = null;
    // List of Methods
    private ArrayList<Method> methods = null;
    //List of attributes
    private ArrayList<String> attributes = null;

    ArrayList<Object> content = new ArrayList<Object>();


    public void initializeNode(){
        this.view = new NodeViewer(this);

    }

    @Override
    public void setEdges(Point2D point) {
        for (Edge e: start){
            e.setCoord(0, point);
        }
        for (Edge e: end){
            e.setCoord(3, point);
        }
    }

    //Width for the node
    private double width;
    //Height for the node
    private double height;
    //Upper left Coordinate that helps us find the other three coordinates
    // of the box using the height and width.
    private Point2D upperLeftCoord;

    //The type information used by the viewer to draw the classbox
    private int type = NodesConfig.CLASSBOX;

    private ArrayList<Edge> end = new ArrayList<>();
    private ArrayList<Edge> start = new ArrayList<>();


    public void addEnd(Edge e) {
        this.end.add(e);
    }

    public void addStart(Edge e) {
        this.start.add(e);
    }

    /**
     * Constructor when we know the upper left coordinate.
     * @param header title for the class
     * @param coord the upper left coordinate for the box
     */
    public  ClassBoxNode(String header, Point2D coord){
        this.header = header;
        this.view = new NodeViewer(this);
        this.upperLeftCoord = coord;
        this.height = NodesConfig.STANDARDHEIGHT;
        this.width = NodesConfig.STANDARDWIDTH;
        content.add(header);
        content.add(attributes);
        content.add(methods);
    }

    /**
     * Default constructor with only the Header information
     * @param header title for the class
     */
    public  ClassBoxNode(String header){
        this.header = header;
        this.view = new NodeViewer(this);
        this.upperLeftCoord = NodesConfig.STANDARDCOORD;
        this.height = NodesConfig.STANDARDHEIGHT;
        this.width = NodesConfig.STANDARDWIDTH;
        content.add(header);
        content.add(attributes);
        content.add(methods);
    }

    public void setUpperLeftCoord(Point2D point){
        upperLeftCoord = point;
    }


    //setters

    /**
     * Given the coordinates, sets the new Upper Left Coordinate.
     * @param x x-value of the upper left point
     * @param y v-value of the upper left point
     */
    public void setUpperLeftCoord(double x, double y){
        upperLeftCoord = new Point2D(x, y);
    }

    /**
     * Sets the width to the new width.
     * @param w
     */
    public void setWidth(double w){
         width = Math.abs(w);
    }

    /**
     * Sets the height to the new height.
     * @param h
     */
    public void setHeight(double h){
        height = Math.abs(h);
    }

    /**
     * Assigns the title as the header
     * in the internal rep
     * @param head
     */

    public void setHeader(String head){
        header = head;
        content.remove(0);
        content.add(0, head);
        System.out.println(content.toString());
    }

    /**
     * adds the String InstVar
     * to the attributes list.
     * In this case if the position
     * is not passed, it's added at the end.
     * @param var
     */
    public void addInstVar(String var){
        if(attributes == null){
            attributes = new ArrayList<String>();
            attributes.add(var);
        }else {
            attributes.add(var);
        }
        content.remove(1);
        content.add(1, attributes);
    }

    /**
     * adds the String InstVar
     * to the attributes list.
     * In this case in the given position
     *
     * @param pos
     * @param var
     */

    public void addInstVar(int pos, String var){
        if(attributes == null){
            attributes = new ArrayList<String>();
            attributes.add(var);
        }else {
            attributes.add(pos, var);
        }
        content.remove(1);
        content.add(1, attributes);
    }

    /**
     * Adds the method to the list of methods.
     * @param m instance of method class to be
     *        added to the node class.
     */

    public void addMethod(Method m){
        if (methods==null){
            methods = new ArrayList<Method>();
        }
        methods.add(m);
        content.remove(2);
        content.add(2, methods);
    }

    /**
     *  Adds the method to the list of methods.
     * @param m instance of method class to be
     *        added to the node class.
     * @param pos int indicating the position at
     *            which to add the method
     */
    public void addMethod(Method m, int pos){
        methods.add(pos, m);
        content.remove(2);
        content.add(2, methods);
    }

    //getters

    /**
     * Returns the type of the node
     * @return int corresponding to type of the Node
     */
    public int getType(){ return type;}
    public double getHeight(){
        return height;
    }

    /**
     * Returns the width of the node
     * @return width of the node
     */
    public double getWidth(){
        return width;
    }

    /**
     * Returns the upper corner of the box
     * @return Point denoting the upper left corner of the box
     */
    public Point2D getUpperLeftCoord(){
        return upperLeftCoord;
    }
    public String getHeader(){return header;}

    /**
     * Returns the x value of the upper left coordinate.
     * @return
     */
    @Override
    public double getX() {
        return upperLeftCoord.getX();
    }

    /**
     * Returns the y value of the upper left coordinate.
     * @return type double indicating the y value of the upper left coordinate
     */
    @Override
    public double getY() {
        return upperLeftCoord.getY();
    }

    /**
     * Returns the coordinate from which to draw
     * the outgoing edges for the node.
     * @return Point that is half way in
     * the centre of the right edge of the node
     */

    @Override
    public Point2D getIncomingEdgeCoord(){
        double newX = getX();
        double newY = getY() + height/2;
        Point2D toReturn = new Point2D(newX, newY);
        return toReturn;
    }

    /**
     * Returns the Coordinate for the incoming
     * edges for a node.
     * @return Point which is the centre of
     * the left edge.
     */

    @Override
    public Point2D getOutgoingEdgeCoord(){
        double newX = getX() + width;
        double newY = getY() + height/2;
        Point2D toReturn = new Point2D(newX, newY);
        return toReturn;
    }

    /**
     * Returns list of methods added to the class box.
     * @return
     */

    public ArrayList<Method> getMethods(){return methods;}

    /**
     * Removes the current title and puts the header as null.
     */
    public void clearHead(){
        header = null;
    }

    /**
     * Clears the entire body of the class box node by
     * setting all components to null.
     */
    public void clearBody(){
        methods = null;
        attributes = null;
        content.remove(1);
        content.add(1, attributes);
        content.remove(2);
        content.add(2, methods);
    }

    /**
     * Checks if the box is empty by looking at if the
     * header and the methods and the attributes are empty.
     * @return
     */


    public boolean isEmpty(){
        if(header == null && methods == null && attributes == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Adds the viewer instance to the Node.
     * @param v
     */


    public void addViewer(NodeViewer v)
    {
        view = v;
    }

    /**
     * Removes the viewer instance from the Node,
     * @param v
     */
    public  void removeViewer(NodeViewer v)
    {
        view = null;
    }

    /**
     * Calls the update method on the viewer that allows the viewer
     * to draw the canvas.
     */

    public void update()
    {
        view.update();
    }

    /**
     * Gives us the list of all instance variables
     * for the given node.
     * @return list of variables
     */

    public ArrayList<String> getAttributes() {
        return attributes;
    }

    public ArrayList<Object> getContent(){
        return content;
    }

    public void addType(int type){
        this.type = type;
    }

}
