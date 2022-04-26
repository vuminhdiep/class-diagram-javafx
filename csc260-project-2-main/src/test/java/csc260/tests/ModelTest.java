package csc260.tests;


import csc260.Controller.WhiteCanvasController;
import csc260.Document.*;
import csc260.View.WhiteCanvasViewer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelTest {
    public class ViewerSpy extends WhiteCanvasViewer {
        private int numUpdates = 0;

        public ViewerSpy(WhiteCanvasModel model, WhiteCanvasController controller) {
            super(model, controller);
        }


        public void update(){
            numUpdates ++;
        }


        public int getNumUpdates(){
            return numUpdates;
        }
    }

    @Test
    public void testModel(){
        WhiteCanvasModel m = new WhiteCanvasModel(800,800);
        WhiteCanvasController controller = new WhiteCanvasController();
        Node nFrom = new ClassBoxNode("TestClassBoxNode");
        Node nTo = new Interface("TestInterface");
        Node n = new TextNode("TestTextNode");
        Edge e = new AnnotatedEdge();
        ViewerSpy fake = new ViewerSpy(m,controller);
        m.addViewer(fake);
        m.notifyViewers();
        assertEquals("There's an update when we add viewer",1,fake.getNumUpdates());

        m.addClass(nFrom);
        m.notifyViewers();
        assertEquals("The model updates when add a class",2,fake.getNumUpdates());

        m.addClass(nTo);
        m.notifyViewers();
        assertEquals("The model updates when add a class",3,fake.getNumUpdates());

        m.addArrow(nFrom, nTo, e);
        m.notifyViewers();
        assertEquals("The model updates when add an arrow",4,fake.getNumUpdates());

        m.removeArrow(e);
        m.notifyViewers();
        assertEquals("The model still updates if remove the arrow",5,fake.getNumUpdates());

        m.removeClass(n);
        m.notifyViewers();
        assertEquals("The model still updates if remove a class",6,fake.getNumUpdates());

        m.removeViewer(fake);
        m.addClass(n);
        assertEquals("There is no update because there is no viewer left",6,fake.getNumUpdates());




    }
}
