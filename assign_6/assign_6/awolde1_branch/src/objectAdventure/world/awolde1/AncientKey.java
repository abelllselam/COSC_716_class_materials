package objectAdventure.world.awolde1;

import objectAdventure.common.Observable;
import objectAdventure.common.Observer;
import objectAdventure.core.item.Item;

import java.util.ArrayList;
import java.util.List;

public class AncientKey implements Item, Observable<AncientKey> {

    private final List<Observer<AncientKey>> observers = new ArrayList<>();
    private boolean used = false;

    @Override
    public void addObserver(Observer<AncientKey> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<AncientKey> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(AncientKey arg) {
        for (Observer<AncientKey> o : observers) {
            o.update(arg);
        }
    }

    public void useKey() {
        this.used = true;
        notifyObservers(this);
    }

    public boolean isUsed() {
        return used;
    }

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
