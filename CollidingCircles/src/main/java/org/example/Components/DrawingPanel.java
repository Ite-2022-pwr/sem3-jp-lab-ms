package org.example.Components;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;
import java.util.Set;
import java.util.HashSet;

// DRAWING PANEL - SIZE, LOCATION, COLOR, THREADS MANAGING CREATED CIRCLES
public class DrawingPanel extends JPanel {

    private final List<CircleThread> circleThreads = new ArrayList<>();

    // creating circle size
    private final int circleSize = 50;

    // creating the last of the circles
    private Circle selectedCircle = null;

    // pressed keys
    private final Set<Integer> pressedKeys = new HashSet<>();

    // available colors for the circles
    private final Color[] availableColors = {
            Color.decode("#318237"), Color.decode("#90D896"), Color.decode("#BAEBBE"),
            Color.decode("#8C3ABC"), Color.decode("#C397DC"), Color.decode("#DCBDEE"),
            Color.decode("#5394B3"), Color.decode("#93BAD7"), Color.decode("#C0D9EC")
    };

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

        // mouse listener to set focus and select circle on click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();

                // finding the clicked circle
                for (CircleThread circleThread : circleThreads) {
                    if (circleThread.getCircle().containsPoint(e.getPoint())) {
                        selectedCircle = circleThread.getCircle();
                        break;
                    }
                }

                if (selectedCircle != null) {
                    selectedCircle.setActive(true);
                }
            }
        });

        // key listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                pressedKeys.add(keyCode);
                moveSelectedCircle(keyCode, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                pressedKeys.remove(keyCode);
                moveSelectedCircle(keyCode, false);
            }
        });
    }

    // creating circle
    public void createCircle() {
        // random location x
        int randomX = getRandomLocation(getWidth() - circleSize);

        // random location y
        int randomY = getRandomLocation(getHeight() - circleSize);

        // random color
        Color randomColor = getRandomColorFromList();

        // creating a new circle
        Circle circle = new Circle(randomX, randomY, randomColor);

        // starting a new thread when the mouse is pressed
        CircleThread circleThread = new CircleThread(circle, DrawingPanel.this);
        circleThreads.add(circleThread);
        circleThread.start();
    }

    // creating a random location of the circle
    private int getRandomLocation(int max) {
        Random random = new Random();
        int margin = 20;
        return margin + random.nextInt(max - 2 * margin);
    }

    // painting the circle
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (CircleThread circleThread : circleThreads) {
            Circle circle = circleThread.getCircle();

            g.setColor(circle.getColor());
            g.fillOval(circle.getX(), circle.getY(), circleSize, circleSize);
        }
    }

    // moving the circle with keyboard
    void moveCircle(Circle circle) {
        if (circle.isActive()) {
            switch (circle.getDirection()) {
                case KeyEvent.VK_UP -> circle.setY(circle.getY() - 5);
                case KeyEvent.VK_DOWN -> circle.setY(circle.getY() + 5);
                case KeyEvent.VK_LEFT -> circle.setX(circle.getX() - 5);
                case KeyEvent.VK_RIGHT -> circle.setX(circle.getX() + 5);
            }

            // checking for collisions
            checkCollisions(circle);
        }
    }
    private void checkCollisions(Circle circle) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // checking for collisions with walls
        if (circle.getX() < 0 || circle.getX() > panelWidth - circleSize ||
                circle.getY() < 0 || circle.getY() > panelHeight - circleSize) {
            circle.setActive(false);
        }

        // checking for collisions with other circles
        for (CircleThread otherCircleThread : circleThreads) {
            if (otherCircleThread.getCircle() != circle && circle.intersects(otherCircleThread.getCircle())) {
                circle.setActive(false);
            }
        }
    }


    // checking if the circle is within the boundaries
    void checkBoundary(Circle circle) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (circle.getX() < 0 || circle.getX() > panelWidth - circleSize ||
                circle.getY() < 0 || circle.getY() > panelHeight - circleSize) {
            circle.setActive(false);
        }
    }

    // getting random color for the circle
    private Color getRandomColorFromList() {
        Random random = new Random();
        int randomIndex = random.nextInt(availableColors.length);
        return availableColors[randomIndex];
    }

    // moving the selected circle with the keyboard
    public void moveSelectedCircle(int keyCode, boolean keyPressed) {
        if (selectedCircle != null) {
            // checking if the key is pressed or released
            if (keyPressed) {
                // setting the direction based on the pressed key
                if (keyCode == KeyEvent.VK_UP) {
                    selectedCircle.setDirection(KeyEvent.VK_UP);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    selectedCircle.setDirection(KeyEvent.VK_DOWN);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    selectedCircle.setDirection(KeyEvent.VK_LEFT);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    selectedCircle.setDirection(KeyEvent.VK_RIGHT);
                }
            } else {
                // if the key is released, stop the movement
                selectedCircle.setDirection(0);
            }

            // move the circle outside the key event handling
            moveCircle(selectedCircle);

            // repainting the drawing panel
            SwingUtilities.invokeLater(this::repaint);
        }
    }
}