package org.example.Components;
import javax.swing.*;

// CIRCLES THREAD - MANAGING THE MOVEMENT OF THE CIRCLES
public class CircleThread extends Thread {
    private final Circle circle;
    private final DrawingPanel drawingPanel;

    // RectangleThread constructor
    public CircleThread(Circle circle, DrawingPanel drawingPanel) {
        this.circle = circle;
        this.drawingPanel = drawingPanel;
    }

    public Circle getCircle() {
        return this.circle;  // Access the member variable using this
    }

    // run method to move the rectangles
    @Override
    public void run() {
        while (this.circle.isActive()) {
            this.drawingPanel.moveCircle(this.circle);
            this.drawingPanel.checkBoundary(this.circle);

            SwingUtilities.invokeLater(drawingPanel::repaint);

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

