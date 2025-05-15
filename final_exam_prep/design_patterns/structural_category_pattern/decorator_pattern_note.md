**Decorator Pattern**

1. Intent
   Attach additional responsibilities to an object dynamically, without changing its class. Decorators wrap the original object and add new behavior.
2. Java Example (Simple Text Stream Decorator)

```java
// 1) Component interface
public interface Text {
    String read();  // read some text
}

// 2) ConcreteComponent
public class PlainText implements Text {
    private String content;
    public PlainText(String content) {
        this.content = content;
    }
    @Override
    public String read() {
        return content;  // just return raw text
    }
}

// 3) Decorator base class
public abstract class TextDecorator implements Text {
    protected Text inner;  // the wrapped component

    public TextDecorator(Text inner) {
        this.inner = inner;
    }

    @Override
    public String read() {
        return inner.read();  // delegate by default
    }
}

// 4) ConcreteDecorator: adds uppercase behavior
public class UpperCaseDecorator extends TextDecorator {
    public UpperCaseDecorator(Text inner) {
        super(inner);
    }

    @Override
    public String read() {
        // add behavior: convert to uppercase
        return inner.read().toUpperCase();
    }
}

// 5) ConcreteDecorator: adds bracket around text
public class BracketDecorator extends TextDecorator {
    public BracketDecorator(Text inner) {
        super(inner);
    }

    @Override
    public String read() {
        // add behavior: wrap in brackets
        return "[" + inner.read() + "]";
    }
}

// 6) Client code: layering decorators
public class Main {
    public static void main(String[] args) {
        Text plain = new PlainText("hello");

        // Wrap with uppercase, then brackets:
        Text decorated = new BracketDecorator(
                             new UpperCaseDecorator(plain));

        System.out.println(decorated.read());
        // Output: [HELLO]
    }
}
```

# Why it works

- You start with a simple PlainText.
- Each decorator takes a Text and adds its own behavior, delegating the rest to the wrapped object.
- You can stack decorators in any order to combine behaviors.
