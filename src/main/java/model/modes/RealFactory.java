/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.modes;

import model.Enemy;
import model.Missile;

/**
 *
 * @author marek
 */
public class RealFactory implements Factory {

    @Override
    public Enemy createEnemy(int x, int y) {
        return null;
    }

    @Override
    public Missile createMissile(int initX, int initY, int angle, int force) {
        return null;
    }
    
}

/**
 * Vzor AbstractFactory - implementace zakladni factory, ktera vytvari real objekty:
 * - pohybujici se nepratele
 * - strely s rovnym smerem
 *
 * @author Lubos Palisek
 */

//@Override
//public RealEnemy createEnemy(int x, int y) {
//    return new RealEnemy(x, y);
//}
//
//@Override
//public Missile createMissile(int firstX, int firstY, int angle, int force) {
//    Missile missile = new Missile(firstX, firstY, angle, force);
//    // Navrhovy vzor Strategy - klient si zvoli, jaka strategie se pouzije
//    missile.setIMovementStrategy(new RealisticMovementStrategy());
//    return missile;
//}
