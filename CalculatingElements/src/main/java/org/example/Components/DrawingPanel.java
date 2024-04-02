package org.example.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

// DRAWING PANEL - SIZE, LOCATION, COLOR, THREADS MANAGING CREATED RECTANGLES
public class DrawingPanel extends JPanel {
    private static final ArrayList<CircleThread> circleThreads = new ArrayList<>();
    private static final ArrayList<SquareThread> squareThreads = new ArrayList<>();
    private final int circleSize = 50;
    public static Color colorPurple1 = Color.decode("#C397DC");
    private final int squareSize = 50;
    public static Color colorBlue1 = Color.decode("#5394B3");
    private final ProcessLabel processLabel;

    // DrawingPanel constructor
    public DrawingPanel(ProcessLabel processLabel) {
        setFocusable(true);
        requestFocusInWindow();
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 10)); // border of the drawing panel
        setLayout(new GridLayout(0, 1, 20, 20));
        setSize(800, 400); // size
        setLocation(100, 100); // location
        setOpaque(true);
        Color colorPink3 = Color.decode("#F7BAD3"); // color
        setBackground(colorPink3);
        this.processLabel = processLabel;

        // mouse listener to check the progress
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                checkShapeUnderMouse(e);
            }
        });

        // mouse listener to delete finished figures by clicking on them
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                checkAndRemoveClickedShape(e);
            }
        });
    }

    // checking what shape is under the mouse
    private void checkShapeUnderMouse(MouseEvent e) {
        for (CircleThread circleThread : circleThreads) {
            Circle circle = circleThread.getCircle();
            if (isMouseOverShape(e, circle.getLocationX(), circle.getLocationY())) {
                updateProcessLabel(circleThread.getElapsedTime(), circleThread.getCalculationTime());
                return;
            }
        }

        for (SquareThread squareThread : squareThreads) {
            Square square = squareThread.getSquare();
            if (isMouseOverShape(e, square.getLocationX(), square.getLocationY())) {
                updateProcessLabel(squareThread.getElapsedTime(), squareThread.getCalculationTime());
                return;
            }
        }
    }

    // checking if the mouse is over a shape
    private boolean isMouseOverShape(MouseEvent e, int x, int y) {
        return e.getX() >= x && e.getX() <= x + 50 && e.getY() >= y && e.getY() <= y + 50;
    }

    // updating the label
    private void updateProcessLabel(long elapsedTime, long calculationTime) {
        SwingUtilities.invokeLater(() -> {
            // calculating the progress
            double progress = ((double) elapsedTime / calculationTime) * 100.0;

            // Ustawienie wartości na maksymalnie 100%
            if (progress > 100.0) {
                progress = 100.0;
            }

            processLabel.setText("Ukonczenie procesu: " + String.format("%.0f", progress) + "%");
            processLabel.setBounds(0, 0, processLabel.getPreferredSize().width, processLabel.getPreferredSize().height);
            processLabel.revalidate();
            processLabel.repaint();
        });
    }

    private void checkAndRemoveClickedShape(MouseEvent e) {
        for (CircleThread circleThread : circleThreads) {
            Circle circle = circleThread.getCircle();
            if (isMouseOverShape(e, circle.getLocationX(), circle.getLocationY()) && circleThread.isFinished()) {
                circleThreads.remove(circleThread);
                repaint();
                return;
            }
        }

        for (SquareThread squareThread : squareThreads) {
            Square square = squareThread.getSquare();
            if (isMouseOverShape(e, square.getLocationX(), square.getLocationY()) && squareThread.isFinished()) {
                squareThreads.remove(squareThread);
                repaint();
                return;
            }
        }
    }

    // adding circles
    public void addCircle() {
        int randomX = getRandomLocation(getWidth() - circleSize);
        int randomY = getRandomLocation(getHeight() - circleSize);
        Color color = colorPurple1;
        Circle newCircle = new Circle(randomX, randomY, color, 10);
        CircleThread circleThread = new CircleThread(newCircle, DrawingPanel.this);
        circleThreads.add(circleThread);
        circleThread.start();
    }

    // adding squares
    public void addSquare() {
        int randomX = getRandomLocation(getWidth() - squareSize);
        int randomY = getRandomLocation(getHeight() - squareSize);
        Color color = colorBlue1;
        Square newSquare = new Square(randomX, randomY, color, 5);
        SquareThread squareThread = new SquareThread(newSquare, DrawingPanel.this);
        squareThreads.add(squareThread);
        squareThread.start();
    }

    // creating a random location of the circle
    private int getRandomLocation(int max) {
        Random random = new Random();
        int margin = 20;
        return margin + random.nextInt(max - 2 * margin);
    }

    // painting all figures
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        synchronized (circleThreads) {
            for (CircleThread circleThread : circleThreads) {
                Circle circle = circleThread.getCircle();
                g.setColor(circle.getColor());
                g.fillOval(circle.getLocationX(), circle.getLocationY(), circleSize, circleSize);
            }
        }

        synchronized (squareThreads) {
            for (SquareThread squareThread : squareThreads) {
                Square square = squareThread.getSquare();
                g.setColor(square.getColor());
                g.fillRect(square.getLocationX(), square.getLocationY(), squareSize, squareSize);
            }
        }
    }
}
