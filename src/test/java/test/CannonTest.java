package test;

import cz.fit.dpo.mvcshooter.Config;
import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Model;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Testuje cannon a jeho nektere metody.
 */
public class CannonTest {
    
    @Test
    public void changeAngleTest() {   
        Model model = new Model();
        Cannon cannon = model.getCannon();
        Config config = Config.getInstance();
        
        int angleDefault = config.getIntProperty("cannon.angle_default");
        int angleStep = config.getIntProperty("cannon.angle_step");
        assertEquals(angleDefault, model.getCannon().getAngle());
        model.angleUp();
        assertEquals(angleDefault + angleStep, model.getCannon().getAngle());
        model.angleDown();
        assertEquals(angleDefault, model.getCannon().getAngle());
    }
    
    @Test
    public void changeForceTest() {   
        Model model = new Model();
        Cannon cannon = model.getCannon();
        Config config = Config.getInstance();
        
        int forceDefault = config.getIntProperty("cannon.force_default");
        int forceStep = config.getIntProperty("cannon.force_step");
        assertEquals(forceDefault, model.getCannon().getForce());
        model.forceUp();
        assertEquals(forceDefault + forceStep, model.getCannon().getForce());
        model.forceDown();
        assertEquals(forceDefault, model.getCannon().getForce());
    }
    
    @Test
    public void movingTest() {   
        Model model = new Model();
        Cannon cannon = model.getCannon();
        Config config = Config.getInstance();
        
        int defaultY = config.getIntProperty("cannon.y_default");
        int step = config.getIntProperty("cannon.step");
        
        assertEquals(defaultY, model.getCannon().getY());
        model.moveCannonDown();
        assertEquals(defaultY + step, model.getCannon().getY());
        model.moveCannonUp();
        assertEquals(defaultY, model.getCannon().getY());
    }
}
