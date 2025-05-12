package objectAdventure.world.awolde1;

public class OnState implements LampState {
    private final Lamp lamp;

    public OnState(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void use() {
        System.out.println("Turning the lamp off...");
        lamp.setState(lamp.getOffState());
    }

    @Override
    public String getDescription() {
        return "The lamp is on.";
    }
}
