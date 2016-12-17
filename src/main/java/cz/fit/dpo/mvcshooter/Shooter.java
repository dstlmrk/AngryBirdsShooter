package cz.fit.dpo.mvcshooter;

import cz.fit.dpo.mvcshooter.view.MainWindow;
import javax.swing.SwingUtilities;

/**
 * Shooter app.
 * @author Marek Dostal
 */
public class Shooter {
    
    public static void main(String[] args) {        
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
               new MainWindow().setVisible(true);
            }
        });
    }
}
