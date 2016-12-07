package interfaces;

/*
 * Interface navrhoveho vzoru Observer pro objekty, ktere "tukaji" do observera.
 */
public interface Observable {
    public void addObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notifyObservers();
}
