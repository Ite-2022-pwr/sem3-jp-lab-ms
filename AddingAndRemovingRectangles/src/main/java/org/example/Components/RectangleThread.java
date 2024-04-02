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

    public boolean contains(int x, int y) {
        return rectangle.contains(x, y);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void moveRight(int pixels) {
        rectangle.moveRight(pixels);
        drawingPanel.repaint();
    }

    public void moveLeft(int pixels) {
        rectangle.moveLeft(pixels);
        drawingPanel.repaint();
    }

    // run method to move the rectangles
    @Override
    public void run() {
        while (true) {
            if(rectangle.getDirection() == 0) {
                moveRight(3);
            } else {
                moveLeft(3);
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
