package org.example.Components;
import java.awt.Color;

// CIRCLE PARAMETERS
public class Circle {
    private int x;
    private int y;
    private int direction;
    private boolean isActive;
    private final Color color;

    // Circle constructor
    public Circle(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.isActive = true;
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
}

