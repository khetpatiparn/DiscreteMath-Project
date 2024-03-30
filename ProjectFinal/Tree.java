import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
    JButton changeGraphButton, KruskalButton, PrimButton, DijkButton, backButton;

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
        changeGraphButton = new JButton("Regenerate Graph");
        KruskalButton = new JButton("Kruskal's algorithm");
        PrimButton = new JButton("Prim's algorithm");
        DijkButton = new JButton("Dijkstra's algorithm");
        backButton = new JButton("Back");

        changeGraphButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        KruskalButton.setBounds(buttonX, buttonY + 80, buttonWidth, buttonHeight);
        PrimButton.setBounds(buttonX, buttonY + 160, buttonWidth, buttonHeight);
        DijkButton.setBounds(buttonX, buttonY + 240, buttonWidth, buttonHeight);
        backButton.setBounds(buttonX, buttonY + 320, buttonWidth, buttonHeight);

        // Add Buttons to display
        add(changeGraphButton);
        add(KruskalButton);
        add(PrimButton);
        add(DijkButton);
        add(backButton);

        // Event Handling Zone
        changeGraphButton.addActionListener(e -> repaint());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSidebar(g);
        // Draw random Vertex
        Font largerFont = new Font("Arial", Font.BOLD, 14);
        g.setFont(largerFont);
        g.setColor(Color.BLACK);
        
        int minX = 50;
        int minY = 50;
        int maxX = getWidth() - 300;
        int maxY = getHeight() - 50;
        int pointSize = 10;

        int[] drawnX = new int[20];
        int[] drawnY = new int[20];

        int numPoints = Integer.parseInt(graphSetup.getInputVertexField().getText());

        // Find the minimum vertex index
        int minVertexIndex = Integer.MAX_VALUE;
        for (Edge edge : graphSetup.getEdgeList()) {
            minVertexIndex = Math.min(minVertexIndex, Math.min(edge.getSrc(), edge.getDst()));
        }

        for (int i = 0; i < numPoints; i++) {
            int randomX, randomY;
            do {
                randomX = (int) (Math.random() * (maxX - minX)) + minX;
                randomY = (int) (Math.random() * (maxY - minY)) + minY;
            } while (isTooClose(randomX, randomY, drawnX, drawnY, i));

            drawnX[i] = randomX;
            drawnY[i] = randomY;

            g.fillOval(randomX, randomY, pointSize, pointSize);
            
            String label = "v" + (i + minVertexIndex); // Adjust the label to start from v1
            g.drawString(label, randomX + pointSize, randomY);
        }

        // Draw Edge-endpoint
        g.setColor(Color.BLUE);
        List<Edge> edgelst = graphSetup.getEdgeList(); // Store the connections between points

        for (Edge edge : edgelst) {
            int startX = drawnX[edge.getSrc() - minVertexIndex] + pointSize / 2; // Adjust the index to start from 0
            int startY = drawnY[edge.getSrc() - minVertexIndex] + pointSize / 2;
            int endX = drawnX[edge.getDst() - minVertexIndex] + pointSize / 2;
            int endY = drawnY[edge.getDst() - minVertexIndex] + pointSize / 2;
            g.drawLine(startX, startY, endX, endY);

            // Define label in Graph
            int labelX = (startX + endX) / 2;
            int labelY = (startY + endY) / 2 - 10;
            g.drawString(edge.getWeight() + "", labelX, labelY);
            // System.out.println(edge.getWeight());
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

    private void drawSidebar(Graphics g) {
        int mainRectWidth = 250;
        int mainRectHeight = 600;
        int mainRectX = getWidth() - mainRectWidth;
        int mainRectY = (getHeight() - mainRectHeight) / 2;
    
        g.setColor(Color.RED);
        g.fillRect(mainRectX, mainRectY, mainRectWidth, mainRectHeight);
    }
}