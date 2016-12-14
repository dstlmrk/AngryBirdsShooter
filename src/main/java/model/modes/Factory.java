package model.modes;

import model.Enemy;
import model.Missile;

/**
 * Vzor Abstraktní továrna - základní třídá, která definuje operace
 * jednotlivých továren.
 */
public interface Factory {
    
    public Enemy createEnemy(int x, int y);
    public Missile createMissile(int initX, int initY, int angle, int force);
}
