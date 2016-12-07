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
public class Cannon extends GameObject {

    public Cannon() {
        super(50, 150);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
