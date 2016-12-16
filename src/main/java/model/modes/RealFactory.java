package model.modes;

import model.Enemy;
import model.Missile;
import model.movement.Realistic;

/**
 * Vzor Abstraktní továrna - implementace, která vytváří pohyblivé
 * nepřátele a střely s balistickou křivkou.
 */
public class RealFactory implements Factory {

    @Override
    public Enemy createEnemy(int x, int y) {
        return new RealEnemy(x, y);
    }

    @Override
    public Missile createMissile(int initX, int initY, int angle, int force) {
        Missile missile = new Missile(initX, initY, angle, force);
        missile.setMovementStrategy(new Realistic());
        return missile;
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
