package org.example.Components;
import java.awt.*;

// RECTANGLE PARAMETERS AND MOVEMENT
public class Rectangle {
    private int startX;
    private final int startY;
    private int endX;
    private int endY;
    private final Color fillColor;
    private final Color borderColor;

    // Rectangle Constructor
    public Rectangle(int startX, int startY, int endX, int endY, Color fillColor, Color borderColor) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    public void setEndPosition(int endX, int endY) {
        this.endX = endX;
        this.endY = endY;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getStartX () {
        return startX;
    }

    public int getStartY () {
        return startY;
    }

    public int getEndX () {
        return endX;
    }

    public int getEndY () {
        return endY;
    }

    // moving right (including the loop of the movement)
    public void moveRight(int pixels) {
        this.startX = (this.startX + 800 + pixels) % 800;
        this.endX = (this.endX + 800 + pixels) % 800;
    }
}
