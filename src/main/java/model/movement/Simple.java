package model.movement;

import interfaces.MovementStrategy;
import model.Coordinates;
import model.Missile;

/**
 * Implementace sikmeho vrhu.
 */
public class Simple implements MovementStrategy {
    
    public Simple() {
        System.out.println("STRELA: Simple");
    }

    @Override
    public Coordinates move(int gravity, Missile missile) {
        // x = x0 + (v0 * t * cos(alpha))     
	int x = (int)(missile.getInitX() + (missile.getForce() * missile.getTime() / 10 * Math.cos(Math.toRadians(missile.getAngle()))));
	// y = y0 + (v0 * t * sin(alpha)) - (1/2 * g * t^2)
	int y = (int)(missile.getInitY() - (missile.getForce() * missile.getTime() / 10 * Math.sin(Math.toRadians(missile.getAngle()))) + (0.5 * gravity * missile.getTime() / 10 * missile.getTime() / 10));
	return new Coordinates(x, y);
    }
    
}
