package csc260.Controller;

import csc260.Document.Node;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

import javafx.geometry.Point2D;

class NodeDrag implements EventHandler<MouseEvent>{
    Group added;
    Wrapper<Point2D> mouseLocation;
    Node internal;

    static void setUpDragging(Group group, NodeDrag.Wrapper<Point2D> mouseLocation) {

        group.setOnDragDetected(event -> {
            group.getParent().setCursor(Cursor.CLOSED_HAND);
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        });

        group.setOnMouseReleased(event -> {
            group.getParent().setCursor(Cursor.DEFAULT);
            mouseLocation.value = null ;
        });
    }

    public NodeDrag(Group added, Node internal){
        this.added = added;
        this.mouseLocation =  new Wrapper<>();
        setUpDragging(added, mouseLocation) ;
        this.internal = internal;
    }

    public void handle(MouseEvent event){
        if (event.getButton() == MouseButton.PRIMARY) {
            if (mouseLocation.value != null) {
                for (javafx.scene.Node s : added.getChildren()) {
                    s.setStyle("-fx-stroke: #9b0303;-fx-opacity: 0.5;");
                }
                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();
                double newX = added.getLayoutX() + deltaX;
                added.setLayoutX(newX);
                double newY = added.getLayoutY() + deltaY;
                added.setLayoutY(newY);
                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
                internal.setUpperLeftCoord(new Point2D(added.getBoundsInParent().getMinX(), added.getBoundsInParent().getMinY()));
                internal.setEdges( internal.getOutgoingEdgeCoord());
            }
        }
    }


    static class Wrapper<T> { T value ; }

}

class NodeClick implements EventHandler<MouseEvent>{

    Group pane;

    public NodeClick(Group pane){
        this.pane = pane;


    }

    public void handle(MouseEvent e){
        for (javafx.scene.Node s: pane.getChildren()){
            s.setStyle("-fx-stroke:#9b0303;-fx-opacity: 0.5;");
        }

    }
}

class circleClick implements EventHandler<MouseEvent>{

    Group group;
    NodeDrag.Wrapper<Point2D> mouseLocation;
    Node internal;
    int handleRadius;
    EventHandler<MouseEvent> handler;

    public circleClick(Circle circle, Node internal, Group group, EventHandler<MouseEvent> handler){
        this.mouseLocation =  new NodeDrag.Wrapper<>();
        setUpDragging(circle, mouseLocation) ;
        this.internal = internal;
        this.handleRadius = 10;
        this.group = group;
        this.handler = handler;
    }

    public void handle(MouseEvent event){
        if (event.getButton() == MouseButton.SECONDARY) {
            if (mouseLocation.value != null) {
                for (javafx.scene.Node s : group.getChildren()) {
                    s.setStyle("-fx-stroke: #9b0303;-fx-opacity: 0.5;");
                }

                double deltaX = event.getSceneX() - mouseLocation.value.getX();
                double deltaY = event.getSceneY() - mouseLocation.value.getY();

                double scaleX = (group.getBoundsInParent().getWidth() - group.getLayoutX())/ group.getBoundsInParent().getWidth();
                double scaleY = (group.getBoundsInParent().getHeight() - group.getLayoutY())/ group.getBoundsInParent().getHeight();

                System.out.println(deltaX);
                System.out.println(deltaY);


//
                group.setScaleX(group.getScaleX() + deltaX/100);
                group.setScaleY(group.getScaleY() +  deltaY/100);

                mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
                internal.setUpperLeftCoord(new Point2D(group.getBoundsInParent().getMinX(), group.getBoundsInParent().getMinY()));
                internal.setWidth(group.getBoundsInParent().getWidth());
                internal.setHeight(group.getBoundsInParent().getHeight());

            }
        }
    }

    static void setUpDragging(Circle circle, NodeDrag.Wrapper<Point2D> mouseLocation) {

        circle.setOnDragDetected(event -> {
            circle.getParent().getParent().setCursor(Cursor.CLOSED_HAND);
            mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
        });

        circle.setOnMouseReleased(event -> {
            circle.getParent().getParent().getParent().setCursor(Cursor.DEFAULT);
            mouseLocation.value = null ;
        });
    }
}

public class NodeController{

    public EventHandler<MouseEvent> handleDrag(Group pane, Node internal){
        return new NodeDrag(pane, internal);
    }

    public EventHandler<MouseEvent> handleClick(Group pane){
        return new NodeClick(pane);
    }

    public EventHandler<MouseEvent> handleCircleDrag(Circle circ,  Node internal, Group group, EventHandler<MouseEvent> handler){
        return new circleClick(circ, internal, group, handler );
    }

}


