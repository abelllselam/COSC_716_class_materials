package objectAdventure.world.awolde1;

import objectAdventure.common.Observer;
import objectAdventure.core.room.Room;

public class MyRoom extends Room implements Observer<AncientKey> {

    private String description = "A mysterious ancient room. A locked door stands silently.";

    public MyRoom() {
        super(1, "My Secret Chamber");
        addItem(new Lamp());
    }

    @Override
    public String getRoomDescription() {
        return description;
    }

    @Override
    public void update(AncientKey key) {
        if (key.isUsed()) {
            description = "The door is now open! You can feel the fresh air coming through.";
        }
    }

    public void initWithItem(AncientKey key) {
        key.addObserver(this);
    }

    public static MyRoom newInstance() {
        return new MyRoom();
    }
}
