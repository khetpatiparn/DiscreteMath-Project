import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Title extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    JLabel title_1, title_2, title_3, title_4, title_5;

    public Title() {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);

        // Screen Head Label
        title_1 = new JLabel();
        title_1.setText("Project-Discretemath");
        title_1.setBounds(120, 50, 590, 60);
        title_1.setOpaque(false);
        title_1.setFont(new Font("Arial", Font.PLAIN, 60));
        title_1.setForeground(Color.BLACK);
        add(title_1);

        // Tree Label
        title_2 = new JLabel();
        title_2.setText("TREE");
        title_2.setBounds(340, 190, 590, 60);
        title_2.setOpaque(false);
        title_2.setFont(new Font("Arial", Font.PLAIN, 40));
        title_2.setForeground(Color.BLACK);
        title_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Code to navigate to the tree page
                // Add your navigation logic here
                System.out.println("Navigating to the tree page...");
            }
        });
        add(title_2);

        // FNA Label
        title_3 = new JLabel();
        title_3.setText("FINITE-STATE AUTOMATA");
        title_3.setBounds(145, 300, 590, 60);
        title_3.setOpaque(false);
        title_3.setFont(new Font("Arial", Font.PLAIN, 40));
        title_3.setForeground(Color.BLACK);
        add(title_3);

        title_4 = new JLabel();
        title_4.setText("Exit");
        title_4.setBounds(359, 430, 590, 60);
        title_4.setOpaque(false);
        title_4.setFont(new Font("Arial", Font.PLAIN, 40));
        title_4.setForeground(Color.BLACK);
        title_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Code to exit the program
                System.exit(0);
            }
        });
        add(title_4);

        title_5 = new JLabel();
        title_5.setText("Credits");
        title_5.setBounds(30, 500, 600, 60);
        title_5.setOpaque(false);
        title_5.setFont(new Font("Arial", Font.PLAIN, 20));
        title_5.setForeground(Color.BLACK);
        add(title_5);

    
    

    }
 }
// Inner Class for Event Handler Section