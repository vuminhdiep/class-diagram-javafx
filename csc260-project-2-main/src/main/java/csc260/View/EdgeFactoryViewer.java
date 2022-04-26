package csc260.View;

import csc260.Document.ArrowHead;
import csc260.Document.Edge;
import csc260.Document.MappingEdges;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

import javafx.geometry.Point2D;

public abstract class EdgeFactoryViewer {
    /**
     * Draws the given arrow from the start to end points with the given distance from either end.
     *
     * @param arrow an Arrow to be drawn
     * @param from the start position
     * @param to the end position
     * @param distance an distance from start and end positions
     *
     */
    private Edge internal;
    private static final String STYLE_CLASS_LINE = "arrow-line";
    private static final String STYLE_CLASS_HEAD = "arrow-head";

    private final Line line = new Line();
    private final ArrowHead head = new ArrowHead();
    public EdgeFactoryViewer(Edge E){
        this.internal = E;
        line.getStyleClass().add(STYLE_CLASS_LINE);
        head.getStyleClass().add(STYLE_CLASS_HEAD);
    }

    /**
     * Draws the arrow for its current size and position values.
     */
    private Pane drawInheritanceEdge() {
        Pane added = new Pane();
        Point2D from = internal.getFrom();
        Point2D to = internal.getTo();

        double fromX = from.getX();
        double toX = to.getX();

        double fromY = from.getY();
        double toY = to.getY();

        final double deltaX = toX - fromX;
        final double deltaY = toY - fromY;

        final double angle = Math.atan2(deltaX, deltaY);

        final double headX = toX - head.getLength() / 2 * Math.sin(angle);
        final double headY = toY - head.getLength() / 2 * Math.cos(angle);



        line.setStartX(fromX);
        line.setStartY(fromY);
        line.setEndX(headX);
        line.setEndY(headY);


//        Point2D endPoint = new Point2D(headX,headY);
//        Polygon arrowHead = createTriangle(endPoint,head.getLength() / 2 * Math.sin(angle),angle);
//        arrowHead.setFill(Color.WHITE);

        head.changeFillColor(Color.WHITE); //draw white arrow head for inheritance
        head.setCenter(headX, headY);
        head.setAngle(Math.toDegrees(-angle));
        head.draw();
//        if (Math.hypot(deltaX, deltaY) < 2 * internal.getLength()) {
//            added.setVisible(false);
//        } else {
//            added.setVisible(true);
//        }

        added.getChildren().addAll(line, head);
        return added;
    }

    private Polygon createTriangle(Point2D origin, double length, double angle){
        Polygon fovTriangle = new Polygon(
                0d, 0d,
                (length * Math.tan(angle)), -length,
                -(length * Math.tan(angle)), -length
        );

        fovTriangle.setLayoutX(origin.getX());
        fovTriangle.setLayoutY(origin.getY());
        return fovTriangle;
    }

    private Pane drawContainmentEdge(){
        Pane added = new Pane();
        Point2D from = internal.getFrom();
        Point2D to = internal.getTo();

        double fromX = from.getX();
        double toX = to.getX();

        double fromY = from.getY();
        double toY = to.getY();

        final double deltaX = toX - fromX;
        final double deltaY = toY - fromY;

        final double angle = Math.atan2(deltaX, deltaY);

        final double headX = toX - head.getLength() / 2 * Math.sin(angle);
        final double headY = toY - head.getLength() / 2 * Math.cos(angle);

        line.setStartX(fromX);
        line.setStartY(fromY);
        line.setEndX(headX);
        line.setEndY(headY);


        head.changeFillColor(Color.BLACK);
        head.setCenter(headX, headY);
        head.setAngle(Math.toDegrees(-angle));
        head.draw();
//        if (Math.hypot(deltaX, deltaY) < 2 * internal.getLength()) {
//            added.setVisible(false);
//        } else {
//            added.setVisible(true);
//        }

        added.getChildren().addAll(line, head);
        return added;


    }

    private Pane drawDelegationEdge(){
        Pane added = new Pane();
        Point2D from = internal.getFrom();
        Point2D to = internal.getTo();

        double fromX = from.getX();
        double toX = to.getX();

        double fromY = from.getY();
        double toY = to.getY();

        final double deltaX = toX - fromX;
        final double deltaY = toY - fromY;

        final double angle = Math.atan2(deltaX, deltaY);

        final double headX = toX - head.getLength() / 2 * Math.sin(angle);
        final double headY = toY - head.getLength() / 2 * Math.cos(angle);

        line.setStartX(fromX);
        line.setStartY(fromY);
        line.setEndX(headX);
        line.setEndY(headY);
        head.changeFillColor(Color.WHITE);
        head.setCenter(headX, headY);
        head.setAngle(Math.toDegrees(-angle));
        Shape diamondHead = head.drawDiamond(headX,headY,head.getLength());

        added.getChildren().addAll(line, diamondHead);
        return added;



    }


    public void draw(Pane canvas) {
        Pane added = new Pane();
        if (internal.getType() == MappingEdges.INHERITANCE){
            added = drawInheritanceEdge();
        }
        else if (internal.getType() == MappingEdges.CONTAINMENT){
            added = drawContainmentEdge();
        }
        else if (internal.getType() == MappingEdges.DELEGATION){
            added = drawDelegationEdge();
        }
        canvas.getChildren().add(added);
    }

}
