package org.example.Components;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

// RECTANGLE THREAD - MANAGING THE MOVEMENT OF THE RECTANGLES
public class SquareThread extends Thread {
    private final Square square;
    private final DrawingPanel drawingPanel;
    private final long startTime;
    private long elapsedTime;
    public static Color colorBlue2 = Color.decode("#1C5C7A");

    // SquareThread constructor
    public SquareThread(Square square, DrawingPanel drawingPanel) {
        this.square = square;
        this.drawingPanel = drawingPanel;
        this.startTime = System.currentTimeMillis();
    }

    public Square getSquare() {
        return square;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public long getCalculationTime() {
        return square.getCalculationTime();
    }

    public boolean isFinished() {
        return elapsedTime >= square.getCalculationTime();
    }

    // run method to move the squares
    @Override
    public void run() {
        while (!isFinished()) {
            long currentTime = System.currentTimeMillis();
            elapsedTime = TimeUnit.MILLISECONDS.toSeconds(currentTime - startTime);

            if (elapsedTime >= square.getCalculationTime()) {
                square.setColor(colorBlue2);
                SwingUtilities.invokeLater(drawingPanel::repaint);
            }

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

