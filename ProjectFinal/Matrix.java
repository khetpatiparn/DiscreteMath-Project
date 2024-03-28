import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Matrix extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    JLabel Head;

    public Matrix() {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);
        Head = new JLabel();
        Head.setText("Matrix");
        Head.setBounds(335, 20, 590, 60);
        Head.setOpaque(false);
        Head.setFont(new Font("Arial", Font.PLAIN, 40));
        Head.setForeground(Color.BLACK);
        add(Head);
    }
}
