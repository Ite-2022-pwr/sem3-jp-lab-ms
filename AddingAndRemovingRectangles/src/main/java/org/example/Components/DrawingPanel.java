package org.example.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// DRAWING PANEL - SIZE, LOCATION, COLOR, THREADS MANAGING CREATED RECTANGLES
public class DrawingPanel extends JPanel {
    private final List<RectangleThread> rectangleThreads = new ArrayList<>();
    private Rectangle currentRectangle = null;
    private int startX, startY;
    private final Random random = new Random();
    private Color randomColorFill;
    private int direction;
    private boolean leftClick = false;

    // available colors for the rectangles
    private final Color[] availableColors = {
            Color.decode("#318237"), Color.decode("#90D896"), Color.decode("#BAEBBE"),
            Color.decode("#8C3ABC"), Color.decode("#C397DC"), Color.decode("#DCBDEE"),
            Color.decode("#5394B3"), Color.decode("#93BAD7"), Color.decode("#C0D9EC")
    };

    private final int[] availableDirections = {0, 1};

    // DrawingPanel constructor
    public DrawingPanel() {
        setFocusable(true);
        requestFocusInWindow();
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 10)); // border of the drawing panel
        setLayout(new GridLayout(0, 1, 20, 20));
        setSize(800, 400); // size
        setLocation(100, 100); // location
        setOpaque(true);
        Color colorPink3 = Color.decode("#F7BAD3"); // color
        setBackground(colorPink3);

        // mouse listener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // checking if the left mouse button is pressed
                if (SwingUtilities.isLeftMouseButton(e)) {
                    startX = e.getX();
                    startY = e.getY();

                    // setting the color when the mouse is pressed
                    randomColorFill = getRandomColor();
                    currentRectangle = new Rectangle(startX, startY, startX, startY, randomColorFill, randomColorFill);

                    direction = getRandomDirection();
                    currentRectangle.setDirection(direction);

                    // starting a new thread when the mouse is pressed
                    RectangleThread rectangleThread = new RectangleThread(currentRectangle, DrawingPanel.this);
                    rectangleThreads.add(rectangleThread);
                    rectangleThread.start();

                    leftClick = true;

                    repaint();
                }

                // checking if the right mouse button is pressed
                if (SwingUtilities.isRightMouseButton(e)) {
                    leftClick = false;

                    // deleting the chosen rectangle
                    for (int i = rectangleThreads.size() - 1; i >= 0; i --) {
                        if (rectangleThreads.get(i).contains(e.getX(), e.getY())) {
                            rectangleThreads.remove(i);
                            break;
                        }
                    }

                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(leftClick) {
                    int endX = e.getX();
                    int endY = e.getY();

                    currentRectangle.setEndPosition(endX, endY);
                    repaint();
                } else {
                    System.out.println("else");
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // checking if the left mouse button is pressed
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int endX = e.getX();
                    int endY = e.getY();
                    currentRectangle.setEndPosition(endX, endY);
                    repaint();
                }
            }
        });
    }

    public int getRandomDirection() {
        return availableDirections[random.nextInt(2)];
    }

    private Color getRandomColor() {
        return availableColors[random.nextInt(availableColors.length)];
    }

    // painting the rectangles using threads
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (RectangleThread rectangleThread : rectangleThreads) {
            Rectangle rectangle = rectangleThread.getRectangle();
            g.setColor(rectangle.getFillColor());
            g.fillRect(rectangle.getStartX(), rectangle.getStartY(), rectangle.getEndX() - rectangle.getStartX(), rectangle.getEndY() - rectangle.getStartY());
            g.fillRect(rectangle.getStartX(), rectangle.getStartY(),
                    rectangle.getEndX() - rectangle.getStartX(), rectangle.getEndY() - rectangle.getStartY());

            if (rectangle.getBorderColor() != null) {
                g.setColor(rectangle.getBorderColor());
                g.drawRect(rectangle.getStartX(), rectangle.getStartY(), rectangle.getEndX() - rectangle.getStartX(), rectangle.getEndY() - rectangle.getStartY());
                g.drawRect(rectangle.getStartX(), rectangle.getStartY(),
                        rectangle.getEndX() - rectangle.getStartX(), rectangle.getEndY() - rectangle.getStartY());
            }
        }
    }
}
