/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import cz.fit.dpo.mvcshooter.Config;
import interfaces.MovementStrategy;
import interfaces.Visitor;
import java.util.ArrayList;
import model.modes.Factory;
import model.movement.*;
import model.shooting.*;

/**
 *
 * @author marek
 */
public class Cannon extends GameObject {

    private MovementStrategy movementStrategy;
    private ShootingState shootingState;
    private int angle, force;
    private int shootingMode;
    Config config;

    public Cannon(int x, int y) {
        super(x, y);
        config = Config.getInstance();
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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public void changeStrategy() {
        // prehodit strategii
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

}
