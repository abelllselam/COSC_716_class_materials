**Factory Method Pattern**

1. Intent
   Define an interface for creating an object, but let subclasses decide which class to instantiate.
   This decouples client code from concrete implementations.

2. Classic Java Example

```java
// This is like a drawing kit for buttons.
// Think of it as saying “I want a button, but I don't care how it looks yet.”
public interface Button {
    void render();   // “Draw the button on the screen”
    void onClick();  // “What happens when someone presses it”
}

// Here’s one kind of button that looks like Windows.
// It knows how to draw itself and what to do when clicked.
public class WindowsButton implements Button {
    @Override
    public void render() {
        // “Draw a Windows-style button”
        System.out.println("Render a Windows-style button");
    }
    @Override
    public void onClick() {
        // “When you click it, do a Windows thing”
        System.out.println("Handle Windows button click");
    }
}

// Here’s another kind of button that lives on a webpage (HTML).
public class HTMLButton implements Button {
    @Override
    public void render() {
        // “Draw a web button, like on a website”
        System.out.println("Render an HTML button");
    }
    @Override
    public void onClick() {
        // “When you click it, do a web thing”
        System.out.println("Handle HTML button click");
    }
}

// Dialog is like a box that needs a button inside.
// But Dialog itself doesn’t know which button—
// it asks its child class to give it one.
public abstract class Dialog {
    // This is our “button factory.” It’s like a magic recipe
    // that each special dialog fills in to say “This is my button!”
    public abstract Button createButton();

    // This is how the dialog shows itself on screen:
    public void renderWindow() {
        // 1) Ask for a button (using the recipe above)
        Button button = createButton();
        // 2) Draw the button
        button.render();
        // 3) Tell the button what to do when clicked
        button.onClick();
    }
}

// WindowsDialog is a special dialog that knows
// “I always want a Windows-style button!”
public class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        // Here we choose the Windows button
        return new WindowsButton();
    }
}

// WebDialog is another special dialog that knows
// “I always want a web (HTML) button!”
public class WebDialog extends Dialog {
    @Override
    public Button createButton() {
        // Here we choose the HTML button
        return new HTMLButton();
    }
}

// -----------------
// How we use it:
public class Main {
    public static void main(String[] args) {
        // Imagine we pick which dialog based on some setting:
        Dialog dialog = new WindowsDialog();
        // Now when we ask the dialog to show,
        // it uses its Windows-button recipe inside:
        dialog.renderWindow();
        // Output:
        // Render a Windows-style button
        // Handle Windows button click
    }
}


```

4. When to Use

- You need to allow a framework or library to instantiate objects supplied by client code.
- You want to centralize object creation logic in subclasses rather than expose constructors.
- You need flexibility to introduce new Product types without changing client code.
