package org.example.Main;

import org.example.Components.*;

import javax.swing.*;
import java.awt.*;

// MAIN PANEL WITH ALL COMPONENTS
public class MainPanel extends JPanel {

    // Panel constructor
    public MainPanel() {
        // size of the panel
        setPreferredSize(new Dimension(1000, 600));

        // color of the background
        Color colorPink1 = Color.decode("#FAD9E7"); // color
        setBackground(colorPink1);

        // setting layout to null to be able to add components using precise location
        setLayout(null);

        // creating components and adding them to the panels
        ProcessLabel processLabel = new ProcessLabel();  // Create ProcessLabel instance first
        DrawingPanel drawingPanel = new DrawingPanel(processLabel);
        ComponentsPanel buttonPanel = new ComponentsPanel(0, 520);
        ComponentsPanel headerPanel = new ComponentsPanel(0, 0);
        Header header = new Header();
        AddingButton circleButton = new AddingButton("c", "dodaj kolko", drawingPanel);
        AddingButton squareButton = new AddingButton("s", "dodaj kwadrat", drawingPanel);
        add(drawingPanel);
        add(buttonPanel);
        add(headerPanel);
        headerPanel.add(header);
        buttonPanel.add(circleButton);
        buttonPanel.add(squareButton);
        buttonPanel.add(processLabel);
    }
}
