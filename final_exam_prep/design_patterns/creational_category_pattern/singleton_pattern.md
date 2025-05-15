# Singleton Pattern

- Structure & Participants
- Singleton: the class itself, which:
- Has a private static field holding its sole instance.
- Has a private constructor to prevent external instantiation.
- Provides a public static method (often called getInstance()) to return the singleton.

```java
//Classic Java Example:
public class Logger {
    // 1) The single shared instance, created lazily
    private static Logger instance;

    // 2) Private constructor prevents external instantiation
    private Logger() { }

    // 3) Global access point
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    // 4) Example method
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

//usage
// Both calls return the same Logger object
Logger log1 = Logger.getInstance();
Logger log2 = Logger.getInstance();

log1.log("Starting app");
// log1 == log2 evaluates to true
You need one shared resource (e.g., a configuration manager, logging service, connection pool).

You want controlled, lazy initialization.
```

# Using a Singleton’s Methods with Different Data

- Even though you always get the same Logger object, you’re free to call its methods with whatever parameters you like. The instance is single, but the behavior can vary based on the arguments you pass.

```java
Logger loggerA = Logger.getInstance();
Logger loggerB = Logger.getInstance();

// Both refer to the same object:
System.out.println(loggerA == loggerB); // true

// But you can send different messages:
loggerA.log("Starting application");    // prints: [LOG] Starting application
loggerB.log("An error occurred");       // prints: [LOG] An error occurred
```

# Why it works:

- log(String message) is just a method on that one Logger instance.
- Each call can use a different message parameter.
- So the singleton controls how many Logger objects you get (exactly one), but does not restrict what data you send to it.
