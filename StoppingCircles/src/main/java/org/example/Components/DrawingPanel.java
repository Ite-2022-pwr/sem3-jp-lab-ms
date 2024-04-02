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
    private Circle lastAddedCircle = null;

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

        // mouse listener to be able to set focus on drawing panel to move the newest circle
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
                if (lastAddedCircle != null) {
                    lastAddedCircle.setActive(true);
                }
            }
        });

        // key listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                pressedKeys.add(keyCode);
                moveLastAddedCircle(keyCode, true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                pressedKeys.remove(keyCode);
                moveLastAddedCircle(keyCode, false);
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
        lastAddedCircle = new Circle(randomX, randomY, randomColor);

        // starting a new thread when the mouse is pressed
        CircleThread circleThread = new CircleThread(lastAddedCircle, DrawingPanel.this);
        circleThreads.add(circleThread);
        circleThread.start();
    }


    // creating a random location of the circle
    private int getRandomLocation(int max) {
        Random random = new Random();
        int margin = 20;
        return margin + random.nextInt(max - 2 * margin);
    }

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
        }
    }

    // checking if the circle is within the boundaries
    void checkBoundary(Circle circle) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (circle.getX() < 0 || circle.getX() > panelWidth - circleSize ||
                circle.getY() < 0 || circle.getY() > panelHeight - circleSize) {
            circle.setActive(false);

            // finding the index of the current circle in the list
            int currentIndex = -1;

            // Iterate through the circleThreads to find the index of the current circle
            for (int i = 0; i < circleThreads.size(); i++) {
                if (circleThreads.get(i).getCircle() == circle) {
                    currentIndex = i;
                    break;
                }
            }

            // if the current circle is not the first one
            if (currentIndex > 0) {
                // setting the last added circle to be the one before the current circle
                lastAddedCircle = circleThreads.get(currentIndex - 1).getCircle();
            } else {
                // if the current circle is the first one, set lastAddedCircle to null
                lastAddedCircle = null;
            }
        }
    }

    // getting random color for the circle
    private Color getRandomColorFromList() {
        Random random = new Random();
        int randomIndex = random.nextInt(availableColors.length);
        return availableColors[randomIndex];
    }

    // moving the newest circle with the keyboard
    public void moveLastAddedCircle(int keyCode, boolean keyPressed) {
        if (lastAddedCircle != null) {
            // check if the key is pressed or released
            if (keyPressed) {
                // set the direction based on the pressed key
                if (keyCode == KeyEvent.VK_UP) {
                    lastAddedCircle.setDirection(KeyEvent.VK_UP);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    lastAddedCircle.setDirection(KeyEvent.VK_DOWN);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    lastAddedCircle.setDirection(KeyEvent.VK_LEFT);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    lastAddedCircle.setDirection(KeyEvent.VK_RIGHT);
                }
            } else {
                // if the key is released, stop the movement
                lastAddedCircle.setDirection(0);
            }

            // repainting the drawing panel
            SwingUtilities.invokeLater(this::repaint);
        }
    }
}