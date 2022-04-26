package csc260.tests;

import csc260.Document.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import javafx.geometry.Point2D;
import java.util.ArrayList;


import static org.junit.Assert.assertEquals;


@RunWith(JUnit4.class)
public class TextNodeTest {
    private TextNode n;
    private TextNode nTwo;
    private TextNode nCoord;
    private static final double DELTA = 1e-15;

    @Before
    public void setUp() {
        Point2D coord = new Point2D(1,2);
        n = new TextNode("TestClass");
        nTwo = new TextNode();
        nCoord = new TextNode("TestText",coord);
    }

    @After
    public void tearDown() {
        n = null;
        nTwo = null;
    }

    @Test
    public void construct()
    {   ArrayList<String> text = new ArrayList<>();
        text.add("TestClass");
        assertEquals("New classbox has no body", false, n.isEmpty());
        assertEquals("New classbox has header", text, n.getContent());

        assertEquals("New classbox has standard coordinates", NodesConfig.STANDARDCOORD, n.getUpperLeftCoord());

    }
    @Test
    public void constructwithCoord()
    {
        assertEquals("New classbox has body because of title", false, nCoord.isEmpty());

        assertEquals("New has its own coordinates passed", 1, nCoord.getX(), DELTA);
        assertEquals("New has its own coordinates passed", 2, nCoord.getY(), DELTA);

    }

    @Test
    public void testGetSetUpperLeftCoord()
    {   Point2D defaultCoord = new Point2D(0, 0);
        assertEquals("Default upperLeftCoord for node n is (0,0)",defaultCoord,n.getUpperLeftCoord());

        n.setUpperLeftCoord(-2,2);

        Point2D newCoord = new Point2D(-2,2);
        assertEquals("UpperLeftCoord for node n is updated and now (-2,2)",newCoord,n.getUpperLeftCoord());

        Point2D coord = new Point2D(1,2);
        assertEquals("LeftUpperCoord of node nTwo will be (1,2) by constructor",coord,nCoord.getUpperLeftCoord());

        nTwo.setUpperLeftCoord(-0.5,0.6666);
        Point2D newCoordTwo = new Point2D(-0.5,0.6666);
        assertEquals("UpperLeftCoord for node nTwo is updated and now (-0.5,0.6666)",newCoordTwo,nTwo.getUpperLeftCoord());

    }

    @Test
    public void testGetSetWidth(){
        double defaultWidth = 150;
        assertEquals("Standard width is 150 for a classBoxNode n",defaultWidth,n.getWidth(),DELTA);
        assertEquals("Standard width is 150 for a classBoxNode nTwo",defaultWidth,nTwo.getWidth(),DELTA);

        n.setWidth(160.22);
        nTwo.setWidth(-50.12);
        assertEquals("New width is 160.22 for a classBoxNode n",160.22,n.getWidth(),DELTA);
        assertEquals("If the new width is < 0 we set the new width with its absolute value",50.12,nTwo.getWidth(),DELTA);

        assertEquals("The height should be default after setting Width",120,n.getHeight(),DELTA);
        assertEquals("The height should be default after setting Width",120,n.getHeight(),DELTA);


    }

    @Test
    public void testGetSetHeight(){
        double defaultHeight = 120;
        assertEquals("Standard width is 150 for a classBoxNode n",defaultHeight,n.getHeight(),DELTA);
        assertEquals("Standard width is 150 for a classBoxNode nTwo",defaultHeight,nTwo.getHeight(),DELTA);

        n.setHeight(160.22);
        nTwo.setHeight(-50.12);
        assertEquals("New width is 160.22 for a classBoxNode n",160.22,n.getHeight(),DELTA);
        assertEquals("If the new width is < 0 we set the new width with its absolute value",50.12,nTwo.getHeight(),DELTA);

    }

