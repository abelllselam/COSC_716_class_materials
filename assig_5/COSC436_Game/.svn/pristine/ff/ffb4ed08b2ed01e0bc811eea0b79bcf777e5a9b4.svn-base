package objectAdventure.world.awolde1;

import objectAdventure.common.Observer;
import objectAdventure.core.room.Room;
import objectAdventure.core.item.Item;
import objectAdventure.world.awolde1.AncientKey;

public class MyRoom extends Room implements Observer {

    private String description = "A mysterious ancient room. A locked door stands silently.";

    public MyRoom() {
        super(1, "My Secret Chamber");
    }

    @Override
    public String getRoomDescription() {
        return description;
    }

    @Override
    public void update(Object observable) {
        if (observable instanceof AncientKey) {
            AncientKey key = (AncientKey) observable;
            if (key.isUsed()) {
                description = "The door is now open! You can feel the fresh air coming through.";
            }
        }
    }

    // room to observe the item:
    public void initWithItem(AncientKey key) {
        key.addObserver(this);  
    }

    public static MyRoom newInstance() {
        return new MyRoom();
    }
    
}
