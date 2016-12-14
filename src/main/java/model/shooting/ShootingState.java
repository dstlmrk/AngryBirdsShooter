package model.shooting;

import java.util.ArrayList;
import model.Cannon;
import model.Missile;
import model.modes.Factory;

/*
 * Interface navrhoveho vzoru State pro objekty...
 */
public interface ShootingState {
    
    public ArrayList<Missile> shoot(Cannon cannon, Factory factory);
}
