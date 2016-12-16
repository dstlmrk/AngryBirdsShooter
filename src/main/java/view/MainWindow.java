package view;

import controller.Controller;
import cz.fit.dpo.mvcshooter.Config;
import model.Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ondrej Stuchlik a Marek Dostal
 */
public class MainWindow extends JFrame {
    
    Config config;

    public MainWindow() {
        
        config = Config.getInstance();
        int width = config.getIntProperty("canvas.width");
        int height = config.getIntProperty("canvas.height");
        
        try {
            Model model = new Model();
            final Controller controller = new Controller(model);
            controller.setView(this);
            
            final Canvas view = new Canvas(0, 0, width, height, model);

            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("MyShooter");
            this.setResizable(false);

            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            this.setLocation(
                  (int) (screen.getWidth() / 2 - 250),
                  (int) (screen.getHeight() / 2 - 250));

            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    controller.keyPressed(evt);
                    System.out.println("key pressed: " + evt.getKeyChar());
                }
            });

            this.add(view);
            this.pack();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    public void showHelp() { 
        JOptionPane.showMessageDialog(this, 
            "Controls: \n"
                + "SPACE - shooting\n"
                + "↑ and ↓ - cannon moving\n"
                + "← and → - missile's angle\n"
                + "- and + - missile's force\n"
                + "g and h - gravity\n"
                + "q - switch shooting cannon mode (simple, triple)\n"
                + "? - load \n"
        );
        
    }
}
