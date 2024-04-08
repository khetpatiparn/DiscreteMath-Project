import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleScreen extends JPanel {
    // Window Screen Size
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    
    // Declare Component
    // Label
    private JLabel lbprojectName;
    // Button
    protected JLabel lbTree, lbFinite, lbExit, lbCredit;

    // Constructor to setup Component and Event handler
    public TitleScreen() {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);

        // Project-Discretemath Label
        lbprojectName = new JLabel();
        lbprojectName.setText("Discretemath-Project");
        lbprojectName.setBounds(120, 50, 590, 60);
        lbprojectName.setOpaque(false);
        lbprojectName.setFont(new Font("Arial", Font.PLAIN, 60));
        lbprojectName.setForeground(Color.BLACK);
        add(lbprojectName);

        // Tree Button
        lbTree = new JLabel();
        lbTree.setText("TREE");
        lbTree.setBounds(340, 190, 120, 60);
        lbTree.setOpaque(true);
        lbTree.setFont(new Font("Arial", Font.PLAIN, 40));
        lbTree.setForeground(Color.BLACK);
        add(lbTree);

        // Finite state automata Button
        lbFinite = new JLabel();
        lbFinite.setText("FINITE-STATE AUTOMATA");
        lbFinite.setBounds(145, 300, 520, 60);
        lbFinite.setOpaque(true);
        lbFinite.setFont(new Font("Arial", Font.PLAIN, 40));
        lbFinite.setForeground(Color.BLACK);
        add(lbFinite);

        // Exit Button
        lbExit = new JLabel();
        lbExit.setText("Exit");
        lbExit.setBounds(359, 430, 70, 60);
        lbExit.setOpaque(true);
        lbExit.setFont(new Font("Arial", Font.PLAIN, 40));
        lbExit.setForeground(Color.BLACK);
        add(lbExit);

        // Credits Button
        lbCredit = new JLabel();
        lbCredit.setText("Credits");
        lbCredit.setBounds(30, 500, 70, 60);
        lbCredit.setOpaque(true);
        lbCredit.setFont(new Font("Arial", Font.PLAIN, 20));
        lbCredit.setForeground(Color.BLACK);
        add(lbCredit);

        addMouseMotionListener(new MousePositionCheck());
    }
    
    // Inner Class for Event Handler Section
    private class MousePositionCheck extends MouseAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
        // Check mouse's position
        // System.out.println("mouseX:" + e.getX() + ", mouseY:" + e.getY());
        }
    }
}