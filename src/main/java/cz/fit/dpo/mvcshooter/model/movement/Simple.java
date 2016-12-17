package cz.fit.dpo.mvcshooter.model.movement;

import cz.fit.dpo.mvcshooter.model.interfaces.MovementStrategy;
import cz.fit.dpo.mvcshooter.model.Coordinates;
import cz.fit.dpo.mvcshooter.model.Missile;

/**
 * Implementace jednoduche strely.
 */
public class Simple implements MovementStrategy {
    
    /** Sikmy vrh s nulovou gravitaci. */
    @Override
    public Coordinates move(int gravity, Missile missile) {
        // teleso leti rovne, pokud je gravitace nulova
        gravity = 0;

        int force = missile.getForce();
        int angle = missile.getAngle();
        int time = missile.getTime();
        
        double alphaCos = Math.cos(Math.toRadians(angle));
        double alphaSin = Math.sin(Math.toRadians(angle));
        
        // x = x0 + (v0 * t * cos(alpha))     
	int x = (int) (missile.getInitX() + (force * time / 10 * alphaCos));
	// y = y0 + (v0 * t * sin(alpha)) - (1/2 * g * t^2)
	int y = (int) (
            missile.getInitY()
            - (force * time / 10 * alphaSin)
            + (0.5 * gravity * time / 10 * time / 10)
        );
	return new Coordinates(x, y);
    }
    
}
