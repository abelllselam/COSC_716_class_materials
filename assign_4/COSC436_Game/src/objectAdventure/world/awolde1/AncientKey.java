package objectAdventure.world.awolde1;

import objectAdventure.core.item.Item; 

public class AncientKey implements Item {

    @Override
    public String getItemFullDescription() {
        return "An old key covered in rust, but it might still work.";
    }

    @Override
    public String getItemDisplayName() {
        return "Ancient Key";
    }
}
