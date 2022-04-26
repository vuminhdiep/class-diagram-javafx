package csc260.Document;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;

import javafx.scene.shape.Shape;
import javafx.scene.shape.Path;

/**
 * An arrow-head shape.
 *
 * <p>
 * This is used by the AnnotatedEdge class.
 * </p>
 */
public class ArrowHead extends Path {

    private static final double DEFAULT_LENGTH = 10;
    private static final double DEFAULT_WIDTH = 10;

    private double x;
    private double y;
    private double length = DEFAULT_LENGTH;
    private double width = DEFAULT_WIDTH;
    private double radius = -1;

    private final Rotate rotate = new Rotate();

    /**
     * Creates a new ArrowHead.
     */
    public ArrowHead() {
        setFill(Color.BLACK);
        setStroke(Color.BLACK);
        setStrokeType(StrokeType.INSIDE);
        getTransforms().add(rotate);
    }

    public void changeFillColor(Color newColor){
        setFill(newColor);
    }

    /**
     * Sets the center position of the arrow-head.
     *
     * @param x the x-coordinate of the center of the arrow-head
     * @param y the y-coordinate of the center of the arrow-head
     */
    public void setCenter(final double x, final double y) {

        this.x = x;
        this.y = y;

        rotate.setPivotX(x);
        rotate.setPivotY(y);
    }

    /**
     * Sets the length of the arrow-head.
     *
     * @param length the length of the arrow-head
     */
    public void setLength(double length) {
        this.length = Math.abs(length);
    }

    /**
     * Gets the length of the arrow-head.
     *
     * @return the length of the arrow-head
     */
    public double getLength() {
        return length;
    }

    /**
     * Sets the width of the arrow-head.
     *
     * @param width the width of the arrow-head
     */
    public void setWidth(double width) {
        this.width = Math.abs(width);
    }

    /**
     * Sets the radius of curvature of the {@link ArcTo} at the base of the arrow-head.
     *
     * <p>
     * If this value is less than or equal to zero, a straight line will be drawn instead. The default is -1.
     * </p>
     *
     * @param radius the radius of curvature of the arc at the base of the arrow-head
     */
    public void setRadiusOfCurvature(double radius) {
        this.radius = Math.abs(radius);
    }

    /**
     * Sets the rotation angle of the arrow-head.
     *
     * @param angle the rotation angle of the arrow-head, in degrees
     */
    public void setAngle(final double angle) {
        rotate.setAngle(angle);
    }

    /**
     * Draws the arrow-head for its current size and position values.
     */
    public void draw() {

        getElements().clear();

        getElements().add(new MoveTo(x, y + length / 2));
        getElements().add(new LineTo(x + width / 2, y - length / 2));

        if (radius > 0) {
            final ArcTo arcTo = new ArcTo();
            arcTo.setX(x - width / 2);
            arcTo.setY(y - length / 2);
            arcTo.setRadiusX(radius);
            arcTo.setRadiusY(radius);
            arcTo.setSweepFlag(true);
            getElements().add(arcTo);
        } else {
            getElements().add(new LineTo(x - width / 2, y - length / 2));
        }

        getElements().add(new ClosePath());
    }

    public Shape drawDiamond(double x, double y, double s) {
        final Path diamond = new Path();
        diamond.setFill(Color.WHITE);
        diamond.setStroke(Color.BLACK);
        MoveTo moveTo = new MoveTo();
        moveTo.setX(x);
        moveTo.setY(y);

        LineTo line1 = new LineTo();
        line1.setX(x+s);
        line1.setY(y-s);

        LineTo line2 = new LineTo();
        line2.setX(x+s*2);
        line2.setY(y);

        LineTo line3 = new LineTo();
        line3.setX(x+s);
        line3.setY(y+s);

        diamond.getElements().add(moveTo);
        diamond.getElements().add(line1);
        diamond.getElements().add(line2);
        diamond.getElements().add(line3);
        return diamond;
    }





}
