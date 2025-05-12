package objectAdventure.world.awolde1;

import objectAdventure.common.Observable;
import objectAdventure.common.Observer;
import objectAdventure.core.item.Item;

import java.util.ArrayList;
import java.util.List;

public class AncientKey implements Item, Observable {

    private final List<Observer> observers = new ArrayList<>();
    private boolean used = false;

    // Register an observer
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    // Remove an observer
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    // Notify all observers about the change
    @Override
    public void notifyObservers(Object arg) {
        for (Observer o : observers) {
            o.update(arg);
        }
    }

    // Mark the key as used and notify observers
    public void useKey() {
        this.used = true;
        notifyObservers(this);  
    }

    // Check if the key has been used
    public boolean isUsed() {
        return used;
    }

    // Implementing the Item interface methods
    @Override
    public String getItemFullDescription() {
        return "A mysterious ancient key that unlocks hidden chambers and secrets.";
    }

    @Override
    public String getItemDisplayName() {
        return "Ancient Key";
    }

    
    public String getItemDescription() {
        return "A key of unknown origin.";
    }

    
    public String getName() {
        return "Ancient Key";
    }
}
