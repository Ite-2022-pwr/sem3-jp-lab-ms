package org.example.Components;
import javax.swing.*;
import java.awt.*;

// DRAWING PANEL - SIZE, LOCATION, COLOR, WAVES FUNCTIONING
public class DrawingPanel extends JPanel {
    private int amplitude;
    private int frequency;
    private int fillPercentage;
    private int xOffset;
    public static Color colorPurple2 = Color.decode("#8142A5");

    // DrawingPanel constructor
    public DrawingPanel(int width, int height, int x, int y, int amplitude, int frequency, int fillPercentage) {
        // setting panel size and position
        setBounds(x, y, width, height);

        // setting background color
        setBackground(Color.WHITE);

        // setting wave parameters
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.fillPercentage = fillPercentage;
        this.xOffset = 0;
    }

    // painting the waves
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRectangularWave(g, amplitude, frequency, fillPercentage, xOffset);
    }

    private void drawRectangularWave(Graphics g, int amplitude, int frequency, int fillPercentage, int xOffset) {
        int panelHeight = getHeight();
        int waveHeight = (int) (amplitude * (fillPercentage / 100.0));
        int startY = (panelHeight - waveHeight) / 2;

        g.setColor(colorPurple2);

        int[] xPoints = new int[getWidth()];
        int[] yPoints = new int[getWidth()];

        for (int x = 0; x < getWidth(); x++) {
            int y = (int) (startY + amplitude * Math.signum(Math.sin(2 * Math.PI * frequency * (x + xOffset) / getWidth())));
            xPoints[x] = x;
            yPoints[x] = y;
        }

        g.drawPolyline(xPoints, yPoints, getWidth());
    }


    public int getAmplitude() {
        return amplitude;
    }

    public int getFrequency() {
        return frequency;
    }

    public int getFillPercentage() {
        return fillPercentage;
    }

    public int getXOffset() {
        return xOffset;
    }

    public void setAmplitude(int amplitude) {
        this.amplitude = amplitude;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setFillPercentage(int fillPercentage) {
        this.fillPercentage = fillPercentage;
    }

    public void setXOffset(int offset) {
        this.xOffset= offset;
    }
}
