package SeeAndSay; // Declares that the interface is part of the SeeAndSay package.

/**
 * The FlyingTalker interface represents an entity
 * that is capable of both flying and talking, and has
 * an additional characteristic of producing a sound while moving.
 *
 * <p>This interface extends the {@link Flyer} and {@link Talker}
 * interfaces, meaning that any class implementing FlyingTalker
 * will inherit all methods defined by these interfaces.</p>
 *
 * <p>Implementing classes should provide unique implementations
 * for flying-related, talking-related, and moving sound behavior.</p>
 *
 * @see Flyer
 * @see Talker
 */
public interface FlyingTalker extends Flyer, Talker {
    /**
     * Returns the sound that the entity makes while moving.
     *
     * <p>Any class implementing this interface must provide an
     * implementation to describe the specific moving sound.</p>
     *
     * @return The moving sound as a {@code String}.
     */
    String getMovingSound();
}