package csc260.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;


import csc260.Document.*;
import csc260.Document.AnnotatedEdge;
import csc260.View.Viewer;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javafx.geometry.Point2D;
import java.util.*; //Testing Iterable

@RunWith(JUnit4.class)
public class testAnnotatedEdge{
    private AnnotatedEdge edge;
    private AnnotatedEdge e;

    private Node from;
    private Node to;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp()
    {   Point2D coordFrom = new Point2D(1,2);
        Point2D coordTo = new Point2D(-3,4.3);
        from = new ClassBoxNode("TestFrom",coordFrom);
        to = new Interface("TestTo",coordTo);
        edge = new AnnotatedEdge(from,to,1);
        e = new AnnotatedEdge();
    }

    @After
    public void tearDown()
    {
        edge = null;
        e = null;
    }

    @Test
    public void construct(){
        assertEquals("A default edge with type CONTAINMENT",2,e.getType());
        assertEquals("An edge has type DELEGATION",1,edge.getType());

        assertEquals("Default annotation is empty string","",e.getAnnotation());
        assertEquals("Default annotation is empty string","",edge.getAnnotation());

        ArrayList<Point2D> pointList = new ArrayList<>();
        Point2D p0 = new Point2D(0,0);
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(0,0);
        Point2D p3 = new Point2D(0,0);

        pointList.add(p0);
        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);

        assertEquals("The default coordinates are (0,0)",pointList,e.getCoords());

        ArrayList<Point2D> pointListE = new ArrayList<>();
        Point2D p0E = new Point2D(151,64.3);
        Point2D p1E = new Point2D(202.33333333333334,113.33333333333334);
        Point2D p2E = new Point2D(-54.333333333333336,12.966666666666661);
        Point2D p3E = new Point2D(-3.0,64.3);

        pointListE.add(p0E);
        pointListE.add(p1E);
        pointListE.add(p2E);
        pointListE.add(p3E);

