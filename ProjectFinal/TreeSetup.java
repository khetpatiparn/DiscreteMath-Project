import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class TreeSetup extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    JLabel Head;
    JLabel instructionLabel;
    JLabel edgeLabel;

    JTextField inputVertexField;
    JComboBox<String> fromChoice;
    JComboBox<String> toChoice;
    JTextField weightField;

    JButton submitButton;
    JButton clearButton;
    List<Edge> edgeList; // เก็บอ็อบเจ็กต์ Edge ที่สร้างขึ้น
    private int edgeCount = 1;
    private JTextPane dataLabel;

    public TreeSetup() {
        edgeList = new ArrayList<>(); // สร้างรายการเปล่า

        setPreferredSize(FRAME_SIZE);
        setLayout(null);
        setBackground(Color.WHITE);

        Head = new JLabel();
        Head.setText("Input");
        Head.setBounds(335, 20, 100, 60);
        Head.setOpaque(false);
        Head.setFont(new Font("Arial", Font.PLAIN, 40));
        Head.setForeground(Color.BLACK);
        add(Head);

        instructionLabel = new JLabel("Input number of vertex:");
        instructionLabel.setBounds(150, 100, 150, 30);
        instructionLabel.setOpaque(false);
        add(instructionLabel);

        edgeLabel = new JLabel("Input edge:");
        edgeLabel.setBounds(150, 150, 80, 30);
        edgeLabel.setOpaque(false);
        add(edgeLabel);

        inputVertexField = new JTextField();
        inputVertexField.setBounds(350, 100, 200, 30);
        inputVertexField.addActionListener(e -> createVertexChoices());
        add(inputVertexField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(420, 200, 100, 30);
        add(submitButton);
        submitAction subAction = new submitAction();
        submitButton.addActionListener(subAction);

        clearButton = new JButton("Clear");
        clearButton.setBounds(550, 200, 100, 30);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                edgeList.clear(); // ล้างรายการทั้งหมดใน edgeList
                dataLabel.setText(""); // ล้างข้อความใน JLabel
                edgeCount = 1; // รีเซ็ตจำนวนอ็อบเจ็กต์ที่สร้างขึ้น
            }
        });
        add(clearButton);

    }

    private void createVertexChoices() {
        String vertexCountText = inputVertexField.getText();
        int vertexCount = 0;

        dataLabel = new JTextPane();
        dataLabel.setOpaque(true);
        dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JScrollPane scrollPane = new JScrollPane(dataLabel);
        // dataLabel.setVerticalAlignment(JLabel.TOP);
        // dataLabel.setWrapStyleWord(true);
        scrollPane.setBounds(150, 250, 500, 200); // เปลี่ยนขนาดของ JLabel เพื่อรองรับข้อความทั้งหมด
        add(scrollPane);

        try {
            vertexCount = Integer.parseInt(vertexCountText);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input for vertex count");
            return;
        }

        String[] choices = new String[vertexCount];
        for (int i = 0; i < vertexCount; i++) {
            choices[i] = String.valueOf(i + 1);
        }

        if (fromChoice != null) {
            remove(fromChoice);
            remove(toChoice);
            remove(weightField);
        }
        fromChoice = new JComboBox<>(choices);
        fromChoice.setBounds(350, 150, 70, 30);
        add(fromChoice);

        JLabel toLabel = new JLabel("to");
        toLabel.setBounds(430, 150, 30, 30);
        add(toLabel);

        toChoice = new JComboBox<>(choices);
        toChoice.setBounds(470, 150, 70, 30);
        add(toChoice);

        weightField = new JTextField();
        weightField.setBounds(350, 200, 50, 30);
        add(weightField);

        JLabel weightInputLabel = new JLabel("Input weight:");
        weightInputLabel.setBounds(150, 200, 120, 30);
        add(weightInputLabel);

        revalidate();
        repaint();
    }

    private class submitAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String from = (String) fromChoice.getSelectedItem();
            String to = (String) toChoice.getSelectedItem();
            int weight = Integer.parseInt(weightField.getText());

            if (from.equals(to)) {
                System.out.println("From and to choices cannot be the same");
                return;
            }

            for (Edge edge : edgeList) {
                if (edge.getSrc() == Integer.parseInt(from) && edge.getDst() == Integer.parseInt(to)) {
                    System.out.println("Duplicate input: " + from + " to " + to);
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
            dataLabel.setText(dataBuilder.toString());

            // นำข้อความที่สร้างเข้าสู่ JLabel
            dataLabel.setText(dataBuilder.toString());

            System.out.println("Submitted: " + from + " to " + to + ", Weight: " + weight);

            edgeCount++; // เพิ่มจำนวนอ็อบเจ็กต์ที่สร้างขึ้น
        }
    }
}