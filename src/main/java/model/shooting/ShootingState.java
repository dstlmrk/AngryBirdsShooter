package model.shooting;

import java.util.ArrayList;
import model.Cannon;
import model.Missile;

/*
 * Interface navrhoveho vzoru State pro objekty...
 */
public interface ShootingState {
    
    public ArrayList<Missile> shoot(Cannon cannon); // ,IBasicFactory factory);
}
