import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tree extends JPanel {
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    JLabel Head;
    JButton KButton, PButton, DButton, Exit;

    public Tree() {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);
        // JLabel
        Head = new JLabel();
        Head.setText("Select Algorithm");
        Head.setBounds(555, 50, 590, 60);
        Head.setOpaque(false);
        Head.setFont(new Font("Arial", Font.PLAIN, 30));
        Head.setForeground(Color.WHITE);
        add(Head);
        // สร้าง JButton
        KButton = new JButton("Kruskal's algorithm");
        PButton = new JButton("Prim's algorithm");
        DButton = new JButton("Dijkstra's algorithm");
        Exit = new JButton("exit");
        // กำหนดตำแหน่งและขนาดของปุ่ม
        int buttonWidth = 200;
        int buttonHeight = 50;
        int BXaxis = 565;
        int BYaxis = 150;

        KButton.setBounds(BXaxis, BYaxis, buttonWidth, buttonHeight);
        PButton.setBounds(BXaxis, BYaxis + 100, buttonWidth, buttonHeight);
        DButton.setBounds(BXaxis, BYaxis + 200, buttonWidth, buttonHeight);
        Exit.setBounds(BXaxis, BYaxis + 300, buttonWidth, buttonHeight);

        // เพิ่มปุ่มลงใน JPanel
        add(KButton);
        add(PButton);
        add(DButton);
        add(Exit);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // วาดสี่เหลี่ยมหลัก
        int mainRectWidth = 250; // กำหนดความกว้างของสี่เหลี่ยมหลัก
        int mainRectHeight = 600; // กำหนดความสูงของสี่เหลี่ยมหลัก
        int mainRectX = getWidth() - mainRectWidth; // คำนวณตำแหน่ง X ด้านขวาของ JPanel
        int mainRectY = (getHeight() - mainRectHeight) / 2; // คำนวณตำแหน่ง Y ให้สี่เหลี่ยมอยู่ตรงกลางของ JPanel

        g.setColor(Color.RED); // เปลี่ยนสีตามที่คุณต้องการ
        g.fillRect(mainRectX, mainRectY, mainRectWidth, mainRectHeight); // วาดสี่เหลี่ยมหลัก

    }
}
