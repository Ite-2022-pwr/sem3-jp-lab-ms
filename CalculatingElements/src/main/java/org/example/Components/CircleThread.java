package org.example.Components;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

// CIRCLES THREAD - MANAGING THE MOVEMENT OF THE CIRCLES
public class CircleThread extends Thread {
    private final Circle circle;
    private final DrawingPanel drawingPanel;
    private final long startTime;
    public static Color colorPurple2 = Color.decode("#8142A5");
    private long elapsedTime;

    // RectangleThread constructor
    public CircleThread(Circle circle, DrawingPanel drawingPanel) {
        this.circle = circle;
        this.drawingPanel = drawingPanel;
        this.startTime = System.currentTimeMillis();
    }

    public Circle getCircle() {
        return this.circle;
    }

    public long getElapsedTime() {
        long currentTime = System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);
    }

    public long getCalculationTime() {
        return circle.getCalculationTime();
    }

    public boolean isFinished() {
        return elapsedTime >= circle.getCalculationTime();
    }

    // run method to move the rectangles
    @Override
    public void run() {
        while (true) {
            long currentTime = System.currentTimeMillis();
            elapsedTime = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);

            if (elapsedTime >= circle.getCalculationTime()) {
                circle.setColor(colorPurple2);
            }

            SwingUtilities.invokeLater(drawingPanel::repaint);

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

