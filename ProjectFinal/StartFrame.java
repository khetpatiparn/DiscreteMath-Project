import javax.swing.JFrame;
import java.awt.Container;

public class StartFrame extends JFrame{
    // Screen
    TitleScreen titleScreen;
    // Creditscreen creditscreen;
    Tree tree;
    FSA fsa;
    GraphSetupScreen graphSetupScreen;
    Matrix matrix;
    
    Container cp;
    
    public StartFrame() {
        cp = getContentPane();
        // Add the Screen
        titleScreen = new TitleScreen();
        // creditscreen = new Creditscreen();
        graphSetupScreen = new GraphSetupScreen();
        // matrix = new Matrix(); 
        // tree = new Tree();
        
        // fsa = new FSA(6);
        
        // cp.add(titleScreen);
        // cp.add(creditscreen);
        cp.add(graphSetupScreen);
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
}



