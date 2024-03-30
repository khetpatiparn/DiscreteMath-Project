package Graph;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class GraphSetupScreen extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    GraphMatrix graphMatrix; 

    // Label Header
    private JLabel setupGraphLabel;

    // Label Form Section
    private JLabel numVertexLabel;
    private JLabel edgeLabel;
    private JLabel weightLabel;
    private JLabel toLabel;

    // Textfield
    JTextField inputVertexField;
    private JTextField inputWeightField;

    // CommbBox
    private JComboBox<String> fromVertexChoice;
    private JComboBox<String> toVertexChoice;

    // Button
    private JButton submitButton;
    public JButton clearButton;
    public JButton backButton;
    public JButton finishButton;

    // TextPane & ScrollPane
    private JTextPane dataLabel;
    private JScrollPane scrollPane;
    
    private int edgeCount = 1;

    private List<Edge> edgeList = new ArrayList<>();; // เก็บอ็อบเจ็กต์ Edge ที่สร้างขึ้น
    
    public GraphSetupScreen(GraphMatrix graphMatrix) {
        this.graphMatrix = graphMatrix;
        setPreferredSize(FRAME_SIZE);
        setLayout(null);
        setBackground(Color.WHITE);

        // Set Up Graph Label
        setupGraphLabel = new JLabel();
        setupGraphLabel.setText("Set Up Graph");
        setupGraphLabel.setBounds(260, 20, 280, 60);
        setupGraphLabel.setOpaque(false);
        setupGraphLabel.setFont(new Font("Arial", Font.BOLD, 40));
        setupGraphLabel.setForeground(Color.BLACK);
        add(setupGraphLabel);

        // Number of Vertex Label
        numVertexLabel = new JLabel("Number of vertex : ");
        numVertexLabel.setBounds(150, 100, 150, 30);
        numVertexLabel.setOpaque(false);
        numVertexLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        add(numVertexLabel);
        
        // Number of Vertex TextField
        inputVertexField = new JTextField();
        inputVertexField.setBounds(350, 100, 300, 30);
        add(inputVertexField);

        // Input Edge Label
        edgeLabel = new JLabel("Edge-Endpoint    : ");
        edgeLabel.setBounds(150, 150, 150, 30);
        edgeLabel.setOpaque(false);
        edgeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        // add(edgeLabel);

        // Word "to" Label
        toLabel = new JLabel("to");
        toLabel.setBounds(430, 150, 30, 30);
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        // add(toLabel);

        // Weight Label
        weightLabel = new JLabel("Input Weight        :");
        weightLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        weightLabel.setBounds(150, 200, 120, 30);
        // add(weightLabel);

        // Input Weight TextField
        inputWeightField = new JTextField();
        inputWeightField.setBounds(350, 200, 50, 30);
        // add(inputWeightField);
        
        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(420, 200, 100, 30);
        submitButton.setEnabled(false); // ตั้งค่าปุ่ม submit เป็นไม่สามารถใช้งานได้เริ่มต้น
        add(submitButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(550, 200, 100, 30);
        // add(clearButton);

        // Finish Button
        finishButton = new JButton("Finish");
        finishButton.setBounds(250, 490, 100, 30);
        finishButton.setEnabled(false); // Initially disable the finish button
        add(finishButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(430, 490, 100, 30);
        add(backButton);

        // TextPane
        dataLabel = new JTextPane();
        dataLabel.setOpaque(false);
        dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane = new JScrollPane(dataLabel);
        scrollPane.setBounds(150, 250, 500, 200); // เปลี่ยนขนาดของ JLabel เพื่อรองรับข้อความทั้งหมด
        // add(scrollPane);

        // Handler and Action
        inputVertexField.addActionListener(e -> createVertexChoices());
        submitButton.addActionListener(new submitAction());
        
        
        clearButton.addActionListener(new clearAction());
        backButton.addActionListener(new clearAction());

        addMouseMotionListener(new MousePositionCheck());

         // Add document listener to inputWeightField
        inputWeightField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkSubmitButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkSubmitButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkSubmitButtonState();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // สร้างอ็อบเจ็กต์ GraphMatrix
                GraphMatrix graphMatrix = new GraphMatrix(Integer.parseInt(inputVertexField.getText()), edgeList.size());
        
                // ส่งข้อมูลจาก edgeList ไปยัง GraphMatrix เพื่อสร้าง adjacency matrix และ weight matrix
                for (Edge edge : edgeList) {
                    graphMatrix.addEdges(edge.getName(), edge.getSrc(), edge.getDst(), edge.getWeight());
                }
        
                // ทำการตรวจสอบว่ากราฟเชื่อมกันหรือไม่
                if (graphMatrix.isConnected()) {
                    // แสดงข้อความหรือดำเนินการต่อตามที่ต้องการ
                    System.out.println("Graph is connected.");
                } else {
                    // แสดงข้อความหรือดำเนินการต่อตามที่ต้องการ
                    System.out.println("Graph is not connected.");
                }
            }
        }); 
    }// Constructor
    
    private void checkSubmitButtonState() {
        // Enable submit button if inputWeightField is not empty, otherwise disable it
        submitButton.setEnabled(!inputWeightField.getText().isEmpty());
    }
    
    private void createVertexChoices() {
        System.out.println("set input vertex");
        String vertexCountText = inputVertexField.getText(); // 5
        int vertexCount = 0;
        
        try {
            vertexCount = Integer.parseInt(vertexCountText);
            if (vertexCount <= 1) {
            // Display a message dialog informing the user of the invalid input
            JOptionPane.showMessageDialog(null, "Vertex count must be an integer greater than 1", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        } catch (NumberFormatException e) {
            // Display a message dialog informing the user of the invalid input
            JOptionPane.showMessageDialog(null, "Invalid input for vertex count. Please enter an integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        inputVertexField.setEditable(false);

        String[] choices = new String[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
                choices[i] = String.valueOf(i + 1);
        }
            
        if (fromVertexChoice != null) {
            remove(fromVertexChoice);
            remove(toVertexChoice);
            remove(inputWeightField);
        }
        
        fromVertexChoice = new JComboBox<>(choices);
        fromVertexChoice.setBounds(350, 150, 70, 30);
        add(fromVertexChoice);

        toVertexChoice = new JComboBox<>(choices);
        toVertexChoice.setBounds(470, 150, 70, 30);
        add(toVertexChoice);
                
        
        add(scrollPane);
        add(edgeLabel);
        add(toLabel);
        add(weightLabel);
        add(inputWeightField);
        add(clearButton);

        
        revalidate();
        repaint();
    }

    private class submitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String from = (String) fromVertexChoice.getSelectedItem();
            String to = (String) toVertexChoice.getSelectedItem();
            int weight = Integer.parseInt(inputWeightField.getText());

            if (from.equals(to)) {
                JOptionPane.showMessageDialog(null, "From and to choices cannot be the same", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                System.out.println("From and to choices cannot be the same");
                return;
            }

            for (Edge edge : edgeList) {
                if (edge.getSrc() == Integer.parseInt(from) && edge.getDst() == Integer.parseInt(to)) {
                    JOptionPane.showMessageDialog(null, "Duplicate input: " + from + " to " + to, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Duplicate input: " + from + " to " + to);
                    return;
                }else if (edge.getSrc() == Integer.parseInt(to) && edge.getDst() == Integer.parseInt(from)){
                    JOptionPane.showMessageDialog(null, "Duplicate input: " + to + " to " + from, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Duplicate input: " + to + " to " + from);
                    return;
                }
            }

            String edgeName = "e" + edgeCount; // สร้างชื่ออ็อบเจ็กต์ตามจำนวนอ็อบเจ็กต์ที่สร้างแล้ว
            edgeList.add(new Edge(edgeName, Integer.parseInt(from), Integer.parseInt(to), weight));

            StringBuilder dataBuilder = new StringBuilder();
            for (Edge edge : edgeList) {
                dataBuilder.append(edge.getName()).append(": ").append(edge.getSrc()).append(" to ")
                        .append(edge.getDst()).append(" weight ").append(edge.getWeight()).append("\n");
            }

            // นำข้อความที่สร้างเข้าสู่ JLabel
            dataLabel.setText(dataBuilder.toString());

            System.out.println("Submitted: " + from + " to " + to + ", Weight: " + weight);

            edgeCount++; // เพิ่มจำนวนอ็อบเจ็กต์ที่สร้างขึ้น
            System.out.println(edgeList);
            if(isConnected()){
                finishButton.setEnabled(true);
            }
        }
    }

    private class MousePositionCheck extends MouseAdapter{
        @Override
        public void mouseMoved(MouseEvent e) {
        // Check mouse's position
        // System.out.println("mouseX:" + e.getX() + ", mouseY:" + e.getY());
        }
    }

    public class clearAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputVertexField.setText("");
            inputVertexField.setEditable(true);
            inputWeightField.setText("");
            edgeList.clear(); // ล้างรายการทั้งหมดใน edgeList
            dataLabel.setText(""); // ล้างข้อความใน JLabel
            edgeCount = 1; // รีเซ็ตจำนวนอ็อบเจ็กต์ที่สร้างขึ้น
            finishButton.setEnabled(false);       
            System.out.println("Clear Edges List"); 
        }
    }

    public JTextField getInputVertexField() {
        return inputVertexField;
    }

    public JTextField getInputWeightField() {
        return inputWeightField;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public boolean isConnected() {
        int oldVertexCount = Integer.parseInt(inputVertexField.getText()); // ค่าเดิม
        int newVertexCount = oldVertexCount; // ค่าใหม่เท่ากับค่าเดิม
        
        // Check if all vertices from 1 to newVertexCount exist in edgeList
        for (int i = 1; i <= newVertexCount; i++) {
            boolean found = false;
            for (Edge edge : edgeList) {
                if (edge.getSrc() == i || edge.getDst() == i) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Graph isn't connected");
                return false; // If any vertex is not connected, return false
            }
        }
        System.out.println("Graph is connected");
        return true; // If all vertices are connected, return true
    }
    
}
