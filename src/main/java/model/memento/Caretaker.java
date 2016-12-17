package model.memento;

import model.Model;

/**
 * Navrhovy vzor Memento - pozaduje po originatorovi (model)
 * externizovani sveho stavu.
 */
public class Caretaker {
    
    private Memento memento = null;

    public void save(Model model) {
        memento = model.saveStateToMemento();
    }

    public void restore(Model model) {
        if (memento != null) {
            model.restoreStateFromMemento(memento);
        } else {
            System.out.println("Game wasn't saved yet");
        }
    }

}
