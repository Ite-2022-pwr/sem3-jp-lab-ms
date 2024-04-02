package org.example.Components;

// RECTANGLE THREAD - MANAGING THE MOVEMENT OF THE RECTANGLES
public class RectangleThread extends Thread {
    private final Rectangle rectangle;
    private final DrawingPanel drawingPanel;

    // RectangleThread constructor
    public RectangleThread(Rectangle rectangle, DrawingPanel drawingPanel) {
        this.rectangle = rectangle;
        this.drawingPanel = drawingPanel;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void moveRight(int pixels) {
        rectangle.moveRight(pixels);
        drawingPanel.repaint();
    }

    // run method to move the rectangles
    @Override
    public void run() {
        while (true) {
            moveRight(3);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