    @Test
    public void testGetType(){

        assertEquals("Type of node n is textNode which is 0 according to NodesConfig",2,n.getType());
        assertEquals("Type of node nTwo is textNode which is 0 according to NodesConfig",2,nTwo.getType());

    }


    @Test
    public void testGetXY(){
        assertEquals("Default X coord of node n is 0",0,n.getX(),DELTA);
        assertEquals("Default Y coord of node n is 0",0,n.getY(),DELTA);

        assertEquals("Default X coord of node nTwo is 1 from setup",1,nCoord.getX(),DELTA);
        assertEquals("Default Y coord of node nTwo is 2 from setup",2,nCoord.getY(),DELTA);

        n.setUpperLeftCoord(1.23,-3);
        nTwo.setUpperLeftCoord(0,0);

        assertEquals("New X coord of node n is 1.23",1.23,n.getX(),DELTA);
        assertEquals("New Y coord of node n is -3",-3,n.getY(),DELTA);

        assertEquals("New X coord of node nTwo is 0",0,nTwo.getX(),DELTA);
        assertEquals("New Y coord of node nTwo is 0 from setup",0,nTwo.getY(),DELTA);
    }


    @Test
    public void testGetIncomingEdgeCoord(){
        Point2D nIncomingEdgeCoord = new Point2D(0,60);
        assertEquals("Default incomingEdgeCoord is half way in the centre of the right edge of the node n",nIncomingEdgeCoord,n.getIncomingEdgeCoord());

        Point2D nCoordIncomingEdgeCoord = new Point2D(1,62);
        assertEquals("Default incomingEdgeCoord is half way in the centre of the right edge of the node nTwo",nCoordIncomingEdgeCoord,nCoord.getIncomingEdgeCoord());

        n.setUpperLeftCoord(-1,-2);
        Point2D newIncomingEdgeCoord = new Point2D(-1,58);
        assertEquals("New incomingEdgeCoord is half way in the centre of the right edge of the node n with new UpperLeftCoord",newIncomingEdgeCoord,n.getIncomingEdgeCoord());

        nCoord.setUpperLeftCoord(3.5,-2.22);
        Point2D newIncomingEdgeCoordTwo = new Point2D(3.5,57.78);
        assertEquals("New incomingEdgeCoordTwo is half way in the centre of the right edge of the node nTwo with new UpperLeftCoord",newIncomingEdgeCoordTwo,nCoord.getIncomingEdgeCoord());


    }

    @Test
    public void testGetOutgoingEdgeCoord(){
        Point2D nOutgoingEdgeCoord = new Point2D(150,60);
        assertEquals("Default outgoingEdgeCoord is half way in the centre of the left edge of the node n",nOutgoingEdgeCoord,n.getOutgoingEdgeCoord());

        Point2D nCoordOutgoingEdgeCoord = new Point2D(151,62);
        assertEquals("Default outgoingEdgeCoord is half way in the centre of the left edge of the node nTwo",nCoordOutgoingEdgeCoord,nCoord.getOutgoingEdgeCoord());

        n.setUpperLeftCoord(-1,-2);
        Point2D newOutgoingEdgeCoord = new Point2D(149,58);
        assertEquals("New outgoingEdgeCoord is half way in the centre of the left edge of the node n with new UpperLeftCoord",newOutgoingEdgeCoord,n.getOutgoingEdgeCoord());

        nCoord.setUpperLeftCoord(3.5,-2.22);
        Point2D newOutgoingEdgeCoordTwo = new Point2D(153.5,57.78);
        assertEquals("New outgoingEdgeCoordTwo is half way in the centre of the left edge of the node nTwo with new UpperLeftCoord",newOutgoingEdgeCoordTwo,nCoord.getOutgoingEdgeCoord());

    }

    @Test
    public void testGetContents(){

        String text = "TestClass";
        ArrayList<Object> contentsList = new ArrayList<>();
        contentsList.add(text);
        assertEquals("Node n has the same contents as contents list",contentsList,n.getContent());

    }





}