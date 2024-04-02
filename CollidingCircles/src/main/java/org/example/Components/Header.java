package org.example.Components;
import javax.swing.*;
import java.awt.*;

// LABEL HEADER - NAME, FONT, COLOR, SIZE
public class Header extends JLabel {
    // Header constructor
    public Header() {
        super();
        setText("Dodawaj kolka przyciskiem i kliknij myszka w kanwe aby przesuwac ostatnie z nich strzalkami"); // text
        Font font2 = new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20);
        setFont(font2); // font
        setHorizontalAlignment(SwingConstants.CENTER); // location of the text
        Color colorPink2 = Color.decode("#D84F74");
        setForeground(colorPink2); // color
        Color colorPink1 = Color.decode("#FAD9E7");
        setBackground(colorPink1); // background color
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(7, 40, 7, 40)); // border and size
    }
}
