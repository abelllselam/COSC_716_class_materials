package SeeAndSay;

/**
 * The Talker interface represents an entity that is capable of making a sound.
 * It extends the Named interface, meaning that any class implementing Talker
 * must provide a name for the entity in addition to defining the sound it produces.
 */
public interface Talker extends Named {
    /**
     * Gets the sound made by the animal.
     *
     * @return the sound as a String
     */
    String getSound();
}




