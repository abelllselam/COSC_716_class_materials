**Proxy**

- Provide a stand-in or “surrogate” for another object to control access, add laziness, logging, security checks, or other extra steps—all without changing the real object’s code.

# Structure & Participants:

| Role            | In Code           | What It Does                                                      |
| --------------- | ----------------- | ----------------------------------------------------------------- |
| **Subject**     | `Image` interface | The common interface both proxy and real object share.            |
| **RealSubject** | `RealImage`       | The heavy object that does the real work (loading & display).     |
| **Proxy**       | `ProxyImage`      | Holds a reference to `RealImage` and controls when/how it’s used. |

3. Example Code with “Five-Year-Old” Comments

```java
// 1) Subject: the “contract” both real and proxy follow.
public interface Image {
    void display();  // “Show me the picture!”
}

// 2) RealSubject: the real, heavy picture.
//    It takes time to load from disk, so we don’t want to do that until needed.
public class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        // “I’m loading the big picture from disk—this is slow!”
        System.out.println("Loading " + filename);
    }

    @Override
    public void display() {
        // “Now I actually show the picture to you.”
        System.out.println("Displaying " + filename);
    }
}

// 3) Proxy: our friendly helper that stands in front of the real picture.
//    It only loads the real one when someone really wants to see it.
public class ProxyImage implements Image {
    private String filename;    // Remember the picture’s file name
    private RealImage realImage; // The hidden real picture, starts out empty

    public ProxyImage(String filename) {
        this.filename = filename; // “I remember which picture you want.”
    }

    @Override
    public void display() {
        // “First time? I haven’t loaded the real picture yet…”
        if (realImage == null) {
            // “Let me load it now, behind the scenes!”
            realImage = new RealImage(filename);
        }
        // “Okay, now that it’s ready, I tell it to show itself.”
        realImage.display();
    }
}

// 4) Client: our playroom where someone asks to see the picture.
public class Main {
    public static void main(String[] args) {
        // We give the client a proxy, not the real picture:
        Image img = new ProxyImage("photo.jpg");

        // First call:
        // Proxy checks: “Do I have the real picture?”
        // No → loads it (slow), then shows it.
        img.display();
        // Output:
        // Loading photo.jpg
        // Displaying photo.jpg

        // Second call:
        // Proxy checks: “Do I have the real picture?”
        // Yes → skips loading, directly shows it.
        img.display();
        // Output:
        // Displaying photo.jpg
    }
}

```

# Why no errors?

- Both RealImage and ProxyImage implement the Image interface, so they each have a display() method.
- The client only knows about Image, so it doesn’t care whether it’s talking to a proxy or the real thing.
