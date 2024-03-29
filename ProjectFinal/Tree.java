import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tree extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    // Label
    JLabel selectAlgoLabel;
    // Button
    JButton KruskalButton, PrimButton, DijkButton, backButton;

    // Set Button sizes and locations
    int buttonWidth = 200;
    int buttonHeight = 50;
    int buttonX = 565;
    int buttonY = 150;

    GraphSetupScreen graphSetup;

    public Tree(GraphSetupScreen graphSetup) {
        System.out.println("Constructor from Tree.java");
        this.graphSetup = graphSetup;


        setPreferredSize(FRAME_SIZE);
        setLayout(null);
        setBackground(Color.WHITE);

        // Select Algo Label 
        selectAlgoLabel = new JLabel("Select Algorithm");
        selectAlgoLabel.setBounds(555, 50, 590, 60);
        selectAlgoLabel.setOpaque(false);
        selectAlgoLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        selectAlgoLabel.setForeground(Color.WHITE);
        add(selectAlgoLabel);

        // Create Buttons
        KruskalButton = new JButton("Kruskal's algorithm");
        PrimButton = new JButton("Prim's algorithm");
        DijkButton = new JButton("Dijkstra's algorithm");
        backButton = new JButton("Back");

        KruskalButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        PrimButton.setBounds(buttonX, buttonY + 100, buttonWidth, buttonHeight);
        DijkButton.setBounds(buttonX, buttonY + 200, buttonWidth, buttonHeight);
        backButton.setBounds(buttonX, buttonY + 300, buttonWidth, buttonHeight);

        // Add Buttons to display
        add(KruskalButton);
        add(PrimButton);
        add(DijkButton);
        add(backButton);

        // Implement Button Listeners (Placeholders for Algorithms)
        KruskalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement Kruskal's Algorithm here
                // Modify drawing in paintComponent as needed
                repaint();
            }
        });

        PrimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        //         // Implement Prim's Algorithm here
        //         // Modify drawing in paintComponent as needed
                repaint();
            }
        });

        DijkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        //         // Implement Dijkstra's Algorithm here
        //         // Modify drawing in paintComponent as needed
        //         repaint();
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

        int minX = 50;
        int minY = 50;
        int maxX = mainRectX - 50;
        int maxY = getHeight() - 50;

        int pointSize = 10;

        int[] drawnX = new int[20];
        int[] drawnY = new int[20];

        int numPoints = Integer.parseInt(graphSetup.getInputVertexField().getText());
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
        
        // Store the connections between points
        List<Edge> edgelst = graphSetup.getEdgeList();

        for (Edge edge : edgelst) {
            int startX = drawnX[edge.getSrc()] + pointSize / 2;
            int startY = drawnY[edge.getSrc()] + pointSize / 2;
            int endX = drawnX[edge.getDst()] + pointSize / 2;
            int endY = drawnY[edge.getDst()] + pointSize / 2;
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