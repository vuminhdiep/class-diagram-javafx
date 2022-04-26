package csc260.View;

import csc260.Controller.NodeController;
import csc260.Document.Method;
import csc260.Document.Node;
import csc260.Document.NodesConfig;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class NodeViewerFactory {

    Node internal;
    static class Delta { double x, y; }

    public NodeViewerFactory(Node N) {
        this.internal = N;
    }


    private Group drawNote() {
        Group added = new Group();
        double x = internal.getX();
        double y = internal.getY();
        int width = (int) internal.getWidth();
        int height = (int) internal.getHeight();

//        x = 0;
//        y = 0;
        Polygon hexagon = new Polygon(x,y, x + width -30, y, x+width, y +30, x+width, y +height, x, y + height);
//        Polygon hexagon = new Polygon(0,0, 0 + width -30, 0, 0+width, 0 +30, 0+width, 0 +height, 0, 0 + height);

        hexagon.setFill(Color.TRANSPARENT);
        hexagon.setStroke(Color.GREEN);
//        hexagon.set
        added.getChildren().add(hexagon);

        Text name = new Text((String) internal.getContent().get(0));
        name.setX(x + 55);
        name.setY(y + 10);

        added.getChildren().add(name);

        return added;
    }



    private Group drawNameClass() {
        Group added = new Group();
        double x = internal.getX();
        double y = internal.getY();
        double width =  internal.getWidth();
        double height =  internal.getHeight();
        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(Color.TRANSPARENT);
        r.setStrokeWidth(2);
        r.setStroke(Color.BLACK);

        Text name =  new Text((String) internal.getContent().get(0));
        name.setX(x + 45);
        name.setY(y + 10);


        added.getChildren().add(r);

        added.getChildren().add(name);

        return added;
    }

    private Group drawInterface() {
        Group added = new Group();
        double x = internal.getX();
        double y = internal.getY();
        int width = (int) internal.getWidth();
        int height = (int) internal.getHeight();
        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(Color.TRANSPARENT);
        r.setStrokeWidth(2);
        r.setStroke(Color.RED);
        Line l = new Line();
        l.setStrokeWidth(2);
        l.setStroke(Color.RED);
        l.setStartX(x);
        l.setStartY(y + 30);
        l.setEndX(x + width);
        l.setEndY(y + 30);

        TextArea txt = new TextArea();
        txt.setLayoutX(x);
        txt.setLayoutY(y + 30);
        txt.setMaxSize(internal.getWidth(), internal.getHeight()-30);
        Text name =  new Text((String) internal.getContent().get(0));
        name.setX(x + 45);
        name.setY(y + 10);



        added.getChildren().add(l);
        added.getChildren().add(r);
        added.getChildren().add(txt);

        added.getChildren().add(name);

        return added;
    }



    private Group drawClass() {
        Group added = new Group();
        double x = internal.getX();
        double y = internal.getY();
        double width =  internal.getWidth();
        double height = internal.getHeight();
        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(Color.TRANSPARENT);
        r.setStrokeWidth(2);
        r.setStroke(Color.BLACK);
        Line l2 = new Line();
        l2.setStartX(x);
        l2.setStartY(y + 15);
        l2.setEndX(x + width);
        l2.setEndY(y + 15);
        Line l = new Line();
        l.setStartX(x);
        l.setStartY(y + 50);
        l.setEndX(x + width);
        l.setEndY(y + 50);


        Object vars = internal.getContent().get(1);
        double currline_x = x;
        double currline_y = y+25;

        if ((vars != null) && (vars instanceof ArrayList)){
            for (String var : (ArrayList<String>) vars) {
                Text inst = new Text(var);
                inst.setX(currline_x);
                inst.setY(currline_y + 10);
                currline_y = currline_y + 10;
                added.getChildren().add(inst);
            }
        }


        Object methods = internal.getContent().get(2);
        double bottomcurrline_x = x;
        double bottomcurrline_y = y+50;

        if ((methods != null) && (methods instanceof ArrayList)){
            for (Method m : (ArrayList<Method>) methods) {
                String mStereo = String.format("%s (", m.getName());
                String p = String.join(",", m.getParas());
                mStereo += p + ")";
                System.out.println(mStereo);
                Text mToAdd = new Text(mStereo);
                mToAdd.setX(bottomcurrline_x);
                mToAdd.setY(bottomcurrline_y + 10);
                bottomcurrline_y = bottomcurrline_y + 10;
                added.getChildren().add(mToAdd);
            }
        }


        Text name = new Text((String) internal.getContent().get(0));
        name.setX(x + 50);
        name.setY(y + 10);
        added.getChildren().add(l);
        added.getChildren().add(l2);
        added.getChildren().add(r);

        added.getChildren().add(name);

        return added;
    }

    public Group drawHalfClass(){
        Group added = new Group();
        double x = internal.getX();
        double y = internal.getY();
        int width = (int) internal.getWidth();
        int height = (int) internal.getHeight();

        Rectangle r = new Rectangle();
        r.setX(x);
        r.setY(y);
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(Color.TRANSPARENT);
        r.setStrokeWidth(2);
        r.setStroke(Color.BLACK);

        Line l = new Line();
        l.setStartX(x);
        l.setStartY(y + 15);
        l.setEndX(x + width);
        l.setEndY(y + 15);

        Object methods = internal.getContent().get(2);
        double bottomcurrline_x = x;
        double bottomcurrline_y = y+50;

        if ((methods != null) && (methods instanceof ArrayList)){
            for (Method m : (ArrayList<Method>) methods) {
                String mStereo = String.format("%s(", m.getName());
                String p = new String();
                if(m.getParas() != null) {
                    p = String.join(", ", m.getParas());
                }
//
                mStereo += p + ")";
                System.out.println(mStereo);
                Text mToAdd = new Text(mStereo);
                mToAdd.setX(bottomcurrline_x);
                mToAdd.setY(bottomcurrline_y + 10);
                bottomcurrline_y = bottomcurrline_y + 10;
                added.getChildren().add(mToAdd);
            }
        }



        Text name = new Text((String) internal.getContent().get(0));
        name.setX(x + 50);
        name.setY(y + 10);
        added.getChildren().add(r);
        added.getChildren().add(l);
        added.getChildren().add(name);

        return added;

    }


    public void draw(Pane canvas) {
        Group added;
        if (internal.getType()==NodesConfig.CLASSBOX) {
            System.out.println("drawing");
            added = drawClass();
        }else if(internal.getType()==NodesConfig.HALFCLASS){
            added = drawHalfClass();
        }else if(internal.getType()==NodesConfig.NAMECLASS){
            added = drawNameClass();
        } else if (internal.getType()== NodesConfig.INTERFACE) {
            added = drawInterface();
        }
        else {
            added = drawNote();
        }

        //Controller
        NodeController controller = new NodeController();

        EventHandler<MouseEvent> mouseDrag = controller.handleDrag(added, internal);
        EventHandler<MouseEvent> mouseClick = controller.handleClick(added);


        added.addEventFilter(MouseEvent.MOUSE_DRAGGED, mouseDrag );
        added.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseClick);


        int handleRadius = 5;

        Circle resizeHandleSE = new Circle(handleRadius, Color.GOLD);

        resizeHandleSE.setLayoutX(internal.getX() + internal.getWidth());
        resizeHandleSE.setLayoutY(internal.getY() + internal.getHeight());
        resizeHandleSE.addEventHandler(MouseEvent.MOUSE_DRAGGED, controller.handleCircleDrag(resizeHandleSE, internal, added, mouseDrag));
        added.getChildren().addAll(resizeHandleSE);
        canvas.getChildren().add(added);

    }


}
