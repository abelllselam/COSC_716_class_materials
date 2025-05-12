package objectAdventure.world.awolde1;

public class OffState implements LampState {
    private final Lamp lamp;

    public OffState(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void use() {
        System.out.println("Turning the lamp on...");
        lamp.setState(lamp.getOnState());
    }

    @Override
    public String getDescription() {
        return "The lamp is off.";
    }
}
