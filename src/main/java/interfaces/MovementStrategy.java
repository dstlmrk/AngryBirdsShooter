package interfaces;

import model.Coordinates;
import model.Missile;


/*
 * Interface navrhoveho vzoru Strategy pro volby pohybu strely.
 */
public interface MovementStrategy {
    public Coordinates move(int gravity, Missile missile);
}
