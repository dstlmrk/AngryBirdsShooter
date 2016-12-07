package view;

import controller.Controller;
import model.Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Ondrej Stuchlik
 */
public class MainWindow extends JFrame {


    public MainWindow() {
        
        try {
            Model model = new Model();
            final Controller controller = new Controller(model);

            final Canvas view = new Canvas(0, 0, 500, 500, model);

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
              + "here goes some description...");
    }
}
