package objectAdventure.world.awolde1;
import objectAdventure.core.item.Item;
import objectAdventure.core.item.ItemInteractionEvent;
import objectAdventure.core.item.ItemInteractionResult;
import objectAdventure.core.item.ItemInteractionEventType;


public class Lamp implements Item {

    private final LampState onState;
    private final LampState offState;
    private LampState currentState;

    public Lamp() {
        this.onState = new OnState(this);
        this.offState = new OffState(this);
        this.currentState = offState;
    }

    public void setState(LampState state) {
        this.currentState = state;
    }

    public LampState getOnState() {
        return onState;
    }

    public LampState getOffState() {
        return offState;
    }

    @Override
    public String getItemDisplayName() {
        return "Lamp";
    }

    @Override
    public String getItemFullDescription() {
        return currentState.getDescription();
    }


    
    public String getName() {
        return "Lamp";
    }

    @Override
public ItemInteractionResult itemInteractionHandler(ItemInteractionEvent event) {
    if (event.event() == ItemInteractionEventType.USE) {
        currentState.use();
        return ItemInteractionResult.success("You toggled the lamp.");
    }

    if (event.event() == ItemInteractionEventType.INSPECT) {
        return ItemInteractionResult.success(currentState.getDescription());
    }

    return ItemInteractionResult.success(); 
}
}
