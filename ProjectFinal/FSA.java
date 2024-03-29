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
    JButton submitButton, clearButton, exitButton; // Added exitButton
    JComboBox<Integer> comboBox;
    JTextField textField;
    JPanel outputPanel; // Added JPanel for output
    JLabel outputLabel; // Added JLabel for displaying output
    private BufferedImage image;

    public FSA(int input) {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);

        // Label above JComboBox
        selectStartStateLabel = new JLabel("Select Start State");
        selectStartStateLabel.setBounds(565, 180, 200, 30);
        selectStartStateLabel.setForeground(Color.WHITE);
        selectStartStateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        add(selectStartStateLabel);

        // JComboBox
        Integer[] items = new Integer[input];
        for (int i = 0; i < input; i++) {
            items[i] = i;
        }
        comboBox = new JComboBox<>(items);
        comboBox.setBounds(565, 230, 200, 30);
        add(comboBox);

        // Separator Label
        separatorLabel = new JLabel("Input : 0,1");
        separatorLabel.setBounds(565, 290, 200, 30);
        separatorLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        separatorLabel.setForeground(Color.WHITE);
        add(separatorLabel);

        // JTextField
        textField = new JTextField();
        textField.setBounds(565, 320, 200, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(c == '0' || c == '1')) {
                    e.consume(); //filter input 
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        add(textField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(565, 360, 95, 30);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = textField.getText();
                System.out.println(inputText);
                // Process the input text here
                outputLabel.setText("Output: " + inputText);
            }
        });
        add(submitButton);

        // Clear Button
        clearButton = new JButton("Clear");
        clearButton.setBounds(670, 360, 95, 30);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });
        add(clearButton);

        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(565, 500, 200, 30); // Adjusted position
        add(exitButton);

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