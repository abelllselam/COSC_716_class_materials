**Pattern: Command**

# Intent:

    Encapsulate a request (an action) as an object, so you can parameterize clients with different requests, queue or log operations, and support undoable actions—all without the sender knowing the details of how the request is carried out.

# Explanation:

    Imagine you have a toy remote control (Invoker) that can send “commands” (like “turn light on” or “turn light off”) to a toy lamp (Receiver). Instead of the remote directly flipping switches, it holds little command cards (Command objects). You can swap cards, store them for later, or even build an “undo” pile—without the remote needing to know how the lamp actually turns on or off.

```java
// 1) Command: the “card” that says exactly what to do.
//    Every card must have an execute() action.
public interface Command {
    void execute();   // “Do the thing on this card!”
    void undo();      // “Undo the thing if we need to go backwards!”
}

// 2) Receiver: the toy lamp that actually knows how to turn on/off.
public class Light {
    private boolean isOn = false;  // Lamp starts off

    public void on() {
        isOn = true;
        System.out.println("💡 Light is ON");
    }

    public void off() {
        isOn = false;
        System.out.println("💤 Light is OFF");
    }
}

// 3) ConcreteCommand: a card to turn the light on.
public class LightOnCommand implements Command {
    private Light light;    // the lamp we control

    public LightOnCommand(Light light) {
        this.light = light; // remember which lamp to control
    }

    @Override
    public void execute() {
        // “When you play this card, turn the light on!”
        light.on();
    }

    @Override
    public void undo() {
        // “If you want to undo, turn the light off!”
        light.off();
    }
}

// 4) ConcreteCommand: a card to turn the light off.
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        // “Play this card to turn the light off!”
        light.off();
    }

    @Override
    public void undo() {
        // “Undo means turn the light back on!”
        light.on();
    }
}

// 5) Invoker: the remote that holds one command at a time.
//    It tells the card to execute or undo.
public class RemoteControl {
    private Command slot;      // a single slot for one command
    private Command lastCommand; // remember last command for undo

    // Put a command card in the remote:
    public void setCommand(Command command) {
        this.slot = command;
    }

    // Press the “on” button:
    public void pressButton() {
        slot.execute();          // “Do the card’s action!”
        lastCommand = slot;      // remember it for undo
    }

    // Press the “undo” button:
    public void pressUndo() {
        if (lastCommand != null) {
            lastCommand.undo();  // “Undo the last action!”
        }
    }
}

// 6) Client: our playroom where we wire everything together.
public class Main {
    public static void main(String[] args) {
        // The receiver (toy lamp):
        Light livingRoomLight = new Light();

        // Create two cards: one to turn on, one to turn off:
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // The invoker (toy remote):
        RemoteControl remote = new RemoteControl();

        // 1) Put the “on” card and press the button:
        remote.setCommand(lightOn);
        remote.pressButton();    // Output: 💡 Light is ON

        // 2) Press undo to take the action back:
        remote.pressUndo();      // Output: 💤 Light is OFF

        // 3) Put the “off” card and press the button:
        remote.setCommand(lightOff);
        remote.pressButton();    // Output: 💤 Light is OFF

        // 4) Undo again:
        remote.pressUndo();      // Output: 💡 Light is ON
    }
}

```

# What’s happening?

- Command is our “card” interface with execute() and undo().
- LightOnCommand and LightOffCommand are concrete cards that tell a Light how to turn on or off.
- RemoteControl is the invoker—it doesn’t know lamp details, just that it can execute() or undo() the card in its slot.
- The client sets up which card goes into the remote, presses buttons, and can undo actions flexibly.
- This is the Command Pattern: you encapsulate requests as objects, giving you powerful flexibility (queues, logs, undos) without hard-coding behavior in the invoker.
