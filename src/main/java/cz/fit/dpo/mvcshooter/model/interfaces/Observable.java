package cz.fit.dpo.mvcshooter.model.interfaces;

/**
 * Interface navrhoveho vzoru Observer pro objekty, ktere pri zmenach
 * "tukaji" do observera (observer chce znat jejich zmeny).
 */
public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
