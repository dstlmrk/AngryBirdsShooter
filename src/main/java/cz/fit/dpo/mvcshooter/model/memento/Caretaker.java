package cz.fit.dpo.mvcshooter.model.memento;

import cz.fit.dpo.mvcshooter.model.Model;

/**
 * Navrhovy vzor Memento - pozaduje po originatorovi (model)
 * externizovani sveho stavu.
 */
public class Caretaker {
    
    private Memento memento = null;

    public void saveState(Model model) {
        memento = model.saveStateToMemento();
    }

    public void restoreState(Model model) {
        if (memento != null) {
            model.restoreStateFromMemento(memento);
        } else {
            System.out.println("Game wasn't saved yet");
        }
    }

}
