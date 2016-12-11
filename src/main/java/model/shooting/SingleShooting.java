/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.shooting;

import java.util.ArrayList;
import model.Cannon;
import model.Missile;

/**
 *
 * @author marek
 */
public class SingleShooting implements ShootingState {
    
    @Override
    public ArrayList<Missile> shoot(Cannon cannon) {
        System.out.println("chystam se vystreli");
//        TODO: nachystat factory asi
        ArrayList<Missile> missiles = new ArrayList<Missile>();
        Missile missile = new Missile(cannon.getX(), cannon.getY(), cannon.getAngle(), cannon.getForce());
//        Missile missile = factory.createMissile(cannon.getX(), cannon.getY(), cannon.getAngle(), cannon.getForce());
        missiles.add(missile);
        return missiles;
    }
}
