package cz.fit.dpo.mvcshooter.model.shooting;

import java.util.ArrayList;
import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Missile;
import cz.fit.dpo.mvcshooter.model.modes.Factory;

/**
 * Interface navrhoveho vzoru State.
 */
public interface ShootingState {
    
    public ArrayList<Missile> shoot(Cannon cannon, Factory factory);
}
