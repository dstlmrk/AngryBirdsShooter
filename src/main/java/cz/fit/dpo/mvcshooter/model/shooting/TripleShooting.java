package cz.fit.dpo.mvcshooter.model.shooting;

import java.util.ArrayList;
import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.Config;
import cz.fit.dpo.mvcshooter.model.modes.Factory;

/**
 * Implementace strileni tremi strelami.
 */
public class TripleShooting implements ShootingState {
    
    // span (range) between missiles
    private final int span;
    
    public TripleShooting() {
        Config config = Config.getInstance();
        span = config.getIntProperty("cannon.range");      
    }
    
    @Override
    public ArrayList<Missile> shoot(Cannon cannon, Factory factory) {
        int angle = cannon.getAngle();
        int force = cannon.getForce();
        int x = cannon.getX();
        int y = cannon.getY();
        
        ArrayList<Missile> missiles = new ArrayList<Missile>();
        // vytvoreni strel pomoci factory
        missiles.add(factory.createMissile(x, y, angle - span, force));
        missiles.add(factory.createMissile(x, y, angle, force));
        missiles.add(factory.createMissile(x, y, angle + span, force));        
        return missiles;
    }
}
