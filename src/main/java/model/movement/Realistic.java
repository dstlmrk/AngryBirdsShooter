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
        int force = (missile.getForce() / 4) == 0 ? 1 : (missile.getForce() / 4);
        int x = (int) missile.getInitX() + (int) (force * missile.getTime());
	int y = (int) missile.getInitY() - (int) (force * missile.getTime() * Math.toRadians(missile.getAngle()));
        return new Coordinates(x, y);	
    }
}
