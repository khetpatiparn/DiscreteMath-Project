import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tree extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    JLabel Head;
    JButton KButton, PButton, DButton, Back;

    // Store the connections between points
    private Point[] connections = {
            new Point(0, 1), // Connect v1 to v2
            new Point(1, 2), // Connect v2 to v3
            new Point(2, 3), // Connect v3 to v4
            new Point(3, 4), // Connect v4 to v5
            new Point(0, 2), // Connect v1 to v3
            new Point(1, 3) // Connect v2 to v4
    };

    public Tree() {
        setPreferredSize(FRAME_SIZE);
        setLayout(null);
        setBackground(Color.WHITE);

        // JLabel
        Head = new JLabel("Select Algorithm");
        Head.setBounds(555, 50, 590, 60);
        Head.setOpaque(false);
        Head.setFont(new Font("Arial", Font.PLAIN, 30));
        Head.setForeground(Color.WHITE);
        add(Head);

        // Create Buttons
        KButton = new JButton("Kruskal's algorithm");
        PButton = new JButton("Prim's algorithm");
        DButton = new JButton("Dijkstra's algorithm");
        Back = new JButton("Back");

        // Set Button sizes and locations
        int buttonWidth = 200;
        int buttonHeight = 50;
        int BXaxis = 565;
        int BYaxis = 150;

        KButton.setBounds(BXaxis, BYaxis, buttonWidth, buttonHeight);
        PButton.setBounds(BXaxis, BYaxis + 100, buttonWidth, buttonHeight);
        DButton.setBounds(BXaxis, BYaxis + 200, buttonWidth, buttonHeight);
        Back.setBounds(BXaxis, BYaxis + 300, buttonWidth, buttonHeight);

        // Add Buttons to display
        add(KButton);
        add(PButton);
        add(DButton);
        add(Back);

        // Implement Button Listeners (Placeholders for Algorithms)
        KButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement Kruskal's Algorithm here
                // Modify drawing in paintComponent as needed
                repaint();
            }
        });

        PButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement Prim's Algorithm here
                // Modify drawing in paintComponent as needed
                repaint();
            }
        });

        DButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement Dijkstra's Algorithm here
                // Modify drawing in paintComponent as needed
                repaint();
            }
        });

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int mainRectWidth = 250;
        int mainRectHeight = 600;
        int mainRectX = getWidth() - mainRectWidth;
        int mainRectY = (getHeight() - mainRectHeight) / 2;

        g.setColor(Color.RED);
        g.fillRect(mainRectX, mainRectY, mainRectWidth, mainRectHeight);

        // Draw random points outside the red rectangle
        g.setColor(Color.BLACK);

        int minX = 10;
        int minY = 10;
        int maxX = mainRectX - 10;
        int maxY = getHeight() - 10;

        int pointSize = 10;

        int[] drawnX = new int[20];
        int[] drawnY = new int[20];

        int numPoints = 5;
        for (int i = 0; i < numPoints; i++) {
            int randomX, randomY;
            do {
                randomX = (int) (Math.random() * (maxX - minX)) + minX;
                randomY = (int) (Math.random() * (maxY - minY)) + minY;
            } while (isTooClose(randomX, randomY, drawnX, drawnY, i));

            drawnX[i] = randomX;
            drawnY[i] = randomY;

            g.fillOval(randomX, randomY, pointSize, pointSize);
            String label = "v" + (i + 1);
            g.drawString(label, randomX + pointSize, randomY);
        }

        // Draw lines between connected points
        g.setColor(Color.BLUE);
        Set<String> usedLabels = new HashSet<>();

        for (Point connection : connections) {
            int startX = drawnX[connection.x] + pointSize / 2;
            int startY = drawnY[connection.x] + pointSize / 2;
            int endX = drawnX[connection.y] + pointSize / 2;
            int endY = drawnY[connection.y] + pointSize / 2;
            g.drawLine(startX, startY, endX, endY);

            // Generate a unique label
            String label;
            int labelNum = 1;
            do {
                label = "e" + labelNum++;
            } while (usedLabels.contains(label));

            usedLabels.add(label);

            int labelX = (startX + endX) / 2;
            int labelY = (startY + endY) / 2 - 10;
            g.drawString(label, labelX, labelY);
        }
    }

    // Function to prevent points from being too close
    private boolean isTooClose(int x, int y, int[] drawnX, int[] drawnY, int numDrawn) {
        for (int i = 0; i < numDrawn; i++) {
            double distance = Math.sqrt(Math.pow(x - drawnX[i], 2) + Math.pow(y - drawnY[i], 2));
            if (distance < 40) {
                return true;
            }
        }
        return false;
    }
}