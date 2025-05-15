**Adapter Pattern**

1. Intent
   Convert the interface of a class into another interface clients expect, allowing classes with incompatible interfaces to work together.
2. Java Example

````java
// 1) This is the “new” way we want to play music:
public interface MediaPlayer {
    void play(String filename);  // “Play this song!”
}

// 2) Here’s our old music player that speaks a different language:
public class LegacyPlayer {
    public void specificPlay(String filename) {
        System.out.println("🎵(old) Playing: " + filename);
    }
}

// 3) The Adapter is like a translator toy.
//    It takes our old player and makes it speak the new language:
public class MediaAdapter implements MediaPlayer {
    private LegacyPlayer oldPlayer;  // the wrapped old toy

    public MediaAdapter(LegacyPlayer oldPlayer) {
        this.oldPlayer = oldPlayer;  // put the old toy inside the adapter
    }

    @Override
    public void play(String filename) {
        // We tell the adapter “play!”
        // Adapter says to old toy: “specificPlay!”
        oldPlayer.specificPlay(filename);
    }
}

// 4) AudioPlayer is our “super toy” that always expects a new‐language player.
//    We hide the old-toy details inside the adapter so nobody else worries:
public class AudioPlayer implements MediaPlayer {
    private MediaPlayer player;  // slot for any new‐language player

    public AudioPlayer() {
        // We build a translator around the old toy right here:
        player = new MediaAdapter(new LegacyPlayer());
    }

    @Override
    public void play(String filename) {
        // No matter if it's a new toy or old toy+adapter,
        // we just call play() and it works:
        player.play(filename);
    }
}

/*
  --- Why there’s no problem here ---
  • We never call “new Runnable” inside itself, so no loops.
  • AudioPlayer only calls play() on its ‘player’ slot.
  • That ‘player’ is the Adapter, which then calls the old toy.
  • Everything happens once, in a straight line—no infinite loops.

  Imagine:
  AudioPlayer → Adapter → LegacyPlayer
  Each step just passes along the “play” message, like a game of telephone,
  and then the music plays. All good!
*/
```
4. Why It Helps

- Lets you integrate legacy or third-party code without modifying it.
- Keeps client code clean by programming to the Target interface.

````
