package model.movement;

import interfaces.MovementStrategy;
import model.Coordinates;
import model.Missile;

/**
 *
 * @author marek
 */
public class Realistic implements MovementStrategy {
    
    public Realistic() {
        System.out.println("STRELA: Realistic");
    }
    
    @Override
    public Coordinates move(int gravity, Missile missile) {
        // TODO: tady bych mel upravit vypocet krivky        
//        int force = (missile.getForce() / 4) == 0 ? 1 : (missile.getForce() / 4);
//        int x = (int) missile.getInitX() + (int) (force * missile.getTime());
//	int y = (int) missile.getInitY() - (int) (force * missile.getTime() * Math.toRadians(missile.getAngle()));
//        return new Coordinates(x, y);
        
        // x = x0 + (v0 * t * cos(alpha))     
	int x = (int)(missile.getInitX() + (missile.getForce() * missile.getTime() / 10 * Math.cos(Math.toRadians(missile.getAngle()))));
	// y = y0 + (v0 * t * sin(alpha)) - (1/2 * g * t^2)
	int y = (int)(missile.getInitY() - (missile.getForce() * missile.getTime() / 10 * Math.sin(Math.toRadians(missile.getAngle()))) + (0.5 * gravity * missile.getTime() / 10 * missile.getTime() / 10));
	return new Coordinates(x, y);
    }
}
