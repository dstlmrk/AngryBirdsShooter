package cz.fit.dpo.mvcshooter.model;

import cz.fit.dpo.mvcshooter.model.interfaces.MovementStrategy;
import cz.fit.dpo.mvcshooter.model.interfaces.Visitor;
import java.util.ArrayList;
import cz.fit.dpo.mvcshooter.model.modes.Factory;
import cz.fit.dpo.mvcshooter.model.movement.Simple;
import cz.fit.dpo.mvcshooter.model.shooting.ShootingState;
import cz.fit.dpo.mvcshooter.model.shooting.SingleShooting;
import cz.fit.dpo.mvcshooter.model.shooting.TripleShooting;

/**
 * Cannon.
 */
public class Cannon extends GameObject {

    private MovementStrategy movementStrategy;
    private ShootingState shootingState;
    private int angle, force;
    // pomocna promenna pro zjistovani stavu
    private int shootingMode;

    public Cannon(int x, int y) {
        super(x, y);
        angle = config.getIntProperty("cannon.angle_default");
	force = config.getIntProperty("cannon.force_default");
        shootingMode = 0;
        movementStrategy = new Simple();
        shootingState = new SingleShooting();
    }
    
    public int getAngle() {
        return angle;
    }

    public int getForce() {
        return force;
    }
    
    /** Vraci mod cannonu: single/triple. */
    public String getCannonShootingMode() {
        if (shootingMode == 0) {
            return "single";
        } else {
            return "triple";
        }
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public ArrayList<Missile> shoot(Factory factory) {
        return shootingState.shoot(this, factory);
    }

    public void changeShootingMode() {
        if (shootingMode == 0) {
            shootingState = new TripleShooting();
        } else {
            shootingState = new SingleShooting();
        }
        shootingMode = (shootingMode + 1) % 2;
    }

    void moveUp() {
        y -= config.getIntProperty("cannon.step");
    }

    void moveDown() {
        y += config.getIntProperty("cannon.step");
    }
        
    void angleUp() {
        int angleStep = config.getIntProperty("cannon.angle_step");
        if (angle + angleStep <= config.getIntProperty("cannon.angle_max")) {
            angle += angleStep;
	}
    }

    void angleDown() {
        int angleStep = config.getIntProperty("cannon.angle_step");
        if (angle - angleStep >= config.getIntProperty("cannon.angle_min")) {
            angle -= angleStep;
	}
    }
    
    public void forceUp() {
        int forceStep = config.getIntProperty("cannon.force_step");
        if (force + forceStep <= config.getIntProperty("cannon.force_max")) {
            force += forceStep;
        }
    }

    public void forceDown() {
        int forceStep = config.getIntProperty("cannon.force_step");
        if (force - forceStep >= config.getIntProperty("cannon.force_min")) {
            force -= forceStep;
        }
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void setShootingState(ShootingState shootingState) {
        this.shootingState = shootingState;
    }

    public void setShootingMode(int shootingMode) {
        this.shootingMode = shootingMode;
    }
    
    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setForce(int force) {
        this.force = force;
    }

    
    /** Kopirovani objektu pro navrhovy vzor Memento. */
    public Cannon copy() {
        Cannon cannon = new Cannon(x, y);
        cannon.setAngle(angle);
        cannon.setForce(force);
        cannon.setMovementStrategy(movementStrategy);
        cannon.setShootingMode(shootingMode);
        cannon.setShootingState(shootingState);
        return cannon;
    }
}
