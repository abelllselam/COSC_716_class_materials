**Pattern: Strategy**

# Intent:

    Define a family of interchangeable algorithms (strategies), encapsulate each one, and make them swappable at runtime so the client can choose how to perform an operation without changing its code.

# Explanation:

    Think of a toy car (Context) that can move in different ways—wheels, tracks, or hover. Instead of hard-coding one way, we give the car a “movement strategy” (Strategy) and can swap it for any kind of movement whenever we like. The car just asks its strategy to move; it doesn’t care how.

```java
// 1) Strategy: the “rule” for how to move.
//    Any movement method must follow this contract.
public interface MoveStrategy {
    void move();  // “Go—move however you’re supposed to!”
}

// 2) ConcreteStrategyA: move using wheels.
public class WheelMove implements MoveStrategy {
    @Override
    public void move() {
        // “Wheeled movement: rolling along!”
        System.out.println("Moving on wheels");
    }
}

// 3) ConcreteStrategyB: move using hover.
public class HoverMove implements MoveStrategy {
    @Override
    public void move() {
        // “Hover movement: floating above ground!”
        System.out.println("Hovering above ground");
    }
}

// 4) ConcreteStrategyC: move using tracks.
public class TrackMove implements MoveStrategy {
    @Override
    public void move() {
        // “Tracked movement: crawling on tracks!”
        System.out.println("Creeping on tracks");
    }
}

// 5) Context: the toy car that uses a MoveStrategy.
//    We can change its movement style at any time.
public class ToyCar {
    private MoveStrategy strategy;  // our current movement plan

    // Constructor: start with any strategy we like.
    public ToyCar(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    // Method to change strategy on the fly:
    public void setMoveStrategy(MoveStrategy strategy) {
        this.strategy = strategy;
    }

    // When we tell the car to go, it delegates to its strategy:
    public void go() {
        strategy.move();
    }
}

// 6) Client: our playroom where we build the car and try different moves.
public class Main {
    public static void main(String[] args) {
        // Start with wheels:
        ToyCar car = new ToyCar(new WheelMove());
        car.go();  // Output: Moving on wheels

        // Now swap to hover:
        car.setMoveStrategy(new HoverMove());
        car.go();  // Output: Hovering above ground

        // Finally, switch to tracks:
        car.setMoveStrategy(new TrackMove());
        car.go();  // Output: Creeping on tracks
    }
}

```

# What’s happening?

- MoveStrategy is the contract for moving.
- Each concrete strategy (WheelMove, HoverMove, TrackMove) knows exactly one way to move.
- ToyCar holds a MoveStrategy reference and, when told to go(), simply calls move() on whatever strategy it currently has.
- The client can swap strategies at runtime—no if statements or rewrites in ToyCar itself—making the code flexible and easy to extend.
