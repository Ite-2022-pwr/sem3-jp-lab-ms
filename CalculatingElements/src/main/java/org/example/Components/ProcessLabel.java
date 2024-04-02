package org.example.Components;
import javax.swing.*;
import java.awt.*;

// LABEL HEADER - NAME, FONT, COLOR, SIZE
public class ProcessLabel extends JLabel {

    // ProcessLabel constructor
    public ProcessLabel() {
        super();
        Font font2 = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);
        setFont(font2); // font
        setHorizontalAlignment(SwingConstants.CENTER); // location of the text
        Color colorPink2 = Color.decode("#D84F74");
        setForeground(colorPink2); // color
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(7, 40, 7, 40)); // border and size
        setPreferredSize(new Dimension(400, 40));
    }
}
