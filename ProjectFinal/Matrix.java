import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Matrix extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    JLabel matrix_title;

    public Matrix() {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);
        matrix_title = new JLabel();
        matrix_title.setText("Adjacency Matrix");
        matrix_title.setBounds(242, 20, 590, 60);
        matrix_title.setOpaque(false);
        matrix_title.setFont(new Font("Arial", Font.PLAIN, 40));
        matrix_title.setForeground(Color.BLACK);
        add(matrix_title);
    }
}
