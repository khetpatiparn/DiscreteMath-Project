import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Creditscreen extends JPanel{
    JLabel creditsTitle, name1, name2, name3;
    JLabel CbackBtn;
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 600;
    private final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    
    public Creditscreen(){
        setPreferredSize(FRAME_SIZE); // set Size of Title Game Screen
        setLayout(null); // use absolute layout
        setBackground(Color.WHITE);
        
        // creditsTitle
        creditsTitle = new JLabel();
        creditsTitle.setText("CREDITS");
        creditsTitle.setBounds(190, 60, 454, 104);
        add(creditsTitle);

        // name
        name1 = new JLabel();
        name1.setText("66050253  PATIPARN LAKORN");
        name1.setBounds(60, 200, 450, 80);
        add(name1);

        name2 = new JLabel();
        name2.setText("66050635  CHATCHAI PANYANAI");
        name2.setBounds(60, 300, 490, 80);
        name2.setForeground(Color.black);
        add(name2);

        name3 = new JLabel();
        name3.setText("66050752  TANAKORN KAMANA");
        name3.setBounds(60, 400, 490, 80);
        add(name3);
        
        // BackButton (But use Label)
        CbackBtn = new JLabel();
        CbackBtn.setText("BACK");
        CbackBtn.setBounds(213, 306, 150, 50);
        CbackBtn.setOpaque(true);
        CbackBtn.setBackground(new Color(166, 166, 166).darker().darker());
        CbackBtn.setHorizontalAlignment(SwingConstants.CENTER);
        CbackBtn.setVerticalAlignment(SwingConstants.CENTER);
        add(CbackBtn);
    }
}
