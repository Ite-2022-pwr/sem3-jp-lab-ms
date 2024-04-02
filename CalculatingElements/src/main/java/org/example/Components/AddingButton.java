package org.example.Components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// BUTTON TO ADD CALCULATING ELEMENTS - TEXT, COLOR, FONT, MOUSE LISTENER
public class AddingButton extends JButton {
    private final DrawingPanel drawingPanel;

    // RadioButton constructor
    public AddingButton(String type, String text, DrawingPanel drawingPanel) {
        super();
        setText(text); // text
        Color colorPink2 = Color.decode("#D84F74");
        setForeground(colorPink2); // color
        Font font2 = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25);
        setFont(font2); // font

        this.drawingPanel = drawingPanel;

        // different mouse listener for circle and for square
        if(type.equals("c")) {
            addMouseListener(new CircleButtonMouseListener());
        } else if (type.equals("s")){
            addMouseListener(new SquareButtonMouseListener());
        }
    }
    class CircleButtonMouseListener extends MouseAdapter {
        // creating a circle when the button is clicked
        @Override
        public void mouseClicked(MouseEvent e) {
            drawingPanel.addCircle();
            drawingPanel.repaint();
        }
    }

    class SquareButtonMouseListener extends MouseAdapter {
        // creating a circle when the button is clicked
        @Override
        public void mouseClicked(MouseEvent e) {
            drawingPanel.addSquare();
            drawingPanel.repaint();
        }
    }
}

