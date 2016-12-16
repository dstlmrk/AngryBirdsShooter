package model.shooting;

import java.util.ArrayList;
import model.Cannon;
import model.Missile;
import model.modes.Factory;

/**
 *
 * @author marek
 */
public class SingleShooting implements ShootingState {
    
    @Override
    public ArrayList<Missile> shoot(Cannon cannon, Factory factory) {
        ArrayList<Missile> missiles = new ArrayList<Missile>();
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
