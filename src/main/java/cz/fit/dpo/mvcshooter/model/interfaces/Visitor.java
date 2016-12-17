package cz.fit.dpo.mvcshooter.model.interfaces;

import cz.fit.dpo.mvcshooter.model.Cannon;
import cz.fit.dpo.mvcshooter.model.Collision;
import cz.fit.dpo.mvcshooter.model.Enemy;
import cz.fit.dpo.mvcshooter.model.GameStats;
import cz.fit.dpo.mvcshooter.model.Missile;

/**
 * Interface navrhoveho vzoru Visitor pro objekty, ktere jsou navstevniky.
 * Visitorem bude v tomto pripade GraphicDrawer, ktery kazde vykreslovani
 * muze implementovat jinak.
 */
public interface Visitor {
    void visit(Cannon cannon);
    void visit(Missile missile);
    void visit(Enemy enemy);
    void visit(Collision collision);
    void visit(GameStats gameStats);
}
