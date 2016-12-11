/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.MovementStrategy;
import interfaces.Visitor;
import java.io.IOException;
import java.util.ArrayList;
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

    public Cannon() {
        super(50, 150);
        this.angle = 20; //angle;
	this.force = 50; //force;
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
    
    public ArrayList<Missile> shoot() {
        System.out.println("chystam se vystrelit");
        return shootingState.shoot(this);
    }

    public void changeShootingMode() {
        if (shootingMode == 0) {
            System.out.println("TripleShooting..");
            shootingState = new TripleShooting();
        } else {
            System.out.println("SingleShooting");
            shootingState = new SingleShooting();
        }
        shootingMode = (shootingMode + 1) % 2;
    }
    
    
    
}
