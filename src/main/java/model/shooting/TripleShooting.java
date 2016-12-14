package model.shooting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import model.Cannon;
import model.Missile;
import cz.fit.dpo.mvcshooter.Config;
import model.modes.Factory;

/**
 * TODO: PREPSAT
 * Vzor State - implementace strely, pokud je atribut (vnitrni stav) entity Cannon nastaven na SingleShootingState.
 *
 */

public class TripleShooting implements ShootingState {
    
    // span (range) between missiles
    private int span;
    
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
        missiles.add(factory.createMissile(x, y, angle - span, force));
        missiles.add(factory.createMissile(x, y, angle, force));
        missiles.add(factory.createMissile(x, y, angle + span, force));        
        return missiles;
    }
}
