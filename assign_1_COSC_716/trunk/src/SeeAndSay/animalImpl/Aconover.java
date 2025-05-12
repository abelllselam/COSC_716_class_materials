package SeeAndSay.animalImpl;

import SeeAndSay.Animal;
import SeeAndSay.Talker;

/**
 * The Aconover class is used to represent an instance animal.
 * We are using our usernames to prevent naming conflicts with other students.
 */
public class Aconover extends Animal implements Talker {

    private final String sound;

    /**
     * Simple Constructor accepting only a string.
     *
     * @param animalName The sound the animal makes
     */
    public Aconover(String animalName) {
        // Call the super constructor, which requires a string.
        super(animalName);

        // Set the sound of the animal
        this.sound = "What's so bad about multiple inheritance?";
    }


    /**
     * Get the sound of the animal.
     *
     * @return The sound of the animal
     */
    public String getSound() {
        // Return the sound of the animal
        return this.sound;
    }

}
