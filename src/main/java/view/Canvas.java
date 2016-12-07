package view;

import interfaces.Observer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import model.Missile;
import model.Model;

/**
 *
 * @author Ondrej Stuchlik
 */
public class Canvas extends JPanel implements Observer { 
    
    GraphicsDrawer drawer = new GraphicsDrawer();
    Model model;

    public Canvas(int x, int y, int width, int height, Model model) {
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setLocation(x, y);
        this.setPreferredSize(new Dimension(width,height));
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawer.setGraphics(g);
        drawer.visit(model.getCannon());
        for (Missile missile: model.getMissiles()) {
            drawer.visit(missile);
        }
        
        //    Jak by to mozna mohlo vypadat?
        //    for (GameObject go : model.getAllGameObjects()) {
        //        go.accept(drawer);
        //    }
        
//        drawer.drawCannon(g, new Cannon());
//        drawer.drawMissile(g, new Missile());
    }

}
