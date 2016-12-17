package cz.fit.dpo.mvcshooter.model.interfaces;

import cz.fit.dpo.mvcshooter.model.Coordinates;
import cz.fit.dpo.mvcshooter.model.Missile;

/**
 * Interface navrhoveho vzoru Strategy pro volby pohybu strely.
 */
public interface MovementStrategy {
    public Coordinates move(int gravity, Missile missile);
}
