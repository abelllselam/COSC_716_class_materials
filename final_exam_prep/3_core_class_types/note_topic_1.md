- There are three core class-type constructs in java.
- Interface, Abstract , and Concrete classes.
- What is Interface:
  - It is a pure specification of behavior (a contract): What does this really mean? It means any class that implements the interface agrees to implement all its method as described, as the return type is specified. It is an agreement that the class that implements them is accepting the responsibility to implement all that is given by the interface otherwise there will be compile time error.
  - Declares methods without implementations (all methods are public abstract by default).: This means that the methods dont have body, which means they are not implemented. By default all methods of interface are public abstract because they are expected to be implemented on what ever class that calls them. However in modern java 8+ interface classes marked as default or static come with their own bodies, so implementing class dont have to provide them with bodies.
  - Example:

```java
public interface Logger {
    void log(String msg);              // abstract — must implement

    default void logError(String msg) { // default — already implemented
        log("[ERROR] " + msg);
    }
}
public interface Converter {
    static double inchesToCm(double in) { //Also have a body, but belong to the interface itself, not to instances:
        return in * 2.54;
    }
}
The static method belongs to the interface can not be overriden or implemented some where else.
```

- Interfaces are used when unrelated classes can share a common API. It is helpful because a class can inherit many interfaces.

# What is Abstract Class:

- This is a class that can include both abstract methods which are without bodies and concrete methods with bodies. Which the abstract methods have to be implemented while the concrete are optional.
- You can not instantiate an Abstract class directly. One class can extend it and another class that extended it can be instantiated.
- Example of an Abstract class:

```java
public abstract class Vehicle {
    // A field every vehicle has
    protected String id;

    // Abstract method: no body, must be implemented by subclasses
    public abstract void drive();

    // Concrete method: shared behavior provided here
    public void startEngine() {
        System.out.println("Engine started.");
    }

    // Constructor to initialize common state
    public Vehicle(String id) {
        this.id = id;
    }
}

//Example sub class:
public class Car extends Vehicle {
    public Car(String id) {
        super(id);  // initialize Vehicle’s id
    }

    @Override
    public void drive() {
        System.out.println("Car " + id + " is driving.");
    }
}
```

- Abstract classes can have constructors which can be called with super().
- Abstract methods declared with the abstract keyword and no method body. And every concrete class must implement them or itself declared abstract. That way it does not need to implement them.

# Concrete Class:

- This is a fully implemented class you can instantiated and not forced to implement its method as it is fully implemented which means all the methods here do have bodies including the extended classes and implemented Interfaces.
- Example of a Concrete Class:

```java
// Extending an abstract class and/or implementing interfaces
public class Car extends Vehicle implements Flyable {
    // Constructor
    public Car(String id) {
        super(id);          // initialize Vehicle’s state
    }

    // Implement abstract methods from Vehicle
    @Override
    public void drive() {
        startEngine();      // inherited concrete method
        System.out.println("Car " + id + " is driving.");
    }

    // Implement interface methods
    @Override
    public void takeOff() {
        System.out.println("Car " + id + " cannot fly!");
    }

    @Override
    public void land() {
        // no-op or throw exception
    }

    // Add any new, car-specific methods too
    public void openTrunk() {
        System.out.println("Trunk opened.");
    }
}

```

- Must override every abstract/in-interface method unless Car is itself declared abstract.
- You automatically get any concrete methods and fields from superclasses.

# Inner Classes:

- An inner class is a class defined within another class.It gives the inner class access to the outer classes members even if it is private (when it is no static when it is static it can only access static members).

- There are two main flavors:
  1. Member (non-static) inner class
     - Can access all outer class fields even private
     - Example:

```java
public class Outer {
    private int x = 10;

    // member inner class
    public class Inner {
        public void show() {
            System.out.println("x = " + x);  // accesses Outer.x
        }
    }
}

//Instantiation
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
inner.show();  // prints “x = 10”
```

    2. Static Nested class
    - can only access the outer class's static members directly.
    - Example:

```java
public class Outer {
    private static int y = 20;

    // static nested class
    public static class Nested {
        public void show() {
            System.out.println("y = " + y);
        }
    }
}

//Instantiation
Outer.Nested nested = new Outer.Nested();
nested.show();  // prints “y = 20”

```

# Anonymous Classes:

- It is a class without a name that you both define and instantiate in one step. directly where it is needed.
- Anonymous classes can be a one off implementation of an interface or subclass.
- The following is an example when the interface is implemented using an anonymous class:

```java
// 1) "new Runnable()" — start implementing the Runnable interface
new Runnable() {
    // 2) override the single method declared by Runnable
    @Override
    public void run() {
        // 3) this code runs when someone calls run()
        System.out.println("Hello from anonymous Runnable!");
    }
}; // 4) ends the class definition AND instantiates it immediately

//The following is the same way you can implement the above:
Runnable r = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running via variable!");
    }
};
r.run();  // invoke it later


```

**Now we are going to see extending a class Anonymously:**

```java
// Suppose we have this base class:
public class Greeter {
    public void greet() {
        System.out.println("Hello from Greeter");
    }
}

// Now we create and instantiate an anonymous subclass of Greeter:
Greeter g = new Greeter() {
    @Override
    public void greet() {
        // This override replaces the base behavior
        System.out.println("Hello from Anonymous Greeter!");
    }
};
// Note: Greeter() here calls the Greeter constructor, then applies our overrides.

g.greet();  // prints: Hello from Anonymous Greeter!

```

# In short we can do this for both:

```java
// For an interface:
Runnable r = new Runnable() {
    @Override
    public void run() { /*…*/ }
};

// For a class:
Greeter g = new Greeter("World") {
    @Override
    public void greet() { /*…*/ }
};

```
