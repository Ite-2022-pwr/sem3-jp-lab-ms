package org.example.Components;
import java.awt.Color;

// CIRCLE PARAMETERS
public class Circle {
    int locationX;
    int locationY;
    private Color color;
    private final long calculationTime;

    // Circle Constructor
    public Circle(int locationX, int locationY, Color color, long calculationTime) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.color = color;
        this.calculationTime = calculationTime;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public long getCalculationTime() {
        return calculationTime;
    }
}

