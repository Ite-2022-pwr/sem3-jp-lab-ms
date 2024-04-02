package org.example.Main;
import org.example.Components.ComponentsPanel;
import org.example.Components.DrawingPanel;
import org.example.Components.Header;
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
        DrawingPanel drawingPanel = new DrawingPanel();

        // creating and adding components to the panel
        ComponentsPanel buttonPanel = new ComponentsPanel(0, 520);
        ComponentsPanel headerPanel = new ComponentsPanel(0, 0);
        Header header = new Header();
        add(drawingPanel);
        add(buttonPanel);
        add(headerPanel);
        headerPanel.add(header);
    }
}
