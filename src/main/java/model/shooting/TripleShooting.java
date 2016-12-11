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

/**
 * TODO: PREPSAT
 * Vzor State - implementace strely, pokud je atribut (vnitrni stav) entity Cannon nastaven na SingleShootingState.
 *
 */

public class TripleShooting implements ShootingState {

    private int span;
    
    public TripleShooting() {
        Config config = Config.getInstance();
        span = config.getIntProperty("cannon.range");      
    }
    
    @Override
    public ArrayList<Missile> shoot(Cannon cannon) {
        int angle = cannon.getAngle();
        int force = cannon.getForce();
        int x = cannon.getX();
        int y = cannon.getY();
        //  TODO: nachystat factory asi
        ArrayList<Missile> missiles = new ArrayList<Missile>();
        missiles.add(new Missile(x, y, angle - span, force));
        missiles.add(new Missile(x, y, angle, force));
        missiles.add(new Missile(x, y, angle + span, force));
        return missiles;
    }
}
