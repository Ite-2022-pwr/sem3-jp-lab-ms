package org.example.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// BUTTON TO CREATE CIRCLES - TEXT, COLOR, FONT, MOUSE LISTENER
public class CreatingCirclesButton extends JButton {
    private final DrawingPanel drawingPanel;

    // CreatingCirclesButton constructor
    public CreatingCirclesButton(DrawingPanel drawingPanel) {
        super();
        setText("dodaj kolko"); // text
        Color colorPink2 = Color.decode("#D84F74");
        setForeground(colorPink2); // color
        Font font2 = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25);
        setFont(font2); // font

        this.drawingPanel = drawingPanel;

        addMouseListener(new ButtonMouseListener()); // mouse listener
    }

    class ButtonMouseListener extends MouseAdapter {
        // creating a circle when the button is clicked
        @Override
        public void mouseClicked(MouseEvent e) {
            drawingPanel.createCircle();
            drawingPanel.repaint();
        }
    }
}
