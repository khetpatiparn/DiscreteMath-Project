package Graph;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Tree extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    // Label
    JLabel selectAlgoLabel;
    // Button
    JButton changeGraphButton, KruskalButton, PrimButton, DijkButton;
    public JButton backButton;

    // Set Button sizes and locations
    int buttonWidth = 200;
    int buttonHeight = 50;
    int buttonX = 565;
    int buttonY = 150;

    GraphSetupScreen graphSetup;
    List<Edge> edgelst;
    int countOfVertex;

    List<VertexPos> verticesPositionList = new ArrayList<>();
    List<EdgePos> egdesPositionList = new ArrayList<>();

    public Tree(GraphSetupScreen graphSetup) {
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
        addMouseMotionListener(new MousePositionCheck());

        PrimButton.addActionListener(new PrimButton());
        KruskalButton.addActionListener(new KruskalAlgo());
        DijkButton.addActionListener(new inputSrcAction());

        backButton.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent e) {

                // Remove the JComboBox and input box from the panel

                for (Component component : getComponents()) {

                    if (component instanceof JComboBox || component instanceof JTextField) {

                        remove(component);

                    }

                }

    

                // Move the selectAlgoLabel and buttons back to their original position

                selectAlgoLabel.setBounds(555, 50, 590, 60);

                changeGraphButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);

                KruskalButton.setBounds(buttonX, buttonY + 80, buttonWidth, buttonHeight);

                PrimButton.setBounds(buttonX, buttonY + 160, buttonWidth, buttonHeight);

                DijkButton.setBounds(buttonX, buttonY + 240, buttonWidth, buttonHeight);

                backButton.setBounds(buttonX, buttonY + 320, buttonWidth, buttonHeight);

    

                // Repaint the panel to reflect the changes

                repaint();

            }

        });

    }



   

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // for smoothly
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

        int[] drawnPointX = new int[Integer.parseInt(graphSetup.getInputVertexField().getText())];
        int[] drawnPointY = new int[Integer.parseInt(graphSetup.getInputVertexField().getText())];

        countOfVertex = Integer.parseInt(graphSetup.getInputVertexField().getText());

        // Find the minimum vertex index
        int minVertexIndex = Integer.MAX_VALUE;

        for (Edge edge : graphSetup.getEdgeList()) {
            minVertexIndex = Math.min(minVertexIndex, Math.min(edge.getSrc(), edge.getDst()));
        }

        for (int i = 0; i < countOfVertex; i++) {
            int randomX, randomY;
            do {
                randomX = (int) (Math.random() * (maxX - minX)) + minX;
                randomY = (int) (Math.random() * (maxY - minY)) + minY;
            } while (isTooClose(randomX, randomY, drawnPointX, drawnPointY, i));

            drawnPointX[i] = randomX;
            drawnPointY[i] = randomY;

            g.fillOval(randomX, randomY, pointSize, pointSize);

            String label = "v" + (i + minVertexIndex); // Adjust the label to start from v1
            g.drawString(label, randomX + pointSize, randomY);
            // System.out.println((i + 1) + " X : "+ drawnPointX[i] + " , Y : " +
            // drawnPointY[i]);
            verticesPositionList.add(new VertexPos(randomX, randomY));
        }
        //System.out.println(verticesPositionList);

        // Draw Edge-endpoint
        g.setColor(Color.BLUE);
        edgelst = graphSetup.getEdgeList(); // Store the connections between points

        for (Edge edge : edgelst) {
            int startX = drawnPointX[edge.getSrc() - minVertexIndex] + pointSize / 2; // Adjust the index to start from
                                                                                      // 0
            int startY = drawnPointY[edge.getSrc() - minVertexIndex] + pointSize / 2;
            int endX = drawnPointX[edge.getDst() - minVertexIndex] + pointSize / 2;
            int endY = drawnPointY[edge.getDst() - minVertexIndex] + pointSize / 2;
            g.drawLine(startX, startY, endX, endY);
            //egdesPositionList.add(new EdgePos(startX, startY, endX, endY));

            // Define label in Graph
            int labelX = (startX + endX) / 2;
            int labelY = (startY + endY) / 2 - 10;
            g.drawString(edge.getWeight() + "", labelX, labelY);
            // System.out.println(edge.getWeight());
        }
        //System.out.println(egdesPositionList);
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
        int mainRectY = 0; // fix

        g.setColor(Color.RED);
        g.fillRect(mainRectX, mainRectY, mainRectWidth, mainRectHeight);
    }

    // Inner Class for Event Handler Section
    private class MousePositionCheck extends MouseAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            // Check mouse's position
            // System.out.println("mouseX:" + e.getX() + ", mouseY:" + e.getY());
        }
    }

    private class PrimButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GraphMatrix gmt = new GraphMatrix(countOfVertex);
            for (Edge item : edgelst) {
                gmt.addEdges(item);
            }
            Prim prims = new Prim(gmt);

            List<Edge> mstEdgesprim = prims.findMST();
            StringBuilder resultBuilder = new StringBuilder("MST Edges (Prim's Algorithm):\n");
            System.out.println("MST Edges Prim's algorithms:");
            for (Edge edge : mstEdgesprim) {
                System.out
                        .println(edge.getName() + ": Src-" + edge.getSrc() + ", Dst-" + edge.getDst() + ", Weight:"
                                + edge.getWeight());
            }

            for (Edge edge : mstEdgesprim) {
                resultBuilder.append(edge.getName())
                        .append(": Src-")
                        .append(edge.getSrc())
                        .append(", Dst-")
                        .append(edge.getDst())
                        .append(", Weight:")
                        .append(edge.getWeight())
                        .append("\n");
            }

            // Create the scrollable area for the results
            JTextArea textArea = new JTextArea(resultBuilder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350, 200));

            // Show the results in a JOptionPane
            JOptionPane.showMessageDialog(Tree.this, scrollPane, "Prim Results", JOptionPane.PLAIN_MESSAGE);
        }
    };

    private class KruskalAlgo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            GraphMatrix gmt = new GraphMatrix(countOfVertex);
            for (Edge item : edgelst) {
                gmt.addEdges(item);
            }
            System.out.println("Kruskal Algo button 's clicked");
            List<Edge> mstEdgesKrus = Kruskal.findMST(gmt);
            StringBuilder resultBuilder = new StringBuilder("MST Edges (Kruskal's Algorithm):\n");
            System.out.println("MST Edges Kruskal's Algorithm:");
            for (Edge edge : mstEdgesKrus) {
                System.out.println(edge.getName() + ": Src-" + edge.getSrc() + ", Dst-" + edge.getDst() + ", Weight:"
                        + edge.getWeight());
            }

            for (Edge edge : mstEdgesKrus) {
                resultBuilder.append(edge.getName())
                        .append(": Src-")
                        .append(edge.getSrc())
                        .append(", Dst-")
                        .append(edge.getDst())
                        .append(", Weight:")
                        .append(edge.getWeight())
                        .append("\n");
            }

            // Create the scrollable area for the results
            JTextArea textArea = new JTextArea(resultBuilder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350, 200));

            // Show the results in a JOptionPane
            JOptionPane.showMessageDialog(Tree.this, scrollPane, "Kruskal Results", JOptionPane.PLAIN_MESSAGE);
        }
    };
     //Input source for Dijkstra

     public class inputSrcAction implements ActionListener {

        @Override

        public void actionPerformed(ActionEvent e) {

            // Move the existing buttons and labels up

            selectAlgoLabel.setBounds(555, 15, 590, 60);

            changeGraphButton.setBounds(buttonX, buttonY - 50, buttonWidth, buttonHeight);

            KruskalButton.setBounds(buttonX, buttonY + 30, buttonWidth, buttonHeight);

            PrimButton.setBounds(buttonX, buttonY + 110, buttonWidth, buttonHeight);

            DijkButton.setBounds(buttonX, buttonY + 190, buttonWidth, buttonHeight);

            backButton.setBounds(buttonX, buttonY + 320, buttonWidth, buttonHeight);



            // Vertex selection in JComboBox

            String vertexCnt = GraphSetupScreen.inputVertexField.getText();

            int ver = Integer.parseInt(vertexCnt);

            String[] vertices = new String[ver + 1]; // Add one for the default option

            vertices[0] = "--- source vertex ---"; // Default option

            for (int i = 1; i <= ver; i++) {

                vertices[i] = "v" + i;

            }



            // Create JComboBox for source input

            JComboBox<String> vertexComboBox = new JComboBox<>(vertices);

            vertexComboBox.setBounds(buttonX, buttonY + 270, buttonWidth, buttonHeight-20);

            add(vertexComboBox);



            // Assuming you have a JLabel for instructions

            JLabel instructionLabel = new JLabel("Enter source vertex:");

            instructionLabel.setBounds(buttonX, buttonY + 230, buttonWidth, buttonHeight);

            add(instructionLabel);



            // Add item listener to the JComboBox

            vertexComboBox.addItemListener(new ItemListener() {

                @Override

                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {

                        // Perform actions when an item is selected

                        if (vertexComboBox.getSelectedIndex() != 0) { // Check if it's not the default option

                            int selectedIndex = vertexComboBox.getSelectedIndex();

                            // Create a new object of Dijkstra and call its method

                            System.out.println("----------------------------");

                            System.out.println("Dijkstra Shortest Path : ");

                            Dijkstra djk = new Dijkstra(ver, GraphMatrix.showWeight, selectedIndex - 1); // Adjust index

                            djk.dijkstra();

                        }

                    }

                }

            });

        }
    }
}