package interfaces;

import model.*;

/*
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