        assertEquals("The list of coordinates are the same as pointListE",pointListE,edge.getCoords());

    }



    @Test
    public void getLength(){

        double res = 154;
        assertEquals("The length is the distance between node from from and node to",res,edge.getLength(),DELTA);

        assertEquals("The default length is 0",0,e.getLength(),DELTA);

        Point2D p0 = new Point2D(0,1);
        Point2D p3 = new Point2D(1.5,3.2);

        edge.setCoord(0,p0);
        edge.setCoord(3,p3);

        double newRes = 2.6627053911388696;
        assertEquals("The length is the distance between node from from and node to is updated",newRes,edge.getLength(),DELTA);

    }

    @Test
    public void SetGetCoord(){
        ArrayList<Point2D> pointList = new ArrayList<>();
        Point2D p0 = new Point2D(0,0);
        Point2D p1 = new Point2D(0,0);
        Point2D p2 = new Point2D(0,0);
        Point2D p3 = new Point2D(0,0);

        pointList.add(p0);
        pointList.add(p1);
        pointList.add(p2);
        pointList.add(p3);

        assertEquals("The default coordinates are (0,0)",pointList,e.getCoords());
        e.setCoord(-10,new Point2D(3,4));

        assertEquals("The coordinates are still (0,0) because of invalid position",pointList,e.getCoords());

        ArrayList<Point2D> pointListE = new ArrayList<>();
        Point2D p0E = new Point2D(151,64.3);
        Point2D p1E = new Point2D(202.33333333333334,113.33333333333334);
        Point2D p2E = new Point2D(-54.333333333333336,12.966666666666661);
        Point2D p3E = new Point2D(-3,64.3);

        pointListE.add(p0E);
        pointListE.add(p1E);
        pointListE.add(p2E);
        pointListE.add(p3E);

        assertEquals("The list of coordinates are the same as pointListE",pointListE,edge.getCoords());

        edge.setCoord(15,new Point2D(-3,4.5));

        assertEquals("The coordinates are still the same because of invalid position",pointListE,edge.getCoords());

        Point2D newP0 = new Point2D(0,1);
        Point2D newP3 = new Point2D(1.5,3.2);

        e.setCoord(0,newP0);
        edge.setCoord(3,newP3);

        pointList.add(0,newP0);
        pointListE.add(3,newP3);

        double res = pointList.get(0).distance(pointList.get(3));
        double resE = pointListE.get(0).distance(pointListE.get(3));
        assertEquals("The length is the distance between node from from and node to is updated",resE,edge.getLength(),DELTA);
        assertEquals("The length is the distance between node from from and node to is updated",res,e.getLength(),DELTA);

    }

    @Test
    public void SetGetType(){
        assertEquals("A default edge with type CONTAINMENT",2,e.getType());
        assertEquals("An edge has type DELEGATION",1,edge.getType());

        e.setType(-3);
        assertEquals("A default type is CONTAINMENT because new type is invalid",2,e.getType());

        edge.setType(0);
        assertEquals("Edge has type INHERITANCE",0,edge.getType());

    }

    @Test
    public void SetGetAnnotation(){
        assertEquals("Default annotation is empty","",e.getAnnotation());
        assertEquals("Default annotation is empty","",edge.getAnnotation());

        e.setAnnotation("star");
        edge.setAnnotation("annotate");

        assertEquals("New annotation is star","star",e.getAnnotation());
        assertEquals("New annotation is annotate","annotate",edge.getAnnotation());

    }

    @Test
    public void updateFrom(){


        Node n = new ClassBoxNode("Test",new Point2D(5,6));
        Point2D nOutgoing = new Point2D(155,66);
        edge.updateFrom(n);
        e.updateFrom(n);

        assertEquals("Return null because there's no node to begin with",null,e.getSource());
        assertEquals("The coordinates of edge e is still (0,0)",new Point2D(0,0),e.getFrom());
        assertEquals("The node from is updated",n,edge.getSource());
        assertEquals("The coordinates of node from is updated",nOutgoing,edge.getFrom());


    }

    @Test
    public void updateTo(){
        Node n = new ClassBoxNode("Test",new Point2D(5,6));
        Point2D nIncoming = new Point2D(5,66.0);
        edge.updateTo(n);
        e.updateTo(n);

        assertEquals("Return null because there's no node to begin with",null,e.getTarget());
        assertEquals("The coordinates of edge e is still (0,0)",new Point2D(0,0),e.getTo());
        assertEquals("The node to is updated",n,edge.getTarget());
        assertEquals("The coordinates of node to is updated",nIncoming,edge.getTo());

    }

    @Test
    public void getFrom(){
        assertEquals("The coordinates of edge e is still (0,0)",new Point2D(0,0),e.getFrom());
        assertEquals("The coordinates of edge is by default",new Point2D(151,64.3),edge.getFrom());

        Node n = new ClassBoxNode("Test",new Point2D(5,6));
        Point2D nOutgoing = new Point2D(155,66.0);
        edge.updateFrom(n);
        e.updateFrom(n);

        assertEquals("The coordinates of edge e is still (0,0)",new Point2D(0,0),e.getFrom());
        assertEquals("The coordinates of node from is updated",nOutgoing,edge.getFrom());

    }

    @Test
    public void getTo(){
        assertEquals("The coordinates of edge e is still (0,0)",new Point2D(0,0),e.getTo());
        assertEquals("The coordinates of edge is by default",new Point2D(-3,64.3),edge.getTo());

        Node n = new ClassBoxNode("Test",new Point2D(5,6));
        Point2D nOutgoing = new Point2D(5,66.0);
        edge.updateTo(n);
        e.updateTo(n);

        assertEquals("The coordinates of edge e is still (0,0)",new Point2D(0,0),e.getTo());
        assertEquals("The coordinates of node from is updated",nOutgoing,edge.getTo());


    }

    @Test
    public void getSourceTest(){

        assertEquals("There is no node in the beginning",null,e.getSource());
        assertEquals("Edge has same node",from,edge.getSource());

        Point2D coordFrom = new Point2D(-10,2.3);
        Node fromN = new ClassBoxNode("TestFrom",coordFrom);
        e.updateFrom(fromN);
        edge.updateFrom(fromN);

        assertEquals("There is no node at the beginning",null,e.getSource());
        assertEquals("There is a node in the edge",fromN,edge.getSource());
    }

    @Test
    public void getTargetTest(){
        assertEquals("There is no node in the beginning",null,e.getTarget());
        assertEquals("Edge has same node",to,edge.getTarget());

        Point2D coord = new Point2D(10,2.3);
        Node toNode = new ClassBoxNode("TestFrom",coord);
        e.updateTo(toNode);
        edge.updateTo(toNode);

        assertEquals("There is no node at the beginning",null,e.getTarget());
        assertEquals("There is a node in the edge",toNode,edge.getTarget());
    }



}
