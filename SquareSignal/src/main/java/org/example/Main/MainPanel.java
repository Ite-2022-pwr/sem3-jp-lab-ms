package org.example.Main;
import org.example.Components.ComponentsPanel;
import org.example.Components.DrawingPanel;
import org.example.Components.Header;
import javax.swing.*;
import java.awt.*;

// MAIN PANEL WITH ALL COMPONENTS
public class MainPanel extends JPanel {
    private final DrawingPanel drawingPanel1;
    private final DrawingPanel drawingPanel2;
    private final DrawingPanel drawingPanel3;

    // MainPanel constructor
    public MainPanel() {
        setPreferredSize(new Dimension(1000, 600));
        Color colorPink1 = Color.decode("#FAD9E7");
        setBackground(colorPink1);
        setLayout(null);

        drawingPanel1 = new DrawingPanel(300, 400, 25, 100, 50, 2, 20);
        drawingPanel2 = new DrawingPanel(300, 400, 350, 100, 20, 5, 30);
        drawingPanel3 = new DrawingPanel(300, 400, 675, 100, 0, 0, 0);

        ComponentsPanel buttonPanel = new ComponentsPanel(0, 520);
        ComponentsPanel headerPanel = new ComponentsPanel(0, 0);
        Header header = new Header();

        add(drawingPanel1);
        add(drawingPanel2);
        add(drawingPanel3);
        add(buttonPanel);
        add(headerPanel);
        headerPanel.add(header);

        // first thread
        SwingWorker<Void, Void> worker1 = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (true) {
                    drawingPanel1.setXOffset(drawingPanel1.getXOffset() + 1);
                    publish();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void process(java.util.List<Void> chunks) {
                drawingPanel1.repaint();
            }
        };
        worker1.execute();

        // second thread
        SwingWorker<Void, Void> worker2 = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (true) {
                    drawingPanel2.setXOffset(drawingPanel2.getXOffset() + 1);
                    publish();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void process(java.util.List<Void> chunks) {
                drawingPanel2.repaint();
            }
        };
        worker2.execute();

        // third thread
        SwingWorker<Void, Void> worker3 = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                while (true) {
                    drawingPanel3.setAmplitude(drawingPanel1.getAmplitude() + drawingPanel2.getAmplitude());
                    drawingPanel3.setFrequency(drawingPanel1.getFrequency() + drawingPanel2.getFrequency());
                    drawingPanel3.setFillPercentage(drawingPanel1.getFillPercentage() + drawingPanel2.getFillPercentage());
                    drawingPanel3.setXOffset(drawingPanel1.getXOffset() + drawingPanel2.getXOffset());
                    publish();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            protected void process(java.util.List<Void> chunks) {
                drawingPanel3.repaint();
            }
        };
        worker3.execute();
    }
}
