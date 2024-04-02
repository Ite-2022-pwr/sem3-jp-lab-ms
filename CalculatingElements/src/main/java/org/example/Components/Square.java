package org.example.Components;
import java.awt.*;

// SQUARE PARAMETERS AND MOVEMENT
public class Square {
    private final int locationX;
    private final int locationY;
    private Color color;
    private final long calculationTime;

    // Rectangle Constructor
    public Square(int locationX, int locationY, Color color, long calculationTime) {
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
