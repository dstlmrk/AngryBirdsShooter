package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.interfaces.Observer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import cz.fit.dpo.mvcshooter.model.GameObject;
import cz.fit.dpo.mvcshooter.model.Model;

/**
 * Platno, ktere se stara o vykreslovani hernich objektu.
 * @author Ondrej Stuchlik a Marek Dostal
 */
public class Canvas extends JPanel implements Observer { 
    
    GraphicsDrawer drawer = new GraphicsDrawer();
    Model model;

    public Canvas(int x, int y, int width, int height, Model model) {
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setLocation(x, y);
        this.setPreferredSize(new Dimension(width, height));
        this.setVisible(true);
        this.model = model;
        // tento objekt bude observerem, sleduje model a jeho zmeny
        this.model.addObserver(this);
    }
    
    @Override
    public void update() {
        repaint();
        // nelaguje se obrazovka
        Toolkit.getDefaultToolkit().sync();
    }
    
    /** Kazdy herni objekt navstivi (je akceptovan) navstevnik. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawer.setGraphics(g); 
        for (GameObject gameObject : model.getGameObjects()) {
            gameObject.accept(drawer);
        }
    }

}
