/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.MovementStrategy;
import interfaces.Visitor;
import model.movement.Simple;

/**
 *
 * @author marek    
 */
public class Missile extends GameObject {
    
    private MovementStrategy movementStrategy;
    private int initX, initY, angle, force, time = 1;
    
    public Missile(int x, int y) {
        super(x, y);
        this.initX = x;
        this.initY = y;
        this.angle = 20; //angle;
	this.force = 50; //force;
        movementStrategy = new Simple();

    }
    
    public int getInitX() {
        return initX;
    }

    public int getInitY() {
        return initY;
    }

    public int getAngle() {
        return angle;
    }

    public int getForce() {
        return force;
    }
    
    public int getTime() {
        return time;
    }
    
    public void move() {
        time += 1;
        Coordinates coordinates = movementStrategy.move(5, this);
        x = coordinates.getX();
        y = coordinates.getY();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    private MovementStrategy Simple() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
