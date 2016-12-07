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

    public Enemy(int x, int y) {
        super(x, y);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
