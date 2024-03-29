import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FSA extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    JLabel Head;
    JLabel selectStartStateLabel;
    JLabel separatorLabel;
    JLabel outputLabel; // Added outputLabel
    JButton submitButton, clearButton, exitButton; // Added exitButton
    JComboBox<Integer> comboBox;
    JTextField textField;
    JPanel outputPanel; // Added JPanel for output
    private BufferedImage image;

    public FSA() {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);
        int labelXaxis = 565;
        int labelYaxis = 120;

        // Label above JComboBox
        selectStartStateLabel = new JLabel("Select Start State");
        selectStartStateLabel.setBounds(labelXaxis, labelYaxis, 200, 30);
        selectStartStateLabel.setForeground(Color.WHITE);
        selectStartStateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        add(selectStartStateLabel);

        // JComboBox
        Integer[] items = new Integer[3];
        for (int i = 0; i < 3; i++) {
            items[i] = i;
        }
        comboBox = new JComboBox<>(items);
        comboBox.setBounds(labelXaxis, labelYaxis + 50, 200, 30);
        add(comboBox);

        // Separator Label
        separatorLabel = new JLabel("Input : 0,1");
        separatorLabel.setBounds(labelXaxis, labelYaxis + 100, 200, 30);
        separatorLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        separatorLabel.setForeground(Color.WHITE);
        add(separatorLabel);

        // JTextField
        textField = new JTextField();
        textField.setBounds(labelXaxis, labelYaxis + 150, 200, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(c == '0' || c == '1')) {
                    e.consume(); // filter input
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        add(textField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(labelXaxis, labelYaxis + 190, 95, 30);
        submitButton.addActionListener(new ActionListener() {
            //algo
            @Override
            public void actionPerformed(ActionEvent e) {
                //Input to check
                String inputText = textField.getText();
                System.out.println(inputText);

                // Get the selected item from the JComboBox
                Integer startState = (int) comboBox.getSelectedItem();
                //System.out.println("Selected Start State: " + startState);

                // Process the input text here
                new FiniteMain(inputText, startState);
                if (FiniteMain.isAccepted == true) {
                    outputLabel.setText("Output: Accept");
                } else {
                    outputLabel.setText("Output: Reject");
                }
            }
        });
        add(submitButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(labelXaxis + 105, labelYaxis + 190, 95, 30);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                outputLabel.setText("Output: ");
            }
        });
        add(clearButton);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(labelXaxis, 500, 200, 30); // Adjusted position
        add(exitButton);

        outputLabel = new JLabel("Output: ");
        outputLabel.setBounds(labelXaxis, labelYaxis + 250, 200, 30);
        outputLabel.setForeground(Color.WHITE);
        outputLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        add(outputLabel);

        try {
            image = ImageIO.read(new java.io.File("FiniteGraph.jpg"));
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Drawing code...
        int mainRectWidth = 250; // กำหนดความกว้างของสี่เหลี่ยมหลัก
        int mainRectHeight = 600; // กำหนดความสูงของสี่เหลี่ยมหลัก
        int mainRectX = getWidth() - mainRectWidth; // คำนวณตำแหน่ง X ด้านขวาของ JPanel
        int mainRectY = (getHeight() - mainRectHeight) / 2; // คำนวณตำแหน่ง Y ให้สี่เหลี่ยมอยู่ตรงกลางของ JPanel
        g.setColor(Color.RED); // เปลี่ยนสีตามที่คุณต้องการ
        g.fillRect(mainRectX, mainRectY, mainRectWidth, mainRectHeight); // วาดสี่เหลี่ยมหลัก

        // Draw image (adjust position and size as needed)
        if (image != null) {
            ((Graphics2D) g).drawImage(image, 60, 190, 400, 150, null); // Adjust X, Y, width, and height
        }
    }
}