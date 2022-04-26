package csc260.Document;


import csc260.Controller.CareTaker;
import csc260.Controller.Originator;
import csc260.View.Viewer;
import csc260.View.WhiteCanvasViewer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedMultigraph;

import javafx.geometry.Point2D;

import java.io.*;

public class WhiteCanvasModel {


    @FXML
    private Canvas canvas;

    int currentInternal;
    int stateNum;
    CareTaker careTaker = new CareTaker();
    Originator originator = new Originator();

    private Graph<Node, Edge> internal;
    private WhiteCanvasViewer viewer;
    private int width;
    private int height;

    private int num = 0 ;

    public WhiteCanvasModel(int width, int height){
        viewer = null;
        internal =  new DirectedMultigraph<Node,Edge>(null, null, false);
        this.width = width;
        this.height = height;

        //Memento pattern
        currentInternal = 0;
        stateNum = 1;
        originator.set(clone(internal));
        careTaker.addMemento(originator.storeInMemento());
    }

    public Iterable<Node> getClasses(){
        return internal.vertexSet();
    }

    public Iterable<Edge> getEdgeClasses(){ return internal.edgeSet();}

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void changeDimVert(int newH){
        height = newH;
    }

    public void changeDimHor(int newW){
        width = newW;
    }

    public void addClass(Node V){
        internal = clone(internal);
        internal.addVertex(V);
        originator.set(clone(internal));
        currentInternal++;
        stateNum++;
        System.out.println("There should be " + stateNum);
        careTaker.clearFromIndex(currentInternal);
        careTaker.addMemento(originator.storeInMemento());
        careTaker.print();
        stateNum = careTaker.size();
        System.out.println("Actual " + stateNum);

    }

    public void save(File file)  {
        FileOutputStream saveFile;
        try {
            saveFile = new FileOutputStream(file.getAbsolutePath());
            ObjectOutputStream save = new ObjectOutputStream (saveFile);
            save.writeObject(internal);
            save.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(File file){
        try {
            FileInputStream saveFile = new FileInputStream(file.getAbsolutePath());
            ObjectInputStream restore = new ObjectInputStream(saveFile);
//            Object obj = restore.readObject();
            Graph<Node, Edge> graph = (Graph<Node, Edge>) ((restore.readObject()));
            Graph<Node,Edge> newInternal = new DirectedMultigraph<>(null, null, false);
            for (Node N : graph.vertexSet()) {
                N.initializeNode();
                newInternal.addVertex(N);
            }

            for (Edge E : graph.edgeSet()) {
                newInternal.addEdge(E.getSource(), E.getTarget(), E);
            }
            this.internal = newInternal;
            this.notifyViewers();

        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void addArrow(Node from, Node to, Edge edge){
        from.addStart(edge);
        to.addEnd(edge);
        internal.addEdge(from, to , edge);
        System.out.println("added edge");
    }

    public void removeClass(Node V){
        internal.removeVertex(V);
    }

    public void removeArrow(Edge E){
        internal.removeEdge(E);
    }

    public void notifyViewers(){
        viewer.update();
    }

    public WhiteCanvasViewer getViewer(){
        return viewer;
    }

    public void addViewer(WhiteCanvasViewer v){
        this.viewer = v;
    }

    public void removeViewer(Viewer v) {
        viewer = null;
    }

    public void clear(){
        internal =  new DirectedMultigraph<>(null, null, false);
    }

    private Graph<Node, Edge> clone(Graph<Node, Edge> graph) {
        Graph<Node,Edge> newInternal = new DirectedMultigraph<>(null, null, false);
        for (Node N : graph.vertexSet()) {
            newInternal.addVertex(N);
        }

        for (Edge E : graph.edgeSet()) {
            newInternal.addEdge(E.getSource(), E.getTarget(), E);
        }

        return newInternal;
    }

    public boolean hasNext(){
        return (stateNum - 1 > currentInternal );
    }

    public boolean hasPrev(){
        return currentInternal > 0;
    }

    public void redo(){
        if (hasNext()){
        currentInternal++;
        internal = originator.restoreFromMemento(careTaker.getMemento(currentInternal));
        }
    }

    public void undo(){
        if (hasPrev()){
            currentInternal--;
            System.out.println(currentInternal);
            internal = originator.restoreFromMemento(careTaker.getMemento(currentInternal));
        }
    }

    public void addEdge(Point2D coord1, Point2D coord2, int type){
        Node nodeOne = findFirstBox(coord1);
        Node nodeTwo = findFirstBox(coord2);
        if (nodeOne == null || nodeTwo == null){
            System.out.println("Cannot add an edge without both existent nodes");
        } else{
            Edge e = new AnnotatedEdge(nodeOne, nodeTwo, type);
            addArrow(nodeOne, nodeTwo, e);
        }
    }

    private Node findFirstBox(Point2D coord){
        Iterable<Node> nodeSet = getClasses();
        System.out.println("FINDING BOX AT: " + coord.toString());
        for(Node node:nodeSet){
            System.out.println("CURRENTLY LOOKING AT NODE: " + node.getUpperLeftCoord().toString());
            double x1 = node.getUpperLeftCoord().getX();
            double y1 = node.getUpperLeftCoord().getY();
            double h = node.getHeight();
            double w = node.getWidth();
            System.out.println("x1: " + x1 + " x1+w: " + (x1 + w) + "  x1 <= coord.getX(): " + (x1 <= coord.getX()) + " coord.getX() <= x1 + w  " + (coord.getX() <= x1 + w ));
            System.out.println("y1: " + y1 + " y1-h: " + (y1 + h) + " coord.getY() >= y1 - h " + (coord.getY() <= y1 + h) + " y1 >= coord.getY() " + (y1 >= coord.getY()));
            if(x1 <= coord.getX() && coord.getX() <= x1 + w && y1 <= coord.getY() && coord.getY() <= y1 + h){
                return node;
            }
        }
        return null;
    }
}
