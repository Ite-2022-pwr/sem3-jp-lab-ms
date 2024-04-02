package org.example.Components;
import java.awt.Color;
import java.awt.Point;

// CIRCLE PARAMETERS
public class Circle {
    private int x;
    private int y;
    private int direction;
    private boolean isActive;
    private final Color color;
    private final int circleSize;

    // Circle constructor
    public Circle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.circleSize = 50;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean intersects(Circle otherCircle) {
        int thisBoundsX = getX();
        int thisBoundsY = getY();
        int otherBoundsX = otherCircle.getX();
        int otherBoundsY = otherCircle.getY();

        // assuming circleSize is a constant for all circles
        int thisBoundsSize = circleSize;
        int otherBoundsSize = otherCircle.getCircleSize();

        // checking for intersection
        return thisBoundsX < otherBoundsX + otherBoundsSize &&
                thisBoundsX + thisBoundsSize > otherBoundsX &&
                thisBoundsY < otherBoundsY + otherBoundsSize &&
                thisBoundsY + thisBoundsSize > otherBoundsY;
    }

    public int getCircleSize() {
        return circleSize;
    }

    public boolean containsPoint(Point point) {
        int centerX = getX() + circleSize / 2;
        int centerY = getY() + circleSize / 2;

        double distance = Math.sqrt(Math.pow(point.getX() - centerX, 2) + Math.pow(point.getY() - centerY, 2));

        return distance <= circleSize / (float)2;
    }
}
