package cz.fit.dpo.mvcshooter.model.interfaces;

/**
 * Interface navrhoveho vzoru Visitor pro objekty, ktere prijimaji visitora.
 * Slouzi pro vetsinu hernich objektu (Missile, Cannon, ..).
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
