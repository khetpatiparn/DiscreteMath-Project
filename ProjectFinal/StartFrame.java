import javax.swing.JFrame;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartFrame extends JFrame{
    public StartFrame() {
        Container cp;
        Creditscreen creditscreen;
        Title title;
        Tree tree;
        FSA fsa;
        TreeSetup ts;
        Matrix matrix;
        cp = getContentPane();
        // title = new Title();
        // creditscreen = new Creditscreen();
         ts = new TreeSetup();
        // matrix = new Matrix(); 
        // tree = new Tree();
        
        //fsa = new FSA(6);
        
        // cp.add(title);
        // cp.add(creditscreen);
         cp.add(ts);
        // cp.add(matrix);
        // cp.add(tree);
         //cp.add(fsa);

        // Event-Handler Control 
        //title.addMouseListener(new NavigationTitleScreen()); // Event TitleScreen
        //creditscreen.addMouseListener(new NavBackCredits()); // Event Credit

        //setup frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setTitle("Project-Discretemath");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
     
    // Inner Class Event 
    private class NavigationTitleScreen extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
           

            }
        }
    private class NavBackCredits extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
               
    
            }
        }
    }



