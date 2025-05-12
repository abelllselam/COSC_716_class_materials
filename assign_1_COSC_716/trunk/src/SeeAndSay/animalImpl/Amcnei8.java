package SeeAndSay.animalImpl;

import SeeAndSay.Animal;
import SeeAndSay.Talker;

public class Amcnei8 extends Animal implements Talker {

    private final String sound;

    /**
     * Simple Constructor accepting only a string.
     *
     * @param animalName The sound the animal makes
     */
    public Amcnei8(String animalName) {
        // Call the super constructor, which requires a string.
        super(animalName);

        // Set the sound of the animal
        this.sound = "Just made a three pointer";
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
