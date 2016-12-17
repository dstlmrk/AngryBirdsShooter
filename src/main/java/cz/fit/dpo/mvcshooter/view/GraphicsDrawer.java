package cz.fit.dpo.mvcshooter.view;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Collision;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.interfaces.Visitor;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import cz.fit.dpo.mvcshooter.model.GameStats;

/**
 *
 * @author Ondrej Stuchlik a Marek Dostal
 */
public class GraphicsDrawer implements Visitor {
    
    private BufferedImage cannonImage;
    private BufferedImage enemyImage1;
    private BufferedImage enemyImage2;
    private BufferedImage missileImage;
    private BufferedImage collisionImage;
    
    private Graphics graphics;

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }

    public GraphicsDrawer() {
        try {
            cannonImage = ImageIO.read(getClass().getResourceAsStream(
                    "/images/cannon.png"
            ));
            enemyImage1 = ImageIO.read(getClass().getResourceAsStream(
                    "/images/enemy1.png"
            ));
            enemyImage2 = ImageIO.read(getClass().getResourceAsStream(
                    "/images/enemy2.png"
            ));
            missileImage = ImageIO.read(getClass().getResourceAsStream(
                    "/images/missile.png"
            ));
            collisionImage = ImageIO.read(getClass().getResourceAsStream(
                    "/images/collision.png"
            ));
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public void visit(Cannon cannon) {
        graphics.drawImage(
            cannonImage, 
            cannon.getX() - cannonImage.getWidth()/2, 
            cannon.getY() - cannonImage.getHeight()/2,
            null
        );
    }

    @Override
    public void visit(Missile missile) {
        graphics.drawImage(
            missileImage, 
            missile.getX() - missileImage.getWidth()/2, 
            missile.getY() - missileImage.getHeight()/2,
            null
        );
    }
    
    @Override
    public void visit(Enemy enemy) {
        int type = enemy.getType();
        BufferedImage enemyImage;
        if (type == 0) {
            enemyImage = enemyImage1;
        } else {
            enemyImage = enemyImage2;
        }
        graphics.drawImage(enemyImage, enemy.getX(), enemy.getY(), null);
    }

    @Override
    public void visit(Collision collision) {
        graphics.drawImage(
            collisionImage,
            collision.getX(),
            collision.getY(),
            null
        );
    }

    @Override
    public void visit(GameStats gameStats) {
        graphics.drawString(
            gameStats.toString(),
            gameStats.getX(),
            gameStats.getY()
        );
    }

}
