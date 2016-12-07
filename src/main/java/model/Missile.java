/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author marek
 */
public class Missile extends GameObject {
    
    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void move() {
        x += 5;
        y += 0;
    }
    
}
