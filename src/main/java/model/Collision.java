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
public class Collision extends GameObject {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
}
