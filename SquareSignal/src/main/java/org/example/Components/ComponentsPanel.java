package org.example.Components;
import javax.swing.*;
import java.awt.*;

// COMPONENT PANEL TO GIVE EACH COMPONENT A PANEL - SIZE, LOCATION, COLOR
public class ComponentsPanel extends JPanel {
    // ComponentsPanel constructor
    public ComponentsPanel(int x, int y) {
        // creating a button panel
        super();
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setSize(1000, 80); // size
        setLocation(x, y); // location
        setOpaque(true);
        Color colorPink3 = Color.decode("#F7BAD3");
        setBackground(colorPink3); // color
    }
}
