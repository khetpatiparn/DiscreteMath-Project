import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Creditscreen extends JPanel {
    JLabel creditsTitle, name1, name2, name3;
    JButton CbackBtn;
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

    public Creditscreen() {
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);

        // creditsTitle
        creditsTitle = new JLabel();
        creditsTitle.setText("CREDITS");
        creditsTitle.setBounds(313, 20, 454, 104);
        creditsTitle.setFont(new Font("Arial", Font.PLAIN, 40));
        add(creditsTitle);

        // name
        name1 = new JLabel();
        name1.setText("66050176  THANAPHON CHAWDONG");
        name1.setBounds(223, 150, 500, 80);
        name1.setForeground(Color.black);
        name1.setFont(new Font("Arial", Font.PLAIN, 20));
        add(name1);

        name2 = new JLabel();
        name2.setText("66050253  PATIPARN LAKORN");
        name2.setBounds(223, 200, 490, 80);
        name2.setForeground(Color.black);
        name2.setFont(new Font("Arial", Font.PLAIN, 20));
        add(name2);

        name3 = new JLabel();
        name3.setText("66050301  Pichsinee Jirawattanakasem");
        name3.setBounds(223, 250, 490, 80);
        name3.setForeground(Color.black);
        name3.setFont(new Font("Arial", Font.PLAIN, 20));
        add(name3);

        // BackButton ใช้ JButton แทน JLabel
        CbackBtn = new JButton(); // สร้าง JButton
        CbackBtn.setText("BACK");
        CbackBtn.setBounds(320, 370, 150, 50);
        CbackBtn.setOpaque(true);
        CbackBtn.setBackground(new Color(166, 166, 166));
        add(CbackBtn);
    }
}
