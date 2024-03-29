import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Container;

public class StartFrame extends JFrame{
    // Screen
    TitleScreen titleScreen;
    Creditscreen creditscreen;
    Tree tree;
    FSA fsa;
    GraphSetupScreen graphSetupScreen;
    Matrix matrix;
    
    Container cp;
    
    public StartFrame() {
        cp = getContentPane();
        // Add the Screen
        titleScreen = new TitleScreen();
        creditscreen = new Creditscreen();
        graphSetupScreen = new GraphSetupScreen();
        matrix = new Matrix();
        tree = new Tree();
        
        fsa = new FSA(6);
        
        cp.add(titleScreen);
        // cp.add(creditscreen);
        // cp.add(graphSetupScreen);
        // cp.add(matrix);
        // cp.add(tree);
         //cp.add(fsa);

        // Event-Handler Control 
        titleScreen.addMouseListener(new NavigationTitleScreen()); // Event TitleScreen
        creditscreen.CbackBtn.addActionListener(new NavBackCredits()); // Event Credit
        // Event Graph Set up 
        graphSetupScreen.backButton.addActionListener(new NavBackGraphSetupScreen()); 
        graphSetupScreen.finishButton.addActionListener(new NavFinishSetup());

        tree.backButton.addActionListener(new NavBackTreeScreen()); // Event Tree Screen

        fsa.exitButton.addActionListener(new NavBackFSA()); // Event FSA SCreen

        //setup frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setTitle("Project-Discretemath");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // Inner Class Event for Title Screen 
    private class NavigationTitleScreen extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (titleScreen.lbTree.getBounds().contains(e.getPoint())){
                System.out.println("==> Tree Button's clicked");
                cp.remove(titleScreen);
                cp.add(graphSetupScreen);
                cp.revalidate();
                cp.repaint();
                System.out.println("-----------------------------");
                System.out.println("Graph Set up Screen's showing");
            }else if (titleScreen.lbFinite.getBounds().contains(e.getPoint())){
                System.out.println("==> Finite Button's clicked");
                cp.remove(titleScreen);
                cp.add(fsa);
                cp.revalidate();
                cp.repaint();
                System.out.println("-----------------------------");
                System.out.println("Finite State Automata Screen's showing");
            }else if (titleScreen.lbCredit.getBounds().contains(e.getPoint())){
                System.out.println("==> Credit Button's clicked");
                cp.remove(titleScreen);
                cp.add(creditscreen);
                cp.revalidate();
                cp.repaint();
                System.out.println("-----------------------------");
                System.out.println("Credit Screen's showing");
            }else if (titleScreen.lbExit.getBounds().contains(e.getPoint())){
                System.out.println("==> Exit Button's clicked");
                System.exit(0);
            }
        }
    }
    // Inner Class Event for Credit Screen 
    private class NavBackCredits implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back button's clicked");
            cp.remove(creditscreen);
            cp.add(titleScreen);
            cp.revalidate();
            cp.repaint();
            System.out.println("-----------------------------");
            System.out.println("Title Screen's showing");
        }
    }
    // Inner Class Event for Graph Setup Screen
    private class NavBackGraphSetupScreen implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back button's clicked");
            graphSetupScreen.clearButton.addActionListener(new GraphSetupScreen().new clearAction());
            System.out.println(graphSetupScreen.edgeList);
            cp.remove(graphSetupScreen);
            cp.add(titleScreen);
            cp.revalidate();
            cp.repaint();
            System.out.println("-----------------------------");
            System.out.println("Title Screen's showing");
        }
    }
    // Inner Class Event for Graph Setup Screen
    private class NavFinishSetup implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Finish button's clicked");
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                System.out.println("You chose Yes");
                cp.remove(graphSetupScreen);
                cp.add(tree);
                cp.revalidate();
                cp.repaint();
                System.out.println("-----------------------------");
                System.out.println("Tree Screen's showing");
            // Code to execute if Yes is selected
            } else {  // (choice == JOptionPane.NO_OPTION)
                System.out.println("You chose No");
            // Code to execute if No is selected
            }
        }
    }

    // Inner Class Event for Tree Screen
    private class NavBackTreeScreen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back Button's clicked");
            cp.remove(tree);
            cp.add(graphSetupScreen);
            cp.revalidate();
            cp.repaint();
            System.out.println("-----------------------------");
            System.out.println("Graph Set up Screen's showing");
        }
    }

    // Inner Class Event for Finite Screen
    private class NavBackFSA implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Back Button's clicked");
            cp.remove(fsa);
            cp.add(titleScreen);
            cp.revalidate();
            cp.repaint();
            System.out.println("-----------------------------");
            System.out.println("Title Screen's showing");
        }
    }
}



