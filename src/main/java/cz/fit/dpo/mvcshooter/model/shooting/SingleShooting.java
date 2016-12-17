package cz.fit.dpo.mvcshooter.model.shooting;

import java.util.ArrayList;
import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.modes.Factory;

/**
 * Implementace strileni jednou strelou.
 */
public class SingleShooting implements ShootingState {
    
    @Override
    public ArrayList<Missile> shoot(Cannon cannon, Factory factory) {
        ArrayList<Missile> missiles = new ArrayList<Missile>();
        // vytvoreni strely pomoci factory
        Missile missile = factory.createMissile(
            cannon.getX(),
            cannon.getY(),
            cannon.getAngle(),
            cannon.getForce()
        );
        missiles.add(missile);

        return missiles;
    }
}
