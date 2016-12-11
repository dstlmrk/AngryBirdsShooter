/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import interfaces.Visitor;

/**
 *
 * @author marek
 */
public class Enemy extends GameObject {
    
    private int type;
    
    public Enemy(int x, int y) {
        super(x, y);
        // random mezi 0 a 1        
        this.type = (int)(Math.random()*2);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public int getType() {
        return type;
    }
    
}
